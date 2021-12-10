package com.pm.pmapi.dao;

import com.pm.pmapi.dto.CommodityInfo;
import com.pm.pmapi.dto.CommodityParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityDao {


    CommodityInfo getCommodityById(@Param("commodityId") Long id);


    List<CommodityInfo> listCommoditiesByType(@Param("type") Long type);

    List<CommodityInfo> listCommoditiesByTypeAndKey(@Param("type") Long type, String key);

}
