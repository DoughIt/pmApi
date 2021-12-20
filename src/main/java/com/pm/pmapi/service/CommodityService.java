package com.pm.pmapi.service;

import com.pm.pmapi.dto.CommodityInfo;
import com.pm.pmapi.dto.CommodityInfos;
import com.pm.pmapi.dto.CommodityParam;
import com.pm.pmapi.mbg.model.TabCommodity;

import java.util.List;

public interface CommodityService {
    /**
     * @Description: 根据商品ID获得商品，再向下转型
     * @Param: [id]
     * @return: com.pm.pmapi.mybatis.model.Commodity
     * @Author: Shen Zhengyu
     * @Date: 2021/12/7
     */
    CommodityInfo getCommodityById(Long userId, Long id);

    CommodityInfo createCommodity(Long userId, Integer type, CommodityParam commodityParam);

    List<CommodityInfo> listCommoditiesByTypeAndKey(Long userId, Integer type, String key, Integer pageNum, Integer pageSize);

    List<CommodityInfo> listCommoditiesByType(Long userId, Integer type, Integer pageNum, Integer pageSize);

    CommodityInfo updateCommodityByPrimaryKey(Long userId, Long id, CommodityParam commodityParam);

    void deleteCommodityById(Long userId, Long id);

    List<CommodityInfos> getCommodities(Long userId, Integer type, String lessonId, Boolean isSold, Boolean isMine, Integer pageNum, Integer pageSize);

    List<CommodityInfo> getSoldCommodityByUserId(Long userId, Integer pageNum, Integer pageSize);

    boolean deleteFavoriteCommodity(Long user_id, Long commodity_id);

    boolean addFavoriteCommodity(Long user_id, Long commodity_id);

    List<CommodityInfo> listFavoriteCommodities(Long user_id);

}
