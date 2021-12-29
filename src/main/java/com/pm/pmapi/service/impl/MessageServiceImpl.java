package com.pm.pmapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.pm.pmapi.dao.MessageDao;
import com.pm.pmapi.dto.MessageSummaryInfo;
import com.pm.pmapi.mbg.mapper.TabMessageMapper;
import com.pm.pmapi.mbg.model.TabMessage;
import com.pm.pmapi.mbg.model.TabMessageExample;
import com.pm.pmapi.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Jerry Zhang <https://github.com/doughit>
 * @Description 自定义消息服务实现类
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @date 2021-12-07 11:31
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private TabMessageMapper messageMapper;
    @Autowired
    private MessageDao messageDao;

    /**
     * 获取发给receiverId的所有消息摘要
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<MessageSummaryInfo> listMessageSummariesByUserId(Long userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return messageDao.listMessageSummariesByUserId(userId);
    }

    /**
     * 获取senderId与receiverId间的消息记录
     *
     * @param senderId
     * @param receiverId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<TabMessage> listMessagesByTwo(Long senderId, Long receiverId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TabMessageExample example = new TabMessageExample();
        // senderId 发 receiverId
        example.createCriteria().andSenderIdEqualTo(senderId).andReceiverIdEqualTo(receiverId);
        // receiverId 发 senderId
        example.or(example.createCriteria().andSenderIdEqualTo(receiverId).andReceiverIdEqualTo(senderId));
        return messageMapper.selectByExample(example);
    }

    /**
     * 添加消息记录
     *
     * @param message
     * @return
     */
    @Override
    public int createMessage(TabMessage message) {
        message.setReadStatus(false);
        message.setIssueTime(new Date());
        return messageMapper.insert(message);
    }

    /**
     * 更新消息已读状态
     *
     * @param messageId
     * @param readStatus
     * @return
     */
    @Override
    public int updateReadStatus(Long messageId, Boolean readStatus) {
        TabMessage message = messageMapper.selectByPrimaryKey(messageId);
        message.setReadStatus(readStatus);
        return messageMapper.updateByPrimaryKeySelective(message);
    }
}
