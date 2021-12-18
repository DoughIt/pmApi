package com.pm.pmapi.service;

import com.pm.pmapi.dto.LessonInfo;

import java.util.List;
import java.util.Optional;

public interface LessonService {

    public LessonInfo getLessonByLessonId(Optional<Long> userId, Long lessonId);

    List<LessonInfo> listLessonsByType(Optional<Long> userId, Integer type, Integer pageNum, Integer pageSize);

    List<LessonInfo> listLessonsByTypeAndKey(Optional<Long> userId, Integer type, String key, Integer pageNum, Integer pageSize);

    /* List<LessonInfo> listLessonsByLessonName(String LessonName, Integer pageNum, Integer pageSize);

    List<LessonInfo> listLessonsByTeacherId(Integer teacherId, Integer pageNum, Integer pageSize); */
}
