package com.pm.pmapi.mbg.mapper;

import com.pm.pmapi.mbg.model.TabFavoriteLesson;
import com.pm.pmapi.mbg.model.TabFavoriteLessonExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TabFavoriteLessonMapper {
    long countByExample(TabFavoriteLessonExample example);

    int deleteByExample(TabFavoriteLessonExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TabFavoriteLesson record);

    int insertSelective(TabFavoriteLesson record);

    List<TabFavoriteLesson> selectByExample(TabFavoriteLessonExample example);

    TabFavoriteLesson selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TabFavoriteLesson record, @Param("example") TabFavoriteLessonExample example);

    int updateByExample(@Param("record") TabFavoriteLesson record, @Param("example") TabFavoriteLessonExample example);

    int updateByPrimaryKeySelective(TabFavoriteLesson record);

    int updateByPrimaryKey(TabFavoriteLesson record);
}