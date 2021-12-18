package com.pm.pmapi.mbg.mapper;

import com.pm.pmapi.mbg.model.TabLessonTag;
import com.pm.pmapi.mbg.model.TabLessonTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TabLessonTagMapper {
    long countByExample(TabLessonTagExample example);

    int deleteByExample(TabLessonTagExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TabLessonTag record);

    int insertSelective(TabLessonTag record);

    List<TabLessonTag> selectByExample(TabLessonTagExample example);

    TabLessonTag selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TabLessonTag record, @Param("example") TabLessonTagExample example);

    int updateByExample(@Param("record") TabLessonTag record, @Param("example") TabLessonTagExample example);

    int updateByPrimaryKeySelective(TabLessonTag record);

    int updateByPrimaryKey(TabLessonTag record);
}