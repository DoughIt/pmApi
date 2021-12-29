package com.pm.pmapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.pm.pmapi.common.api.CommonPage;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;

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
    public CommonPage<TopicInfo>  listChildrenByParentId(Long parentId, Integer pageNum, Integer pageSize) {
        TopicInfo parent = getTopicInfoById(parentId);
        List<TopicInfo> resList = new ArrayList<>();
        resList.add(parent);
        PageHelper.startPage(pageNum, pageSize);
        List<TopicInfo> treeList = topicDao.listChildrenByParentId(parentId);
        for (TopicInfo info : treeList) {
            info.setChildren(explainChildren(info));
            resList.add(info);
        }
        treeList.add(parent);
        return CommonPage.restPage(treeList, resList);
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
    public CommonPage<TopicInfo> listTopicByFilterType(TopicFilter filter, Long relatedId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TopicInfo> treeList = topicDao.listTopicByFilterType(filter.getValue(), relatedId);
        List<TopicInfo> resList = new ArrayList<>();
        for (TopicInfo info : treeList) {
            info.setChildren(explainChildren(info));
            resList.add(info);
        }
        return CommonPage.restPage(treeList, resList);
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
    public CommonPage<TopicInfo>  listTopicByUserId(Long userId, Integer pageNum, Integer pageSize) {
        List<TopicInfo> resList = new ArrayList<>();
        PageHelper.startPage(pageNum, pageSize);
        List<TopicInfo> treeList = topicDao.listTopicByUserId(userId);
        for (TopicInfo info : treeList) {
            info.setChildren(explainChildren(info));
            resList.add(info);
        }
        return CommonPage.restPage(treeList, resList);
    }

    /**
     * 获取帖子详情
     *
     * @param id
     * @return
     */
    @Override
    public TopicInfo getTopicInfoById(Long id) {
        TopicInfo treeInfo = topicDao.getTopicInfoById(id);
        treeInfo.setChildren(explainChildren(treeInfo));
        return treeInfo;
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
        topic.setIssueTime(new Date());
        topic.setState(1);
        topicMapper.insert(topic);
        return getTopicInfoById(topic.getId());
    }

    public List<TopicInfo> explainChildren(TopicInfo parent) {
        List<TopicInfo> infoList = new ArrayList<>();
        if (parent != null) {
            for (TopicInfo info : parent.getChildren()) {
                TopicInfo info1 = new TopicInfo();
                BeanUtils.copyProperties(info, info1);
                info1.setChildren(null);
                infoList.add(info1);
                if (info.getChildren() != null && info.getChildren().size() > 0) {
                    infoList.addAll(explainChildren(info));
                }
            }
        }
        return infoList;
    }
}
