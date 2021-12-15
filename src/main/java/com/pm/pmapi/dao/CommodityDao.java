package com.pm.pmapi.dao;

import com.pm.pmapi.dto.CommodityInfo;
import com.pm.pmapi.dto.CommodityParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface CommodityDao {

    List<CommodityInfo> listCommodities();

    List<CommodityInfo> listCommoditiesByType(@Param("type") Integer type);

    List<CommodityInfo> listCommoditiesByLessonIdAndType(@Param("lessonId") String lessonId, @Param("type") Integer type);

    List<CommodityInfo> listCommoditiesBySellerIdAndType(@Param("sellerId") Long sellerId, @Param("type") Integer type);

    List<CommodityInfo> listCommoditiesBySellerIdAndLessonIdAndType(@Param("sellerId") Long sellerId, @Param("lessonId") String lessonId, @Param("type") Integer type);

    CommodityInfo getCommodityById(@Param("id") Long id);

    List<CommodityInfo> listCommoditiesBySellerIdAndLessonId(@Param("sellerId") Long sellerId, @Param("lessonId") String lessonId);

    List<CommodityInfo> listCommoditiesBySellerId(@Param("sellerId") Long sellerId);


    List<CommodityInfo> listCommoditiesByLessonId(@Param("lessonId") String lessonId);


    //使用时两边加入%%
    List<CommodityInfo> listCommoditiesByTypeAndKey(@Param("type") Integer type, @Param("key") String key);

}
