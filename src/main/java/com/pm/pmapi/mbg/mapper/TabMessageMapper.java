package com.pm.pmapi.mbg.mapper;

import com.pm.pmapi.mbg.model.TabMessage;
import com.pm.pmapi.mbg.model.TabMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TabMessageMapper {
    long countByExample(TabMessageExample example);

    int deleteByExample(TabMessageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TabMessage record);

    int insertSelective(TabMessage record);

    List<TabMessage> selectByExample(TabMessageExample example);

    TabMessage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TabMessage record, @Param("example") TabMessageExample example);

    int updateByExample(@Param("record") TabMessage record, @Param("example") TabMessageExample example);

    int updateByPrimaryKeySelective(TabMessage record);

    int updateByPrimaryKey(TabMessage record);
}