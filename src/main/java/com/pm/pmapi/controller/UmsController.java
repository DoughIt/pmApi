package com.pm.pmapi.controller;


import com.pm.pmapi.common.api.CommonResult;
import com.pm.pmapi.dto.UpdateUserParam;
import com.pm.pmapi.dto.UserParam;
import com.pm.pmapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
        if (status > 0) {
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
        return CommonResult.failed("");
    }

    /**
     * 后端登出
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
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
    public CommonResult getAuthCode(@RequestParam String studentId) {
        return userService.generateAuthCode(studentId);
    }

    /**
     * 判断验证码是否正确
     */
    @RequestMapping(value = "/authCode", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult verifyAuthCOde(@RequestParam String studentId, @RequestParam String authCode) {
        return userService.verifyAuthCode(studentId, authCode);
    }
}
