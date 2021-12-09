package com.pm.pmapi.dto;

import lombok.Getter;
import lombok.Setter;


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
     * 用户id
     */
    private Long id;

    /**
     * 学号
     */
    private String studentId;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 小程序端传入wx.login返回值code
     */
    private String miniCode;

    /**
     * 用户昵称
     */
    private String nickName;
}
