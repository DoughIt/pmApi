package com.pm.pmapi.service.impl;

import com.pm.pmapi.dao.LessonDao;
import com.pm.pmapi.dao.UserDao;
import com.pm.pmapi.dto.LessonInfo;
import com.pm.pmapi.mbg.mapper.TabLessonMapper;
import com.pm.pmapi.mbg.mapper.TabUserMapper;
import com.pm.pmapi.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public LessonInfo getLessonByLessonId(Long lessonId) {
        LessonInfo lessonInfo = lessonDao.getLessonByLessonId(lessonId);
        return lessonInfo;
    }
}
