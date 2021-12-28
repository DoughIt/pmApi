package com.pm.pmapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.pm.pmapi.dao.LessonDao;
import com.pm.pmapi.dao.LessonPopularityDao;
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
    LessonDao lessonDao;

    @Autowired
    LessonPopularityDao lessonPopularityDao;

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
        if (null != lessonDao.getLessonByLessonId(lessonId)) {
            LessonInfo lessonInfo = lessonDao.getLessonByLessonId(lessonId);

            // get school name
            TabSchoolExample tabSchoolExample = new TabSchoolExample();
            TabSchoolExample.Criteria criteriaSchool = tabSchoolExample.createCriteria();
            criteriaSchool.andSchoolIdEqualTo(lessonInfo.getSchoolId());
            List<TabSchool> schoolList = tabSchoolMapper.selectByExample(tabSchoolExample);

            lessonInfo.setSchoolName(schoolList.get(0).getSchoolName());

            // get teacher name
            TabTeacherExample tabTeacherExample = new TabTeacherExample();
            TabTeacherExample.Criteria criteriaTeacher = tabTeacherExample.createCriteria();
            criteriaTeacher.andTeacherIdEqualTo(lessonInfo.getTeacherId());
            List<TabTeacher> teacherList = tabTeacherMapper.selectByExample(tabTeacherExample);

            lessonInfo.setTeacherName(teacherList.get(0).getTeacherName());

            // get collected
            if (userId.isPresent()) {
                TabFavoriteLessonExample tabFavoriteLessonExample = new TabFavoriteLessonExample();
                TabFavoriteLessonExample.Criteria criteriaFavoriteLesson = tabFavoriteLessonExample.createCriteria();
                criteriaFavoriteLesson.andUserIdEqualTo(userId.get()).andLessonIdEqualTo(lessonId);
                List<TabFavoriteLesson> favoriteLessonList = tabFavoriteLessonMapper.selectByExample(tabFavoriteLessonExample);

                lessonInfo.setCollected(!favoriteLessonList.isEmpty());
            } else {
                lessonInfo.setCollected(false);
            }

            List<String> pictures = new ArrayList<>();
            pictures.add("https://doughit.oss-cn-shanghai.aliyuncs.com/bbs/images/20211228/defaultLesson.jpeg");
            lessonInfo.setPictures(pictures);

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
                List<TabLessonPopularity> tabLessonPopularityList = tabLessonPopularityMapper.selectByExample(new TabLessonPopularityExample());
                Iterator<TabLessonPopularity> iter = tabLessonPopularityList.iterator();
                while (iter.hasNext()) {
                    TabLessonPopularity now = iter.next();
                    LessonInfo lessonInfo = getLessonByLessonId(userId, now.getLessonId());
                    rtnList.add(lessonInfo);
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
                if (null != tabLessonMapper.selectByExample(tabLessonExample) && tabLessonMapper.selectByExample(tabLessonExample).size() > 0) {
                    lessonList = tabLessonMapper.selectByExample(tabLessonExample);

                    iter = lessonList.iterator();
                    while (iter.hasNext()) {
                        TabLesson now = iter.next();
                        LessonInfo lessonInfo = getLessonByLessonId(userId, now.getLessonId());
                        rtnList.add(lessonInfo);
                    }
                }

                // select by lesson number
                tabLessonExample = new TabLessonExample();
                criteriaLesson = tabLessonExample.createCriteria();
                criteriaLesson.andLessonNumberLike('%' + key + '%');
                if (null != tabLessonMapper.selectByExample(tabLessonExample) && tabLessonMapper.selectByExample(tabLessonExample).size() > 0) {
                    lessonList = tabLessonMapper.selectByExample(tabLessonExample);

                    iter = lessonList.iterator();
                    while (iter.hasNext()) {
                        TabLesson now = iter.next();
                        LessonInfo lessonInfo = getLessonByLessonId(userId, now.getLessonId());
                        rtnList.add(lessonInfo);
                    }
                }

                // select by semester
                tabLessonExample = new TabLessonExample();
                criteriaLesson = tabLessonExample.createCriteria();
                criteriaLesson.andSemesterLike('%' + key + '%');
                if (null != tabLessonMapper.selectByExample(tabLessonExample) && tabLessonMapper.selectByExample(tabLessonExample).size() > 0) {
                    lessonList = tabLessonMapper.selectByExample(tabLessonExample);

                    iter = lessonList.iterator();
                    while (iter.hasNext()) {
                        TabLesson now = iter.next();
                        LessonInfo lessonInfo = getLessonByLessonId(userId, now.getLessonId());
                        rtnList.add(lessonInfo);
                    }
                }

                // select by school
                TabSchoolExample tabSchoolExample = new TabSchoolExample();
                TabSchoolExample.Criteria criteriaSchool = tabSchoolExample.createCriteria();
                criteriaSchool.andSchoolNameLike('%' + key + '%');
                if (null != tabSchoolMapper.selectByExample(tabSchoolExample) && tabSchoolMapper.selectByExample(tabSchoolExample).size() > 0) {
                    List<TabSchool> schoolList = tabSchoolMapper.selectByExample(tabSchoolExample);

                    List<Long> schoolIdList = new ArrayList<>();
                    Iterator<TabSchool> schoolIter = schoolList.iterator();
                    while (schoolIter.hasNext()) {
                        TabSchool nowSchool = schoolIter.next();
                        schoolIdList.add(nowSchool.getSchoolId());
                    }

                    Iterator<Long> schoolIdIter = schoolIdList.iterator();
                    while (schoolIdIter.hasNext()) {
                        Long nowSchoolId = schoolIdIter.next();

                        tabLessonExample = new TabLessonExample();
                        criteriaLesson = tabLessonExample.createCriteria();
                        criteriaLesson.andSchoolIdEqualTo(nowSchoolId);
                        if (null != tabLessonMapper.selectByExample(tabLessonExample) && tabLessonMapper.selectByExample(tabLessonExample).size() > 0) {
                            lessonList = tabLessonMapper.selectByExample(tabLessonExample);

                            iter = lessonList.iterator();
                            while (iter.hasNext()) {
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
                if (null != tabTeacherMapper.selectByExample(tabTeacherExample) && tabTeacherMapper.selectByExample(tabTeacherExample).size() > 0) {
                    List<TabTeacher> teacherList = tabTeacherMapper.selectByExample(tabTeacherExample);

                    List<Long> teacherIdList = new ArrayList<>();
                    Iterator<TabTeacher> teacherIter = teacherList.iterator();
                    while (teacherIter.hasNext()) {
                        TabTeacher nowTeacher = teacherIter.next();
                        teacherIdList.add(nowTeacher.getTeacherId());
                    }

                    Iterator<Long> teacherIdIter = teacherIdList.iterator();
                    while (teacherIdIter.hasNext()) {
                        Long nowTeacherId = teacherIdIter.next();

                        tabLessonExample = new TabLessonExample();
                        criteriaLesson = tabLessonExample.createCriteria();
                        criteriaLesson.andTeacherIdEqualTo(nowTeacherId);
                        if (null != tabLessonMapper.selectByExample(tabLessonExample) && tabLessonMapper.selectByExample(tabLessonExample).size() > 0) {
                            lessonList = tabLessonMapper.selectByExample(tabLessonExample);

                            iter = lessonList.iterator();
                            while (iter.hasNext()) {
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
                if (null != tabTagMapper.selectByExample(tabTagExample) && tabTagMapper.selectByExample(tabTagExample).size() > 0) {
                    List<TabTag> tagList = tabTagMapper.selectByExample(tabTagExample);

                    Iterator<TabTag> tagIter = tagList.iterator();
                    while (tagIter.hasNext()) {
                        TabTag nowTag = tagIter.next();
                        TabLessonTagExample tabLessonTagExample = new TabLessonTagExample();
                        TabLessonTagExample.Criteria criteriaLessonTag = tabLessonTagExample.createCriteria();
                        criteriaLessonTag.andTagIdEqualTo(nowTag.getTagId());
                        if (null != tabLessonTagMapper.selectByExample(tabLessonTagExample) && tabLessonMapper.selectByExample(tabLessonExample).size() > 0) {
                            List<TabLessonTag> lessonTagList = tabLessonTagMapper.selectByExample(tabLessonTagExample);

                            Iterator<TabLessonTag> lessonTagIter = lessonTagList.iterator();
                            while (lessonTagIter.hasNext()) {
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

    @Override
    public List<LessonInfo> listFavoriteLessons(Optional<Long> userId) {
        List<LessonInfo> rtnList = new ArrayList<>();

        TabFavoriteLessonExample tabFavoriteLessonExample = new TabFavoriteLessonExample();
        TabFavoriteLessonExample.Criteria criteriaFavoriteLesson = tabFavoriteLessonExample.createCriteria();
        criteriaFavoriteLesson.andUserIdEqualTo(userId.get());
        if (null != tabFavoriteLessonMapper.selectByExample(tabFavoriteLessonExample) && tabFavoriteLessonMapper.selectByExample(tabFavoriteLessonExample).size() > 0) {
            List<TabFavoriteLesson> tabFavoriteLessonList = tabFavoriteLessonMapper.selectByExample(tabFavoriteLessonExample);

            Iterator<TabFavoriteLesson> iter = tabFavoriteLessonList.iterator();
            while (iter.hasNext()) {
                TabFavoriteLesson now = iter.next();
                LessonInfo lessonInfo = getLessonByLessonId(userId, now.getLessonId());
                rtnList.add(lessonInfo);
            }
        }

        return rtnList;
    }

    @Override
    public Boolean addFavoriteLesson(Optional<Long> userId, Long lessonId) {
        TabFavoriteLessonExample tabFavoriteLessonExample = new TabFavoriteLessonExample();
        TabFavoriteLessonExample.Criteria criteriaFavoriteLesson = tabFavoriteLessonExample.createCriteria();
        criteriaFavoriteLesson.andUserIdEqualTo(userId.get()).andLessonIdEqualTo(lessonId);
        if (null != tabFavoriteLessonMapper.selectByExample(tabFavoriteLessonExample) && tabFavoriteLessonMapper.selectByExample(tabFavoriteLessonExample).size() > 0) {
            return false;
        }

        TabFavoriteLesson tabFavoriteLesson = new TabFavoriteLesson();
        tabFavoriteLesson.setUserId(userId.get());
        tabFavoriteLesson.setLessonId(lessonId);

        tabFavoriteLessonMapper.insert(tabFavoriteLesson);

        // add popularity
        TabLessonPopularityExample tabLessonPopularityExample = new TabLessonPopularityExample();
        TabLessonPopularityExample.Criteria criteriaLessonPopularity = tabLessonPopularityExample.createCriteria();
        criteriaLessonPopularity.andLessonIdEqualTo(lessonId);

        TabLessonPopularity tabLessonPopularity;
        Long popularity = Long.valueOf(0);

        if (null == tabLessonPopularityMapper.selectByExample(tabLessonPopularityExample) || tabLessonPopularityMapper.selectByExample(tabLessonPopularityExample).size() == 0) {
            tabLessonPopularity = new TabLessonPopularity();
            tabLessonPopularity.setLessonId(lessonId);

            popularity++;
            tabLessonPopularity.setPopularity(popularity);

            tabLessonPopularityMapper.insert(tabLessonPopularity);
        } else {
            tabLessonPopularity = tabLessonPopularityMapper.selectByExample(tabLessonPopularityExample).get(0);
            popularity = tabLessonPopularity.getPopularity();

            popularity++;
            tabLessonPopularity.setPopularity(popularity);

            tabLessonPopularityMapper.updateByPrimaryKey(tabLessonPopularity);
        }

        return true;
    }

    @Override
    public Boolean deleteFavoriteLesson(Optional<Long> userId, Long lessonId) {
        TabFavoriteLessonExample tabFavoriteLessonExample = new TabFavoriteLessonExample();
        TabFavoriteLessonExample.Criteria criteriaFavoriteLesson = tabFavoriteLessonExample.createCriteria();
        criteriaFavoriteLesson.andUserIdEqualTo(userId.get()).andLessonIdEqualTo(lessonId);
        if (null == tabFavoriteLessonMapper.selectByExample(tabFavoriteLessonExample) || tabFavoriteLessonMapper.selectByExample(tabFavoriteLessonExample).size() == 0) {
            return false;
        }

        TabFavoriteLesson tabFavoriteLesson = tabFavoriteLessonMapper.selectByExample(tabFavoriteLessonExample).get(0);
        Long delId = tabFavoriteLesson.getId();

        tabFavoriteLessonMapper.deleteByPrimaryKey(delId);

        // delete popularity
        TabLessonPopularityExample tabLessonPopularityExample = new TabLessonPopularityExample();
        TabLessonPopularityExample.Criteria criteriaLessonPopularity = tabLessonPopularityExample.createCriteria();
        criteriaLessonPopularity.andLessonIdEqualTo(lessonId);

        TabLessonPopularity tabLessonPopularity = tabLessonPopularityMapper.selectByExample(tabLessonPopularityExample).get(0);
        Long popularity = tabLessonPopularity.getPopularity();
        popularity--;
        tabLessonPopularity.setPopularity(popularity);
        tabLessonPopularityMapper.updateByPrimaryKey(tabLessonPopularity);

        return true;
    }
}
