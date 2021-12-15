package com.pm.pmapi.mbg.mapper;

import com.pm.pmapi.mbg.model.TabTeacher;
import com.pm.pmapi.mbg.model.TabTeacherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TabTeacherMapper {
    long countByExample(TabTeacherExample example);

    int deleteByExample(TabTeacherExample example);

    int insert(TabTeacher record);

    int insertSelective(TabTeacher record);

    List<TabTeacher> selectByExample(TabTeacherExample example);

    int updateByExampleSelective(@Param("record") TabTeacher record, @Param("example") TabTeacherExample example);

    int updateByExample(@Param("record") TabTeacher record, @Param("example") TabTeacherExample example);
}