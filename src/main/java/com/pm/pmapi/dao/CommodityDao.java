package com.pm.pmapi.dao;

import com.pm.pmapi.dto.CommodityInfo;
import com.pm.pmapi.dto.CommodityParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityDao {

    List<CommodityInfo> listCommodities();

    CommodityInfo getCommodityById(@Param("commodityId") Long id);

    List<CommodityInfo> listCommoditiesBySellerIdAndLessonId(@Param("sellerId") Long sellerId, @Param("lessonId") String lessonId);

    List<CommodityInfo> listCommoditiesBySellerId(@Param("sellerId") Long sellerId);


    List<CommodityInfo> listCommoditiesByLessonId(@Param("lessonId") String lessonId);

    List<CommodityInfo> listCommoditiesByType(@Param("type") Long type);

    List<CommodityInfo> listCommoditiesByTypeAndKey(@Param("type") Long type, String key);

}
