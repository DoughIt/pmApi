package com.pm.pmapi.dao;

import com.pm.pmapi.dto.TopicInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jerry Zhang <https://github.com/doughit>
 * @Description 自定义帖子Dao
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @date 2021-12-06 09:00
 */
public interface TopicDao {

    /**
     * 获取回帖信息
     *
     * @param parentId
     * @return
     */
    List<TopicInfo> listChildrenByParentId(@Param("parentId") Long parentId);

    /**
     * 按分类获取帖子列表
     *
     * @param filter
     * @param relatedId
     * @return
     */
    List<TopicInfo> listTopicByFilterType(@Param("filter") Integer filter, @Param("relatedId") Long relatedId);


    /**
     * 获取userId发布的所有帖子
     *
     * @param userId
     * @return
     */
    List<TopicInfo> listTopicByUserId(@Param("userId") Long userId);

    /**
     * 获取帖子详情
     *
     * @param id
     * @return
     */
    TopicInfo getTopicInfoById(@Param("id") Long id);
}
