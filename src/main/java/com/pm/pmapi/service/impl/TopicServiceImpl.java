package com.pm.pmapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.pm.pmapi.common.constant.TopicFilter;
import com.pm.pmapi.dao.TopicDao;
import com.pm.pmapi.dao.UserDao;
import com.pm.pmapi.dto.SimpleUserInfo;
import com.pm.pmapi.dto.TopicInfo;
import com.pm.pmapi.dto.TopicParam;
import com.pm.pmapi.mbg.mapper.TabTopicMapper;
import com.pm.pmapi.mbg.mapper.TabUserMapper;
import com.pm.pmapi.mbg.model.TabTopic;
import com.pm.pmapi.mbg.model.TabUser;
import com.pm.pmapi.service.TopicService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jerry Zhang <https://github.com/doughit>
 * @Description 帖子服务实现类
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @date 2021-12-06 09:26
 */
@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicDao topicDao;
    @Autowired
    private TabTopicMapper topicMapper;

    /**
     * 获取所有回帖
     *
     * @param parentId
     * @param pageSize
     * @param pageNum
     * @return
     */
    @Override
    public List<TopicInfo> listChildrenByParentId(Long parentId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return topicDao.listChildrenByParentId(parentId);
    }

    /**
     * 获取所有帖子
     *
     * @param filter    筛选分类，课程帖子、商品帖子、不分类
     * @param relatedId 若filter为课程帖子，则为lessonId；若filter为商品帖子，则为goodsId；否则为空
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<TopicInfo> listTopicByFilterType(TopicFilter filter, Long relatedId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return topicDao.listTopicByFilterType(filter.getValue(), relatedId);
    }

    /**
     * 获取userId发布的所有帖子
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<TopicInfo> listTopicByUserId(Long userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return topicDao.listTopicByUserId(userId);
    }

    /**
     * 获取帖子详情
     *
     * @param id
     * @return
     */
    @Override
    public TopicInfo getTopicInfoById(Long id) {
        return topicDao.getTopicInfoById(id);
    }

    /**
     * 发布帖子
     *
     * @param userId
     * @param topicParam
     * @return
     */
    @Override
    public TopicInfo createTopic(Long userId, TopicParam topicParam) {
        TabTopic topic = new TabTopic();
        BeanUtils.copyProperties(topicParam, topic);
        topic.setParentId(topicParam.getTopicId());
        topic.setUserId(userId);
        topicMapper.insert(topic);
        return getTopicInfoById(topic.getId());
    }
}
