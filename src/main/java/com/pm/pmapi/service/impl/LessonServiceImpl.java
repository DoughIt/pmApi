package com.pm.pmapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.pm.pmapi.dao.LessonDao;
import com.pm.pmapi.dao.UserDao;
import com.pm.pmapi.dto.LessonInfo;
import com.pm.pmapi.mbg.mapper.*;
import com.pm.pmapi.mbg.model.*;
import com.pm.pmapi.service.LessonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    UserDao userDao;

    @Autowired
    TabUserMapper userMapper;

    @Autowired
    LessonDao lessonDao;

    @Autowired
    TabLessonMapper tabLessonMapper;

    @Autowired
    TabSchoolMapper tabSchoolMapper;

    @Autowired
    TabTeacherMapper tabTeacherMapper;

    @Autowired
    TabFavoriteLessonMapper tabFavoriteLessonMapper;

    @Autowired
    TabLessonPopularityMapper tabLessonPopularityMapper;

    @Autowired
    TabTagMapper tabTagMapper;

    @Autowired
    TabLessonTagMapper tabLessonTagMapper;


    @Override
    public LessonInfo getLessonByLessonId(Optional<Long> userId, Long lessonId) {
        if(null != lessonDao.getLessonByLessonId(lessonId)) {
            LessonInfo lessonInfo = lessonDao.getLessonByLessonId(lessonId);

            TabSchoolExample tabSchoolExample = new TabSchoolExample();
            TabSchoolExample.Criteria criteriaSchool = tabSchoolExample.createCriteria();
            criteriaSchool.andSchoolIdEqualTo(lessonInfo.getSchoolId());
            List<TabSchool> schoolList = tabSchoolMapper.selectByExample(tabSchoolExample);

            lessonInfo.setSchoolName(schoolList.get(0).getSchoolName());

            TabTeacherExample tabTeacherExample = new TabTeacherExample();
            TabTeacherExample.Criteria criteriaTeacher = tabTeacherExample.createCriteria();
            criteriaTeacher.andTeacherIdEqualTo(lessonInfo.getTeacherId());
            List<TabTeacher> teacherList = tabTeacherMapper.selectByExample(tabTeacherExample);

            lessonInfo.setTeacherName(teacherList.get(0).getTeacherName());

            if(userId.isPresent()) {
                TabFavoriteLessonExample tabFavoriteLessonExample = new TabFavoriteLessonExample();
                TabFavoriteLessonExample.Criteria criteriaFavoriteLesson = tabFavoriteLessonExample.createCriteria();
                criteriaFavoriteLesson.andUserIdEqualTo(userId.get()).andLessonIdEqualTo(lessonId);
                List<TabFavoriteLesson> favoriteLessonList = tabFavoriteLessonMapper.selectByExample(tabFavoriteLessonExample);

                lessonInfo.setCollected(!favoriteLessonList.isEmpty());
            } else {
                lessonInfo.setCollected(false);
            }

            // TODO: set pictures

            return lessonInfo;
        }

        return null;
    }

    @Override
    public List<LessonInfo> listLessonsByType(Optional<Long> userId, Integer type, Integer pageNum, Integer pageSize) {
        List<LessonInfo> rtnList = new ArrayList<>();
        PageHelper.startPage(pageNum, pageSize);

        switch (type) {
            case 1:
                Long k = new Long(pageNum * pageSize);
                if(null != tabLessonPopularityMapper.getHotK(k)) {
                    List<TabLessonPopularity> tabLessonPopularityList = tabLessonPopularityMapper.getHotK(k);

                    Iterator<TabLessonPopularity> iter = tabLessonPopularityList.iterator();
                    while(iter.hasNext()) {
                        TabLessonPopularity now = iter.next();
                        LessonInfo lessonInfo = getLessonByLessonId(userId, now.getLessonId());
                        rtnList.add(lessonInfo);
                    }
                }
                break;
        }
        return rtnList;
    }

    @Override
    public List<LessonInfo> listLessonsByTypeAndKey(Optional<Long> userId, Integer type, String key, Integer pageNum, Integer pageSize) {
        List<LessonInfo> rtnList = new ArrayList<>();
        PageHelper.startPage(pageNum, pageSize);

        switch (type) {
            case 2:
                // select by lesson name
                TabLessonExample tabLessonExample = new TabLessonExample();
                TabLessonExample.Criteria criteriaLesson = tabLessonExample.createCriteria();
                criteriaLesson.andLessonNameLike('%' + key + '%');
                List<TabLesson> lessonList;
                Iterator<TabLesson> iter;
                if(null != tabLessonMapper.selectByExample(tabLessonExample)) {
                    lessonList = tabLessonMapper.selectByExample(tabLessonExample);

                    iter = lessonList.iterator();
                    while(iter.hasNext()) {
                        TabLesson now = iter.next();
                        LessonInfo lessonInfo = getLessonByLessonId(userId, now.getLessonId());
                        rtnList.add(lessonInfo);
                    }
                }

                // select by lesson number
                tabLessonExample = new TabLessonExample();
                criteriaLesson = tabLessonExample.createCriteria();
                criteriaLesson.andLessonNumberLike('%' + key + '%');
                if(null != tabLessonMapper.selectByExample(tabLessonExample)) {
                    lessonList = tabLessonMapper.selectByExample(tabLessonExample);

                    iter = lessonList.iterator();
                    while(iter.hasNext()) {
                        TabLesson now = iter.next();
                        LessonInfo lessonInfo = getLessonByLessonId(userId, now.getLessonId());
                        rtnList.add(lessonInfo);
                    }
                }

                // select by semester
                tabLessonExample = new TabLessonExample();
                criteriaLesson = tabLessonExample.createCriteria();
                criteriaLesson.andSemesterLike('%' + key + '%');
                if(null != tabLessonMapper.selectByExample(tabLessonExample)) {
                    lessonList = tabLessonMapper.selectByExample(tabLessonExample);

                    iter = lessonList.iterator();
                    while(iter.hasNext()) {
                        TabLesson now = iter.next();
                        LessonInfo lessonInfo = getLessonByLessonId(userId, now.getLessonId());
                        rtnList.add(lessonInfo);
                    }
                }

                // select by school
                TabSchoolExample tabSchoolExample = new TabSchoolExample();
                TabSchoolExample.Criteria criteriaSchool = tabSchoolExample.createCriteria();
                criteriaSchool.andSchoolNameLike('%' + key + '%');
                if(null != tabSchoolMapper.selectByExample(tabSchoolExample)) {
                    List<TabSchool> schoolList = tabSchoolMapper.selectByExample(tabSchoolExample);

                    List<Long> schoolIdList = new ArrayList<>();
                    Iterator<TabSchool> schoolIter = schoolList.iterator();
                    while(schoolIter.hasNext()) {
                        TabSchool nowSchool = schoolIter.next();
                        schoolIdList.add(nowSchool.getSchoolId());
                    }

                    Iterator<Long> schoolIdIter = schoolIdList.iterator();
                    while(schoolIdIter.hasNext()) {
                        Long nowSchoolId = schoolIdIter.next();

                        tabLessonExample = new TabLessonExample();
                        criteriaLesson = tabLessonExample.createCriteria();
                        criteriaLesson.andSchoolIdEqualTo(nowSchoolId);
                        if(null != tabLessonMapper.selectByExample(tabLessonExample)) {
                            lessonList = tabLessonMapper.selectByExample(tabLessonExample);

                            iter = lessonList.iterator();
                            while(iter.hasNext()) {
                                TabLesson now = iter.next();
                                LessonInfo lessonInfo = getLessonByLessonId(userId, now.getLessonId());
                                rtnList.add(lessonInfo);
                            }
                        }
                    }
                }

                // select by school
                TabTeacherExample tabTeacherExample = new TabTeacherExample();
                TabTeacherExample.Criteria criteriaTeacher = tabTeacherExample.createCriteria();
                criteriaTeacher.andTeacherNameLike('%' + key + '%');
                if(null != tabTeacherMapper.selectByExample(tabTeacherExample)) {
                    List<TabTeacher> teacherList = tabTeacherMapper.selectByExample(tabTeacherExample);

                    List<Long> teacherIdList = new ArrayList<>();
                    Iterator<TabTeacher> teacherIter = teacherList.iterator();
                    while(teacherIter.hasNext()) {
                        TabTeacher nowTeacher = teacherIter.next();
                        teacherIdList.add(nowTeacher.getTeacherId());
                    }

                    Iterator<Long> teacherIdIter = teacherIdList.iterator();
                    while(teacherIdIter.hasNext()) {
                        Long nowTeacherId = teacherIdIter.next();

                        tabLessonExample = new TabLessonExample();
                        criteriaLesson = tabLessonExample.createCriteria();
                        criteriaLesson.andTeacherIdEqualTo(nowTeacherId);
                        if(null != tabLessonMapper.selectByExample(tabLessonExample)) {
                            lessonList = tabLessonMapper.selectByExample(tabLessonExample);

                            iter = lessonList.iterator();
                            while(iter.hasNext()) {
                                TabLesson now = iter.next();
                                LessonInfo lessonInfo = getLessonByLessonId(userId, now.getLessonId());
                                rtnList.add(lessonInfo);
                            }
                        }
                    }
                }

                // select by tag
                TabTagExample tabTagExample = new TabTagExample();
                TabTagExample.Criteria criteriaTag = tabTagExample.createCriteria();
                criteriaTag.andTagLike('%' + key + '%');
                if(null != tabTagMapper.selectByExample(tabTagExample)) {
                    List<TabTag> tagList = tabTagMapper.selectByExample(tabTagExample);

                    Iterator<TabTag> tagIter = tagList.iterator();
                    while(tagIter.hasNext()) {
                        TabTag nowTag = tagIter.next();
                        TabLessonTagExample tabLessonTagExample = new TabLessonTagExample();
                        TabLessonTagExample.Criteria criteriaLessonTag = tabLessonTagExample.createCriteria();
                        criteriaLessonTag.andTagIdEqualTo(nowTag.getTagId());
                        if(null != tabLessonTagMapper.selectByExample(tabLessonTagExample)) {
                            List<TabLessonTag> lessonTagList = tabLessonTagMapper.selectByExample(tabLessonTagExample);

                            Iterator<TabLessonTag> lessonTagIter = lessonTagList.iterator();
                            while(lessonTagIter.hasNext()) {
                                TabLessonTag now = lessonTagIter.next();
                                LessonInfo lessonInfo = getLessonByLessonId(userId, now.getLessonId());
                                rtnList.add(lessonInfo);
                            }
                        }
                    }
                }
                break;

        }
        return rtnList;
    }
}
