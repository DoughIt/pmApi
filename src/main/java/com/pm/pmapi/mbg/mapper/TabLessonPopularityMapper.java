package com.pm.pmapi.mbg.mapper;

import com.pm.pmapi.mbg.model.TabLessonPopularity;
import com.pm.pmapi.mbg.model.TabLessonPopularityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TabLessonPopularityMapper {
    long countByExample(TabLessonPopularityExample example);

    int deleteByExample(TabLessonPopularityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TabLessonPopularity record);

    int insertSelective(TabLessonPopularity record);

    List<TabLessonPopularity> selectByExample(TabLessonPopularityExample example);

    TabLessonPopularity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TabLessonPopularity record, @Param("example") TabLessonPopularityExample example);

    int updateByExample(@Param("record") TabLessonPopularity record, @Param("example") TabLessonPopularityExample example);

    int updateByPrimaryKeySelective(TabLessonPopularity record);

    int updateByPrimaryKey(TabLessonPopularity record);

    List<TabLessonPopularity> getHotK(@Param("k") Long k);
}