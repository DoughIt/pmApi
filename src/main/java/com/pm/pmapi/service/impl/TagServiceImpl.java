package com.pm.pmapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.pm.pmapi.dao.TagDao;
import com.pm.pmapi.dto.TagInfo;
import com.pm.pmapi.mbg.mapper.TabLessonMapper;
import com.pm.pmapi.mbg.mapper.TabLessonTagMapper;
import com.pm.pmapi.mbg.mapper.TabLessonUserTagMapper;
import com.pm.pmapi.mbg.mapper.TabTagMapper;
import com.pm.pmapi.mbg.model.*;
import com.pm.pmapi.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagDao tagDao;

    @Autowired
    TabTagMapper tabTagMapper;

    @Autowired
    TabLessonTagMapper tabLessonTagMapper;

    @Autowired
    TabLessonUserTagMapper tabLessonUserTagMapper;

    @Autowired
    TabLessonMapper tabLessonMapper;

    @Override
    public Integer createTag(Optional<Long> userId, String lessonNumber, String tagName, Boolean isPositive) {
        TabLessonExample tabLessonExample = new TabLessonExample();
        TabLessonExample.Criteria criteriaLesson = tabLessonExample.createCriteria();
        criteriaLesson.andLessonNumberEqualTo(lessonNumber);

        // if lesson number / userId is not exist
        if(!userId.isPresent() || null == tabLessonMapper.selectByExample(tabLessonExample) || tabLessonMapper.selectByExample(tabLessonExample).size() == 0) {
            return -1;
        }

        Long lessonId = tabLessonMapper.selectByExample(tabLessonExample).get(0).getLessonId();

        TabTagExample tabTagExample = new TabTagExample();
        TabTagExample.Criteria criteriaTag = tabTagExample.createCriteria();
        criteriaTag.andTagEqualTo(tagName);
        TabTag tabTag;


        if(null == tabTagMapper.selectByExample(tabTagExample) ||  tabTagMapper.selectByExample(tabTagExample).size() == 0) {
            // create new tag
            tabTag = new TabTag();
            tabTag.setTag(tagName);
            tabTagMapper.insertSelective(tabTag);

            tabTag = tabTagMapper.selectByExample(tabTagExample).get(0);
        } else {
            tabTag = tabTagMapper.selectByExample(tabTagExample).get(0);

            TabLessonTagExample tabLessonTagExample = new TabLessonTagExample();
            TabLessonTagExample.Criteria criteriaLessonTag = tabLessonTagExample.createCriteria();
            criteriaLessonTag.andLessonIdEqualTo(lessonId).andTagIdEqualTo(tabTag.getTagId());

            // if lesson tag exist
            if(tabLessonTagMapper.selectByExample(tabLessonTagExample).size() > 0) {
                return 0;
            }
        }

        // create new lesson tag
        TabLessonTag tabLessonTag = new TabLessonTag();
        tabLessonTag.setTagId(tabTag.getTagId());
        tabLessonTag.setLessonId(lessonId);
        if(isPositive) {
            tabLessonTag.setPositive((long) 1);
            tabLessonTag.setNegative((long) 0);
        } else {
            tabLessonTag.setPositive((long) 0);
            tabLessonTag.setNegative((long) 1);
        }
        tabLessonTagMapper.insert(tabLessonTag);

        // create new user lesson tag
        TabLessonUserTag tabLessonUserTag = new TabLessonUserTag();
        tabLessonUserTag.setLessonId(lessonId);
        tabLessonUserTag.setUserId(userId.get());
        tabLessonUserTag.setTagId(tabTag.getTagId());
        if(isPositive) {
            tabLessonUserTag.setPositiveselected(true);
            tabLessonUserTag.setNegetiveselected(false);
        } else {
            tabLessonUserTag.setPositiveselected(false);
            tabLessonUserTag.setNegetiveselected(true);
        }
        tabLessonUserTagMapper.insert(tabLessonUserTag);

        return 1;
    }

    @Override
    public TagInfo getTagByLessonIdAndTagName(Optional<Long> userId, Long lessonId, String tagName) {
        TabLessonExample tabLessonExample = new TabLessonExample();
        TabLessonExample.Criteria criteriaLesson = tabLessonExample.createCriteria();
        criteriaLesson.andLessonIdEqualTo(lessonId);

        // if lessonId is not exist
        if(null == tabLessonMapper.selectByExample(tabLessonExample) || tabLessonMapper.selectByExample(tabLessonExample).size() == 0) {
            return null;
        }

        TabTagExample tabTagExample = new TabTagExample();
        TabTagExample.Criteria criteriaTag = tabTagExample.createCriteria();
        criteriaTag.andTagEqualTo(tagName);

        // if tag is not exist
        if(null == tabTagMapper.selectByExample(tabTagExample) ||  tabTagMapper.selectByExample(tabTagExample).size() == 0) {
            return null;
        }

        Long tagId = tabTagMapper.selectByExample(tabTagExample).get(0).getTagId();

        TabLessonTagExample tabLessonTagExample = new TabLessonTagExample();
        TabLessonTagExample.Criteria criteriaLessonTag = tabLessonTagExample.createCriteria();
        criteriaLessonTag.andLessonIdEqualTo(lessonId).andTagIdEqualTo(tagId);

        // if lesson tag is not exist
        if(null == tabLessonTagMapper.selectByExample(tabLessonTagExample) ||  tabLessonTagMapper.selectByExample(tabLessonTagExample).size() == 0) {
            return null;
        }

        TabLessonTag tabLessonTag = tabLessonTagMapper.selectByExample(tabLessonTagExample).get(0);

        TagInfo tagInfo = new TagInfo();
        tagInfo.setTagId(tagId);
        tagInfo.setTagName(tagName);
        tagInfo.setLessonId(lessonId);
        tagInfo.setPositive(tabLessonTag.getPositive());
        tagInfo.setNegative(tabLessonTag.getNegative());

        // set user evaluation
        if(userId.isPresent()) {
            TabLessonUserTagExample tabLessonUserTagExample = new TabLessonUserTagExample();
            TabLessonUserTagExample.Criteria criteriaLessonUserTag = tabLessonUserTagExample.createCriteria();
            criteriaLessonUserTag.andTagIdEqualTo(tagId).andLessonIdEqualTo(lessonId).andUserIdEqualTo(userId.get());

            if(null == tabLessonUserTagMapper.selectByExample(tabLessonUserTagExample) || tabLessonUserTagMapper.selectByExample(tabLessonUserTagExample).size() == 0) {
                tagInfo.setPositiveSelected(false);
                tagInfo.setNegativeSelected(false);
            } else {
                List<TabLessonUserTag> lessonUserTagList = tabLessonUserTagMapper.selectByExample(tabLessonUserTagExample);
                tagInfo.setPositiveSelected(lessonUserTagList.get(0).getPositiveselected());
                tagInfo.setNegativeSelected(lessonUserTagList.get(0).getNegetiveselected());
            }
        } else {
            tagInfo.setPositiveSelected(false);
            tagInfo.setNegativeSelected(false);
        }

        return tagInfo;
    }

    @Override
    public List<TagInfo> listTagsOfLessonByLessonId(Optional<Long> userId, Long lessonId, Integer pageNum, Integer pageSize) {
        List<TagInfo> tagInfoList = new ArrayList<>();
        PageHelper.startPage(pageNum, pageSize);

        if(null != tagDao.listTagInfoByTLessonId(lessonId) && tagDao.listTagInfoByTLessonId(lessonId).size() > 0) {
            List<TagInfo> tmpTagInfoList = tagDao.listTagInfoByTLessonId(lessonId);

            Iterator<TagInfo> iter = tmpTagInfoList.iterator();
            while(iter.hasNext()) {
                TagInfo now = iter.next();

                // get tag name
                TabTagExample tabTagExample = new TabTagExample();
                TabTagExample.Criteria criteriaTag = tabTagExample.createCriteria();
                criteriaTag.andTagIdEqualTo(now.getTagId());
                List<TabTag> tagList = tabTagMapper.selectByExample(tabTagExample);

                now.setTagName(tagList.get(0).getTag());

                // get user evaluation
                if(userId.isPresent()) {
                    TabLessonUserTagExample tabLessonUserTagExample = new TabLessonUserTagExample();
                    TabLessonUserTagExample.Criteria criteriaLessonUserTag = tabLessonUserTagExample.createCriteria();
                    criteriaLessonUserTag.andTagIdEqualTo(now.getTagId()).andLessonIdEqualTo(lessonId).andUserIdEqualTo(userId.get());
                    List<TabLessonUserTag> lessonUserTagList = tabLessonUserTagMapper.selectByExample(tabLessonUserTagExample);

                    now.setPositiveSelected(lessonUserTagList.get(0).getPositiveselected());
                    now.setNegativeSelected(lessonUserTagList.get(0).getNegetiveselected());
                } else {
                    now.setPositiveSelected(false);
                    now.setNegativeSelected(false);
                }

                tagInfoList.add(now);
            }
        }

        return tagInfoList;
    }

    @Override
    public Boolean evaluateTag(Optional<Long> userId, Long lessonId, String tagName, Integer evaluate) {
        TabLessonExample tabLessonExample = new TabLessonExample();
        TabLessonExample.Criteria criteriaLesson = tabLessonExample.createCriteria();
        criteriaLesson.andLessonIdEqualTo(lessonId);

        // if lessonId / userId is not exist
        if(!userId.isPresent() || null == tabLessonMapper.selectByExample(tabLessonExample) || tabLessonMapper.selectByExample(tabLessonExample).size() == 0) {
            return false;
        }

        TabTagExample tabTagExample = new TabTagExample();
        TabTagExample.Criteria criteriaTag = tabTagExample.createCriteria();
        criteriaTag.andTagEqualTo(tagName);

        // if tag is not exist
        if(null == tabTagMapper.selectByExample(tabTagExample) ||  tabTagMapper.selectByExample(tabTagExample).size() == 0) {
            return false;
        }

        Long tagId = tabTagMapper.selectByExample(tabTagExample).get(0).getTagId();

        TabLessonTagExample tabLessonTagExample = new TabLessonTagExample();
        TabLessonTagExample.Criteria criteriaLessonTag = tabLessonTagExample.createCriteria();
        criteriaLessonTag.andLessonIdEqualTo(lessonId).andTagIdEqualTo(tagId);

        // if lesson tag is not exist
        if(null == tabLessonTagMapper.selectByExample(tabLessonTagExample) ||  tabLessonTagMapper.selectByExample(tabLessonTagExample).size() == 0) {
            return false;
        }

        TabLessonTag tabLessonTag = tabLessonTagMapper.selectByExample(tabLessonTagExample).get(0);
        Long positive = tabLessonTag.getPositive();
        Long negative = tabLessonTag.getNegative();

        TabLessonUserTagExample tabLessonUserTagExample = new TabLessonUserTagExample();
        TabLessonUserTagExample.Criteria criteriaLessonUserTag = tabLessonUserTagExample.createCriteria();
        criteriaLessonUserTag.andTagIdEqualTo(tagId).andLessonIdEqualTo(lessonId).andUserIdEqualTo(userId.get());
        if(null == tabLessonUserTagMapper.selectByExample(tabLessonUserTagExample) || tabLessonUserTagMapper.selectByExample(tabLessonUserTagExample).size() == 0) {
            // create new user lesson tag
            TabLessonUserTag tabLessonUserTag = new TabLessonUserTag();
            tabLessonUserTag.setLessonId(lessonId);
            tabLessonUserTag.setUserId(userId.get());
            tabLessonUserTag.setTagId(tagId);
            if(evaluate == 1) {
                tabLessonUserTag.setPositiveselected(true);
                tabLessonUserTag.setNegetiveselected(false);
                positive++;
                tabLessonTag.setPositive(positive);
            } else {
                tabLessonUserTag.setPositiveselected(false);
                tabLessonUserTag.setNegetiveselected(true);
                negative++;
                tabLessonTag.setNegative(negative);
            }
            tabLessonUserTagMapper.insert(tabLessonUserTag);
            tabLessonTagMapper.updateByPrimaryKey(tabLessonTag);
        } else {
            TabLessonUserTag tabLessonUserTag = tabLessonUserTagMapper.selectByExample(tabLessonUserTagExample).get(0);
            Boolean positiveSelected = tabLessonUserTag.getPositiveselected();
            Boolean negativeSelected = tabLessonUserTag.getNegetiveselected();

            if(evaluate == 1) {
                if(positiveSelected) {
                    tabLessonUserTag.setPositiveselected(false);
                    positive--;
                } else {
                    tabLessonUserTag.setPositiveselected(true);
                    positive++;
                }
                tabLessonTag.setPositive(positive);
            } else {
                if(negativeSelected) {
                    tabLessonUserTag.setNegetiveselected(false);
                    negative--;
                } else {
                    tabLessonUserTag.setNegetiveselected(true);
                    negative++;
                }
                tabLessonTag.setNegative(negative);
            }
            tabLessonUserTagMapper.updateByPrimaryKey(tabLessonUserTag);
            tabLessonTagMapper.updateByPrimaryKey(tabLessonTag);
        }

        return true;
    }
}
