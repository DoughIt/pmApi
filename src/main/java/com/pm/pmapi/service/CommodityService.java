package com.pm.pmapi.service;

import com.pm.pmapi.dto.CommodityInfo;
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
    CommodityInfo getCommodityById(Long id);

    CommodityInfo createCommodity(Long userId, Integer type,CommodityParam commodityParam);

    List<CommodityInfo> listCommoditiesByTypeAndKey(Integer type, String key, Integer pageNum, Integer pageSize);

    List<CommodityInfo> listCommoditiesByType(Integer type,Integer pageNum, Integer pageSize);

    CommodityInfo updateCommodityByPrimaryKey(Long id, CommodityParam commodityParam);

    void deleteCommodityById(Long id);

    List<CommodityInfo> getSoldCommodityByUserId(Long id,Integer pageNum, Integer pageSize);
}
