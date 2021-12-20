package com.pm.pmapi.mbg.mapper;

import com.pm.pmapi.mbg.model.TabLessonUserTag;
import com.pm.pmapi.mbg.model.TabLessonUserTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TabLessonUserTagMapper {
    long countByExample(TabLessonUserTagExample example);

    int deleteByExample(TabLessonUserTagExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TabLessonUserTag record);

    int insertSelective(TabLessonUserTag record);

    List<TabLessonUserTag> selectByExample(TabLessonUserTagExample example);

    TabLessonUserTag selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TabLessonUserTag record, @Param("example") TabLessonUserTagExample example);

    int updateByExample(@Param("record") TabLessonUserTag record, @Param("example") TabLessonUserTagExample example);

    int updateByPrimaryKeySelective(TabLessonUserTag record);

    int updateByPrimaryKey(TabLessonUserTag record);
}