package com.pm.pmapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @Description 定义大多数返回数据只需要的部分用户数据
 *
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-12-07 22:24
 */
@Getter
@Setter
public class SimpleUserInfo {
    /**
     * 用户id
     */
    @NotEmpty
    private Long id;
    /**
     * 学号
     */
    private String studentId;
    /**
     * 小程序端openid
     */
    private String openId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 头像地址
     */
    private String avatar;
}
