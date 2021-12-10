package com.pm.pmapi.service;

import com.pm.pmapi.dto.MessageSummaryInfo;
import com.pm.pmapi.mbg.model.TabMessage;

import java.util.List;

/**
 * @Description 自定义消息服务接口
 *
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-12-07 11:24
 */
public interface MessageService {
    /**
     * 获取与userId的相关所有消息摘要
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<MessageSummaryInfo> listMessageSummariesByUserId(Long userId, Integer pageNum, Integer pageSize);

    /**
     * 获取senderId与receiverId间的消息记录
     * @param senderId
     * @param receiverId
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<TabMessage> listMessagesByTwo(Long senderId, Long receiverId, Integer pageNum, Integer pageSize);

    /**
     * 添加消息记录
     * @param message
     * @return
     */
    int createMessage(TabMessage message);

    /**
     * 更新消息已读状态
     * @param messageId
     * @param readStatus
     * @return
     */
    int updateReadStatus(Long messageId, Boolean readStatus);
}
