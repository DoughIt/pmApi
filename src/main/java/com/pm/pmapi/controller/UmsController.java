package com.pm.pmapi.controller;


import com.pm.pmapi.common.api.CommonResult;
import com.pm.pmapi.dto.UserParam;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 用户模块控制器，控制用户登录、登出，获取用户信息，更新用户信息等
 *
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-10-13 19:17
 */
@Controller
@RequestMapping("/api/ums")
public class UmsController {

    /**
     * 用户登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> login(@Validated @RequestBody UserParam userParam) {
        // TODO 调用service进行登录验证
        return CommonResult.validateFailed("验证失败！");
    }

    /**
     * 后端登出
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult logout() {
        return CommonResult.success(null);
    }

}
