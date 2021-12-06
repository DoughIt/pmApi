package com.pm.pmapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @Description 验证码参数
 *
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-12-06 08:52
 */
@Getter
@Setter
public class AuthCodeParam {
    /**
     * 学号
     */
    @NotEmpty
    private String studentId;

    /**
     * 验证码
     */
    @NotEmpty
    private String authCode;
}
