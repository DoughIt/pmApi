package com.pm.pmapi.controller;


import cn.hutool.core.util.StrUtil;
import com.pm.pmapi.common.api.CommonPage;
import com.pm.pmapi.common.api.CommonResult;
import com.pm.pmapi.common.constant.TopicFilter;
import com.pm.pmapi.component.IAuthenticationFacade;
import com.pm.pmapi.dto.*;
import com.pm.pmapi.mbg.model.TabUser;
import com.pm.pmapi.service.TopicService;
import com.pm.pmapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jerry Zhang <https://github.com/doughit>
 * @Description 用户模块控制器，控制用户登录、登出，获取用户信息，更新用户信息等
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @date 2021-10-13 19:17
 */
@Controller
@RequestMapping("/api/ums")
public class UmsController {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private UserService userService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private IAuthenticationFacade authenticationFacade;

    /**
     * 用户注册
     *
     * @param userParam 注册参数
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult register(@Validated @RequestBody UserParam userParam) {
        int status = userService.register(userParam);
        if (status <= 0) {
            return CommonResult.failed("注册失败");
        }
        return CommonResult.success(null);
    }

    /**
     * 用户登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@Validated @RequestBody UserParam userParam) {
        String token = userService.login(userParam);
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    /**
     * 获取用户信息
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getUserInfo() {
        if (authenticationFacade.getAuthentication() == null) {
            return CommonResult.unauthorized(null);
        }
        Long userId = Long.parseLong(authenticationFacade.getAuthentication().getName());
        TabUser user = userService.getUserById(userId);
        user.setPassword(null);
        return CommonResult.success(user);
    }

    /**
     * 刷新token
     */
    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = userService.refreshToken(token);
        if (refreshToken == null) {
            return CommonResult.failed("Token 已过期");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }


    /**
     * 修改用户基本信息
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateUser(@Validated @RequestBody UpdateUserParam userParam) {
        // 更新用户名
        if (!StrUtil.isEmpty(userParam.getUsername())) {
            TabUser user = new TabUser();
            user.setId(userParam.getId());
            user.setUsername(userParam.getUsername());
            userService.update(user);
        }
        if (!StrUtil.isEmpty(userParam.getNewPassword())) {
            int status = userService.updatePassword(userParam);
            if (status > 0) {
                logout();
                return CommonResult.success("你已成功修改密码，请重新登录");
            } else if (status == -1) {
                return CommonResult.failed("提交参数不合法");
            } else if (status == -2) {
                return CommonResult.failed("用户不存在");
            } else if (status == -3) {
                return CommonResult.failed("新密码与旧密码重复");
            } else {
                return CommonResult.failed();
            }
        }
        return CommonResult.success("更新成功");
    }

    /**
     * 后端登出
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult logout() {
        return CommonResult.success(null);
    }


    /**
     * 获取验证码
     *
     * @param studentId
     * @return
     */
    @RequestMapping(value = "/authCode", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAuthCode(@RequestParam("studentId") String studentId) {
        return userService.generateAuthCode(studentId);
    }

    /**
     * 判断验证码是否正确
     */
    @RequestMapping(value = "/authCode", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult verifyAuthCOde(@Validated @RequestBody AuthCodeParam authCodeParam) {
        return userService.verifyAuthCode(authCodeParam.getStudentId(), authCodeParam.getAuthCode());
    }

    /**
     * 获取帖子列表
     *
     * @param pageNum
     * @param pageSize
     * @param lessonId
     * @param goodsId
     * @param topicId
     * @return
     */
    @RequestMapping(value = "topicList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<TopicInfo>> getTopicList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                            @RequestParam(value = "pageSize", defaultValue = "8") Integer pageSize,
                                                            @RequestParam("lessonId") Long lessonId,
                                                            @RequestParam("goodsId") Long goodsId,
                                                            @RequestParam("topicId") Long topicId) {
        List<TopicInfo> topicInfoList = new ArrayList<>();
        if (topicId != null) {
            topicInfoList = topicService.listChildrenByParentId(topicId, pageNum, pageSize);
        } else if (lessonId != null) {
            topicInfoList = topicService.listTopicByFilterType(TopicFilter.LESSON, lessonId, pageNum, pageSize);
        } else if (goodsId != null) {
            topicInfoList = topicService.listTopicByFilterType(TopicFilter.GOODS, goodsId, pageNum, pageSize);
        }
        return CommonResult.success(CommonPage.restPage(topicInfoList));
    }


    /**
     * 获取我的帖子列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/myTopicList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<TopicInfo>> getMyTopics(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                           @RequestParam(value = "pageSize", defaultValue = "8") Integer pageSize) {

        if (authenticationFacade.getAuthentication() == null) {
            return CommonResult.unauthorized(null);
        }
        Long userId = Long.parseLong(authenticationFacade.getAuthentication().getName());
        List<TopicInfo> topicInfoList = topicService.listTopicByUserId(userId, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(topicInfoList));
    }

    /**
     * 发布帖子
     *
     * @param topicParam
     * @return
     */
    @RequestMapping(value = "/topic", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<TopicInfo> addTopic(@Validated @RequestBody TopicParam topicParam) {
        return CommonResult.success(topicService.createTopic(
                Long.parseLong(authenticationFacade.getAuthentication().getName()), topicParam));
    }


}
