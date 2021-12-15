package com.pm.pmapi.mbg.mapper;

import com.pm.pmapi.mbg.model.TabLesson;
import com.pm.pmapi.mbg.model.TabLessonExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TabLessonMapper {
    long countByExample(TabLessonExample example);

    int deleteByExample(TabLessonExample example);

    int deleteByPrimaryKey(Long lessonId);

    int insert(TabLesson record);

    int insertSelective(TabLesson record);

    List<TabLesson> selectByExample(TabLessonExample example);

    TabLesson selectByPrimaryKey(Long lessonId);

    int updateByExampleSelective(@Param("record") TabLesson record, @Param("example") TabLessonExample example);

    int updateByExample(@Param("record") TabLesson record, @Param("example") TabLessonExample example);

    int updateByPrimaryKeySelective(TabLesson record);

    int updateByPrimaryKey(TabLesson record);
}