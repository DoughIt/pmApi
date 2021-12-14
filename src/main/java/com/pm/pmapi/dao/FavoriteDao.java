package com.pm.pmapi.dao;

import com.pm.pmapi.dto.CommodityInfo;
import com.pm.pmapi.mbg.model.TabFavorite;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FavoriteDao {
    List<TabFavorite> getFavoriteByUserId(@Param("user_id") Long id);

    TabFavorite getFavoriteByUserIdAndCommodityId(@Param("user_id") Long user_id, @Param("commodity_id") Long commodity_id);

}
