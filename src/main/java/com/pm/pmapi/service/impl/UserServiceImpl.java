package com.pm.pmapi.service.impl;

import cn.hutool.core.util.StrUtil;
import com.pm.pmapi.common.api.CommonResult;
import com.pm.pmapi.common.exception.Asserts;
import com.pm.pmapi.common.utils.JwtTokenUtil;
import com.pm.pmapi.component.IAuthenticationFacade;
import com.pm.pmapi.dao.UserDao;
import com.pm.pmapi.dto.AdminUserDetails;
import com.pm.pmapi.dto.MiniProgramSession;
import com.pm.pmapi.dto.UpdateUserParam;
import com.pm.pmapi.dto.UserParam;
import com.pm.pmapi.mbg.mapper.TabUserMapper;
import com.pm.pmapi.mbg.model.*;
import com.pm.pmapi.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 * @author Jerry Zhang <https://github.com/doughit>
 * @Description UserService实现类
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @date 2021-11-29 11:15
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TabUserMapper userMapper;
    @Autowired
    private UserDao userDao;
    @Autowired
    private IAuthenticationFacade authenticationFacade;
    @Autowired
    private MiniProgramService miniProgramService;
    @Autowired
    private UserCacheService userCacheService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private MailService mailService;
    @Value("${redis.key.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    /**
     * 注册用户
     *
     * @param userParam 用户信息
     * @return 用户id
     */
    @Override
    public Long register(UserParam userParam) {
        TabUser user = new TabUser();
        BeanUtils.copyProperties(userParam, user);
        user.setUnthorized(StrUtil.isEmptyOrUndefined(userParam.getStudentId()) ? 0 : 1);
        user.setStatus(true);
        user.setRegisterTime(new Date());
        user.setNav(0L);
        if (StrUtil.isNotEmpty(userParam.getStudentId()) || StrUtil.isNotEmpty(userParam.getOpenId())) {
            // 查询是否具有相同studentId或openID的用户
            TabUserExample example = new TabUserExample();
            example.createCriteria().andStudentIdEqualTo(user.getStudentId());
            example.or(example.createCriteria().andOpenIdEqualTo(user.getOpenId()));
            List<TabUser> userList = userMapper.selectByExample(example);
            if (userList != null && userList.size() > 0) {
                Asserts.fail("用户已存在");
            }
        }
        // 将密码进行加密
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        userMapper.insert(user);
        return user.getId();
    }

    /**
     * 用户登录
     *
     * @param userParam 用户信息
     * @return 生成的Jwt token
     */
    @Override
    public String login(UserParam userParam) {
        String token = null;
        TabUser user = getUserByStudentIdOrOpenId(userParam);
        // 使用学号登录
        if (StrUtil.isNotEmpty(userParam.getStudentId()) && user.getUnthorized() != 1) {
            Asserts.fail("邮箱未认证");
        }
        try {
            UserDetails userDetails = loadUserById(user.getId());
            //小程序端使用code不用验证密码
            if (StrUtil.isEmptyOrUndefined(userParam.getMiniCode())
                    && !passwordEncoder.matches(userParam.getPassword(), userDetails.getPassword())) {
                Asserts.fail("密码错误");
            }
            if (!userDetails.isEnabled()) {
                Asserts.fail("账号已被禁用");
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                    null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            token = jwtTokenUtil.generateToken(userDetails);
            // 更新登录时间
            updateLoginTime(user.getId());

        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常：{}", e.getMessage());
        }
        return token;
    }

    /**
     * 更新登录时间
     *
     * @param id
     */
    private void updateLoginTime(Long id) {
        TabUser user = getUserById(id);
        user.setLoginTime(new Date());
        userMapper.updateByPrimaryKey(user);
    }

    /**
     * 刷新Token
     *
     * @param oldToken 旧的Token
     * @return 新的Token
     */
    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @Override
    public int delete(Long id) {
        userCacheService.delUser(id);
        TabUserExample example = new TabUserExample();
        example.createCriteria().andIdEqualTo(id);
        return userMapper.deleteByExample(example);
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @Override
    public int update(TabUser user) {
        TabUser rawUser = getUserById(user.getId());
        if (rawUser == null) {
            Asserts.fail("用户不存在");
            return -2;
        }
        if (StrUtil.equals(rawUser.getPassword(), user.getPassword())) {
            // 与原密码一致
            user.setPassword(null);
        } else {
            if (StrUtil.isEmpty(user.getPassword())) {
                user.setPassword(null);
            } else {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
        }
        user.setNav(rawUser.getNav());
        userCacheService.delUser(user.getId());
        return userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 更新用户密码
     *
     * @param userParam
     * @return
     */
    @Override
    public int updatePassword(UpdateUserParam userParam) {
        if (StrUtil.isEmpty(userParam.getPassword()) || StrUtil.isEmpty(userParam.getNewPassword())) {
            Asserts.fail("密码为空");
        }
        TabUser user = getUserById(userParam.getUserId());
        if (user == null) {
            Asserts.fail("用户不存在");
        }
        // 验证旧密码
        if (!passwordEncoder.matches(userParam.getPassword(), user.getPassword())) {
            Asserts.fail("旧密码错误");
        }
        if (userParam.getNewPassword().equals(userParam.getPassword())) {
            Asserts.fail("验证失败，新密码与旧密码相同");
        }
        // 将旧密码进行加密操作
        String encodePassword = passwordEncoder.encode(userParam.getPassword());
        user.setPassword(encodePassword);
        userCacheService.delUser(user.getId());
        return userMapper.updateByPrimaryKey(user);
    }

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    @Override
    public UserDetails loadUserById(Long id) {
        TabUser user = getUserById(id);
        if (user != null) {
            return new AdminUserDetails(user);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public TabUser getUserById(Long id) {
        TabUser user = userCacheService.getUser(id);
        if (user != null) return user;
        TabUserExample example = new TabUserExample();
        example.createCriteria().andIdEqualTo(id);
        List<TabUser> userList = userMapper.selectByExample(example);
        if (userList != null && userList.size() > 0) {
            user = userList.get(0);
            userCacheService.setUser(user);
            return user;
        }
        return null;
    }

    /**
     * 从数据库获取用户信息
     *
     * @param userParam
     * @return
     */
    @Override
    public TabUser getUserByStudentIdOrOpenId(UserParam userParam) {
        TabUserExample example = new TabUserExample();
        if (StrUtil.isNotEmpty(userParam.getStudentId())) {
            example.createCriteria().andStudentIdEqualTo(userParam.getStudentId());
            List<TabUser> userList = userMapper.selectByExample(example);
            if (userList != null && userList.size() > 0) {
                return userList.get(0);
            }
        } else if (StrUtil.isNotEmpty(userParam.getMiniCode())) {
            // 获取openId
            MiniProgramSession session = miniProgramService.codeToSession(userParam.getMiniCode());
            if (session == null || session.getOpenid() == null) {
                Asserts.fail("获取小程序会话失败");
            }
            if (session.getErrmsg() != null) {
                Asserts.fail(session.getErrmsg());
            }
            String openId = session.getOpenid();
            example.createCriteria().andOpenIdEqualTo(openId);
            List<TabUser> userList = userMapper.selectByExample(example);
            if (userList != null && userList.size() > 0) {
                return userList.get(0);
            }
            // 创建用户
            userParam.setOpenId(openId);
            userParam.setStudentId(null);
            // 默认密码
            userParam.setPassword("111111");
            return getUserById(register(userParam));
        }
        return null;
    }

    /**
     * 生成验证码，发送至studentId@fudan.edu.cn邮箱
     *
     * @param studentId
     * @return
     */
    @Override
    public CommonResult generateAuthCode(String studentId) {
        UserParam param = new UserParam();
        param.setStudentId(studentId);
        TabUser user = getUserByStudentIdOrOpenId(param);
        if (user.getUnthorized() == 1 && !StrUtil.isEmptyOrUndefined(user.getOpenId())) {
            return CommonResult.failed("邮箱已认证");
        }
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(10));
        }
        // 验证码绑定学号并存储到redis
        String key = REDIS_KEY_PREFIX_AUTH_CODE + studentId;
        redisService.set(key, sb.toString());
        redisService.expire(key, AUTH_CODE_EXPIRE_SECONDS);
        mailService.sendHtmlAuthCode(studentId + "@fudan.edu.cn", sb.toString(), (int) (AUTH_CODE_EXPIRE_SECONDS / 60), studentId);
        user.setUnthorized(2);
        userMapper.updateByPrimaryKeySelective(user);
        return CommonResult.success("验证码已发送到" + studentId + "@fudan.edu.cn邮箱");
    }

    /**
     * 判断验证码是否正确
     *
     * @param studentId
     * @param authCode
     * @return
     */
    @Override
    public CommonResult verifyAuthCode(String studentId, String authCode) {
        if (StrUtil.isEmpty(authCode)) {
            return CommonResult.failed("请输入验证码");
        }
        if (authCode.length() != 4) {
            return CommonResult.failed("验证码是4位长度数字");
        }
        String realAuthCode = (String) redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + studentId);
        if (StrUtil.isEmptyOrUndefined(realAuthCode)) {
            return CommonResult.failed("验证码已过期");
        }
        UserParam param = new UserParam();
        param.setStudentId(studentId);
        TabUser user = getUserByStudentIdOrOpenId(param);
        if (authCode.equals(realAuthCode)) {
            if (StrUtil.isEmptyOrUndefined(user.getOpenId())) {
                // 网页端已认证
                // 小程序端绑定用户
                TabUser currentUser = getUserById(Long.valueOf(authenticationFacade.getAuthentication().getName()));
                currentUser.setStudentId(studentId);
                currentUser.setUnthorized(1);
                userDao.updateUserNavByPrimaryKey(user.getId(), currentUser.getId(), currentUser.getNav());
                userMapper.updateByPrimaryKeySelective(currentUser);
                userDao.updateUserIdAndDeleteByPrimaryKey(currentUser.getId(), user.getId());
            } else {
                user.setUnthorized(1);
                userMapper.updateByPrimaryKeySelective(user);
            }
            return CommonResult.success(null, "验证码校验成功，authCode=" + realAuthCode);
        } else {
            user.setUnthorized(0);
            userMapper.updateByPrimaryKeySelective(user);
            return CommonResult.failed("验证码不正确");
        }
    }
}
