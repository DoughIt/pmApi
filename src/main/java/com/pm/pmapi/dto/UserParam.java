package com.pm.pmapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @Description 自定义登录时传递参数
 *
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-10-13 19:11
 */
@Getter
@Setter
public class UserParam {
    /**
     * 用户openid
     */
    @NotEmpty
    private String openId;
    // TODO 等待小程序端定义其他属性
}
