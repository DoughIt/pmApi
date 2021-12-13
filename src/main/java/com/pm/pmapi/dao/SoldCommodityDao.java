package com.pm.pmapi.dao;

import com.pm.pmapi.dto.CommodityInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SoldCommodityDao {

    List<CommodityInfo> listCommodities();

    List<CommodityInfo> listCommoditiesByLessonId(@Param("lessonId") String lessonId);

    List<CommodityInfo> listCommoditiesBySellerId(@Param("sellerId") Long sellerId);

    List<CommodityInfo> listCommoditiesBySellerIdAndLessonId(@Param("sellerId") Long sellerId, @Param("lessonId") String lessonId);


}
