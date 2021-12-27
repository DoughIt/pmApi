package com.pm.pmapi.dao;

import com.pm.pmapi.dto.LessonInfo;
import com.pm.pmapi.mbg.model.TabLessonPopularity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LessonPopularityDao {

    List<TabLessonPopularity> getHotK(@Param("k") Long k);

    List<LessonInfo> getHotLessonList();
}
