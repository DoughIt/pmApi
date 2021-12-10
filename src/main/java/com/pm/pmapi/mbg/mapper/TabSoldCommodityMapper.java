package com.pm.pmapi.mbg.mapper;

import com.pm.pmapi.mbg.model.TabSoldCommodity;
import com.pm.pmapi.mbg.model.TabSoldCommodityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TabSoldCommodityMapper {
    long countByExample(TabSoldCommodityExample example);

    int deleteByExample(TabSoldCommodityExample example);

    int deleteByPrimaryKey(Long soldId);

    int insert(TabSoldCommodity record);

    int insertSelective(TabSoldCommodity record);

    List<TabSoldCommodity> selectByExampleWithBLOBs(TabSoldCommodityExample example);

    List<TabSoldCommodity> selectByExample(TabSoldCommodityExample example);

    TabSoldCommodity selectByPrimaryKey(Long soldId);

    int updateByExampleSelective(@Param("record") TabSoldCommodity record, @Param("example") TabSoldCommodityExample example);

    int updateByExampleWithBLOBs(@Param("record") TabSoldCommodity record, @Param("example") TabSoldCommodityExample example);

    int updateByExample(@Param("record") TabSoldCommodity record, @Param("example") TabSoldCommodityExample example);

    int updateByPrimaryKeySelective(TabSoldCommodity record);

    int updateByPrimaryKeyWithBLOBs(TabSoldCommodity record);

    int updateByPrimaryKey(TabSoldCommodity record);
}