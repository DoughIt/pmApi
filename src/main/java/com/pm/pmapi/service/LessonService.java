package com.pm.pmapi.service;

import com.pm.pmapi.dto.LessonInfo;

import java.util.List;

public interface LessonService {

    public LessonInfo getLessonByLessonId(Long lessonId);

    /* List<LessonInfo> listLessonsByTypeAndKey(Integer type, String key, Integer pageNum, Integer pageSize);

    List<LessonInfo> listLessonsByLessonName(String LessonName, Integer pageNum, Integer pageSize);

    List<LessonInfo> listLessonsByTeacherId(Integer teacherId, Integer pageNum, Integer pageSize); */
}
