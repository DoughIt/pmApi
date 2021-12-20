package com.pm.pmapi.mbg.mapper;

import com.pm.pmapi.mbg.model.TabTag;
import com.pm.pmapi.mbg.model.TabTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TabTagMapper {
    long countByExample(TabTagExample example);

    int deleteByExample(TabTagExample example);

    int deleteByPrimaryKey(Long tagId);

    int insert(TabTag record);

    int insertSelective(TabTag record);

    List<TabTag> selectByExample(TabTagExample example);

    TabTag selectByPrimaryKey(Long tagId);

    int updateByExampleSelective(@Param("record") TabTag record, @Param("example") TabTagExample example);

    int updateByExample(@Param("record") TabTag record, @Param("example") TabTagExample example);

    int updateByPrimaryKeySelective(TabTag record);

    int updateByPrimaryKey(TabTag record);
}