package com.pm.pmapi.mbg.mapper;

import com.pm.pmapi.mbg.model.TabMiniProgram;
import com.pm.pmapi.mbg.model.TabMiniProgramExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TabMiniProgramMapper {
    long countByExample(TabMiniProgramExample example);

    int deleteByExample(TabMiniProgramExample example);

    int deleteByPrimaryKey(String appId);

    int insert(TabMiniProgram record);

    int insertSelective(TabMiniProgram record);

    List<TabMiniProgram> selectByExample(TabMiniProgramExample example);

    TabMiniProgram selectByPrimaryKey(String appId);

    int updateByExampleSelective(@Param("record") TabMiniProgram record, @Param("example") TabMiniProgramExample example);

    int updateByExample(@Param("record") TabMiniProgram record, @Param("example") TabMiniProgramExample example);

    int updateByPrimaryKeySelective(TabMiniProgram record);

    int updateByPrimaryKey(TabMiniProgram record);
}