package com.pm.pmapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @Description 自定义发送消息接收参数
 *
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-12-07 11:59
 */
@Getter
@Setter
public class MessageParam {
    /**
     * 消息接受者
     */
    @NotEmpty
    private Long receiverId;
    /**
     * 内容
     */
    @NotEmpty
    private String content;
}
