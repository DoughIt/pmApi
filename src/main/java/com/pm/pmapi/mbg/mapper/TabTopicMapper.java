package com.pm.pmapi.mbg.mapper;

import com.pm.pmapi.mbg.model.TabTopic;
import com.pm.pmapi.mbg.model.TabTopicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TabTopicMapper {
    long countByExample(TabTopicExample example);

    int deleteByExample(TabTopicExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TabTopic record);

    int insertSelective(TabTopic record);

    List<TabTopic> selectByExample(TabTopicExample example);

    TabTopic selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TabTopic record, @Param("example") TabTopicExample example);

    int updateByExample(@Param("record") TabTopic record, @Param("example") TabTopicExample example);

    int updateByPrimaryKeySelective(TabTopic record);

    int updateByPrimaryKey(TabTopic record);
}