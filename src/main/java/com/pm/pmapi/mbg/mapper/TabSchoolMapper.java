package com.pm.pmapi.mbg.mapper;

import com.pm.pmapi.mbg.model.TabSchool;
import com.pm.pmapi.mbg.model.TabSchoolExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TabSchoolMapper {
    long countByExample(TabSchoolExample example);

    int deleteByExample(TabSchoolExample example);

    int deleteByPrimaryKey(Long schoolId);

    int insert(TabSchool record);

    int insertSelective(TabSchool record);

    List<TabSchool> selectByExample(TabSchoolExample example);

    TabSchool selectByPrimaryKey(Long schoolId);

    int updateByExampleSelective(@Param("record") TabSchool record, @Param("example") TabSchoolExample example);

    int updateByExample(@Param("record") TabSchool record, @Param("example") TabSchoolExample example);

    int updateByPrimaryKeySelective(TabSchool record);

    int updateByPrimaryKey(TabSchool record);
}