package com.pm.pmapi.dao;

import com.pm.pmapi.dto.CommodityInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SoldCommodityDao {


    List<CommodityInfo> listCommoditiesByType(@Param("type") Integer type);

    List<CommodityInfo> listCommoditiesByLessonIdAndType(@Param("lessonId") String lessonId, @Param("type") Integer type);

    List<CommodityInfo> listCommoditiesBySellerIdAndType(@Param("sellerId") Long sellerId, @Param("type") Integer type);

    List<CommodityInfo> listCommoditiesBySellerIdAndLessonIdAndType(@Param("sellerId") Long sellerId, @Param("lessonId") String lessonId, @Param("type") Integer type);

    List<CommodityInfo> listCommoditiesByLessonId(@Param("lessonId") String lessonId);

    List<CommodityInfo> listCommoditiesBySellerId(@Param("sellerId") Long sellerId);

    List<CommodityInfo> listCommoditiesBySellerIdAndLessonId(@Param("sellerId") Long sellerId, @Param("lessonId") String lessonId);


}
