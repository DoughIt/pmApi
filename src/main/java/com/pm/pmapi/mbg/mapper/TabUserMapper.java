package com.pm.pmapi.mbg.mapper;

import com.pm.pmapi.mbg.model.TabUser;
import com.pm.pmapi.mbg.model.TabUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TabUserMapper {
    long countByExample(TabUserExample example);

    int deleteByExample(TabUserExample example);

    int deleteByPrimaryKey(@Param("id") Long id, @Param("nav") Long nav);

    int insert(TabUser record);

    int insertSelective(TabUser record);

    List<TabUser> selectByExample(TabUserExample example);

    TabUser selectByPrimaryKey(@Param("id") Long id, @Param("nav") Long nav);

    int updateByExampleSelective(@Param("record") TabUser record, @Param("example") TabUserExample example);

    int updateByExample(@Param("record") TabUser record, @Param("example") TabUserExample example);

    int updateByPrimaryKeySelective(TabUser record);

    int updateByPrimaryKey(TabUser record);
}