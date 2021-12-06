package com.pm.pmapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.pm.pmapi.common.constant.TopicFilter;
import com.pm.pmapi.dao.TopicDao;
import com.pm.pmapi.dto.TopicInfo;
import com.pm.pmapi.dto.TopicParam;
import com.pm.pmapi.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 帖子服务实现类
 *
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-12-06 09:26
 */
@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicDao topicDao;
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
     * @param topicParam
     * @return
     */
    @Override
    public TopicInfo createTopic(TopicParam topicParam) {
        // TODO 使用mapper添加
        return null;
    }
}
