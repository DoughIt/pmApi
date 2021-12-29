package com.pm.pmapi.service;

import com.pm.pmapi.common.api.CommonPage;
import com.pm.pmapi.common.constant.TopicFilter;
import com.pm.pmapi.dto.TopicInfo;
import com.pm.pmapi.dto.TopicParam;

import java.util.List;

/**
 * @author Jerry Zhang <https://github.com/doughit>
 * @Description 帖子服务
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @date 2021-12-06 09:07
 */
public interface TopicService {
    /**
     * 获取所有回帖
     * @param parentId 被回复的帖子id
     * @param pageNum
     * @param pageSize
     * @return
     */
    CommonPage<TopicInfo> listChildrenByParentId(Long parentId, Integer pageNum, Integer pageSize);

    /**
     * 获取所有帖子
     * @param filter 筛选分类，课程帖子、商品帖子、不分类
     * @param relatedId 若filter为课程帖子，则为lessonId；若filter为商品帖子，则为goodsId；否则为空
     * @param pageNum
     * @param pageSize
     * @return
     */
    CommonPage<TopicInfo>  listTopicByFilterType(TopicFilter filter, Long relatedId, Integer pageNum, Integer pageSize);

    /**
     * 获取userId发布的所有帖子
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    CommonPage<TopicInfo>  listTopicByUserId(Long userId, Integer pageNum, Integer pageSize);

    /**
     * 获取帖子详情
     * @param id
     * @return
     */
    TopicInfo getTopicInfoById(Long id);

    /**
     * 发布帖子
     * @param userId
     * @param topicParam
     * @return
     */
    TopicInfo createTopic(Long userId, TopicParam topicParam);
}
