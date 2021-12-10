package com.pm.pmapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @Description 自定义更新用户信息时传递的参数
 *
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-11-29 11:16
 */
@Getter
@Setter
public class UpdateUserParam {
    /**
     * 用户id，非空
     */
    @NotEmpty
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 旧密码
     */
    private String password;
    /**
     * 新密码
     */
    private String newPassword;
    /**
     * 头像链接
     */
    private String avatar;
    /**
     * 用户个人描述
     */
    private String description;
}
