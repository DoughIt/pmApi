package com.pm.pmapi.dto;

import com.pm.pmapi.mbg.model.TabMessage;
import com.pm.pmapi.mbg.model.TabUser;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jerry Zhang <https://github.com/doughit>
 * @Description 自定义返回结构-消息摘要
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @date 2021-12-07 11:21
 */
@Getter
@Setter
public class MessageSummaryInfo {
    /**
     * 对话者信息
     */
    private SimpleUserInfo interlocutor;
    /**
     * 最后一条消息
     */
    private TabMessage lastMessage;
}
