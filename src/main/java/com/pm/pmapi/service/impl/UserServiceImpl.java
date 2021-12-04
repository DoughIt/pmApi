package com.pm.pmapi.service.impl;

import cn.hutool.core.util.StrUtil;
import com.pm.pmapi.common.exception.Asserts;
import com.pm.pmapi.common.utils.JwtTokenUtil;
import com.pm.pmapi.dto.AdminUserDetails;
import com.pm.pmapi.dto.UpdateUserParam;
import com.pm.pmapi.dto.UserParam;
import com.pm.pmapi.mbg.mapper.TabUserMapper;
import com.pm.pmapi.mbg.model.TabUser;
import com.pm.pmapi.mbg.model.TabUserExample;
import com.pm.pmapi.service.UserCacheService;
import com.pm.pmapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


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
    private UserCacheService userCacheService;

    /**
     * 注册用户
     *
     * @param userParam 用户信息
     * @return 1操作成功，0操作失败
     */
    @Override
    public int register(UserParam userParam) {
        if (StrUtil.isEmptyOrUndefined(userParam.getStudentId()) && StrUtil.isEmptyOrUndefined(userParam.getOpenId()))
            return 0;
        TabUser user = new TabUser();
        BeanUtils.copyProperties(userParam, user);
        user.setRegisterTime(new Date());
        // 查询是否具有相同studentId 或 openId的用户
        TabUserExample example = new TabUserExample();
        if (!StrUtil.isEmptyOrUndefined(user.getStudentId()))
            example.createCriteria().andStudentIdEqualTo(user.getStudentId());
        if (!StrUtil.isEmptyOrUndefined(user.getOpenId()))
            example.or(example.createCriteria().andOpenIdEqualTo(user.getOpenId()));
        List<TabUser> userList = userMapper.selectByExample(example);
        if (userList != null && userList.size() > 0) {
            Asserts.fail("用户已存在");
        }
        // 将密码进行加密
        String encodePassword = passwordEncoder.encode(user.toString());
        user.setPassword(encodePassword);
        return userMapper.insert(user);
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
        try {
            UserDetails userDetails = loadUserById(userParam.getId());
            if (!passwordEncoder.matches(userParam.getPassword(), userDetails.getPassword())) {
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
            updateLoginTime(userParam.getId());

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
        return userMapper.deleteByPrimaryKey(id);
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
        userCacheService.delUser(user.getId());
        return userMapper.updateByPrimaryKey(user);
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
            return -1;
        }
        TabUser user = getUserById(userParam.getId());
        if (user == null) {
            Asserts.fail("用户不存在");
            return -2;
        }
        // 验证旧密码
        if (!passwordEncoder.matches(userParam.getPassword(), user.getPassword())) {
            Asserts.fail("验证失败，新密码与旧密码相同");
            return -2;
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
        return null;
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
}
