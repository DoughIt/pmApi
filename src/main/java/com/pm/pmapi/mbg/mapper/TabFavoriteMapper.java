package com.pm.pmapi.mbg.mapper;

import com.pm.pmapi.mbg.model.TabFavorite;
import com.pm.pmapi.mbg.model.TabFavoriteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TabFavoriteMapper {
    long countByExample(TabFavoriteExample example);

    int deleteByExample(TabFavoriteExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TabFavorite record);

    int insertSelective(TabFavorite record);

    List<TabFavorite> selectByExample(TabFavoriteExample example);

    TabFavorite selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TabFavorite record, @Param("example") TabFavoriteExample example);

    int updateByExample(@Param("record") TabFavorite record, @Param("example") TabFavoriteExample example);

    int updateByPrimaryKeySelective(TabFavorite record);

    int updateByPrimaryKey(TabFavorite record);
}