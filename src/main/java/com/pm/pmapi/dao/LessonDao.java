package com.pm.pmapi.dao;

import com.pm.pmapi.dto.LessonInfo;
import org.apache.ibatis.annotations.Param;

public interface LessonDao {

    LessonInfo getLessonByLessonId(@Param("lessonId") Long lessonId);
}
