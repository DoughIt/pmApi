package com.pm.pmapi.mbg.mapper;

import com.pm.pmapi.mbg.model.TabCommodity;
import com.pm.pmapi.mbg.model.TabCommodityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TabCommodityMapper {
    long countByExample(TabCommodityExample example);

    int deleteByExample(TabCommodityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TabCommodity record);

    int insertSelective(TabCommodity record);

    List<TabCommodity> selectByExampleWithBLOBs(TabCommodityExample example);

    List<TabCommodity> selectByExample(TabCommodityExample example);

    TabCommodity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TabCommodity record, @Param("example") TabCommodityExample example);

    int updateByExampleWithBLOBs(@Param("record") TabCommodity record, @Param("example") TabCommodityExample example);

    int updateByExample(@Param("record") TabCommodity record, @Param("example") TabCommodityExample example);

    int updateByPrimaryKeySelective(TabCommodity record);

    int updateByPrimaryKeyWithBLOBs(TabCommodity record);

    int updateByPrimaryKey(TabCommodity record);
}