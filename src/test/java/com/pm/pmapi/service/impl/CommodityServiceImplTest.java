package com.pm.pmapi.service.impl;

import com.pm.pmapi.dao.CommodityDao;
import com.pm.pmapi.dto.CommodityInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CommodityServiceImplTest {

    @Autowired
    CommodityServiceImpl commodityService;


    @Test
    void getCommodityById() {
    }

    @Test
    void createCommodity() {
    }




    @Test
    void updateCommodityByPrimaryKey() {
    }

    @Test
    void deleteCommodityById() {
    }

    @Test
    void deleteFavoriteCommodity() {
    }

    @Test
    void addFavoriteCommodity() {
    }

    @Test
    void listFavoriteCommodities() {
    }

    @Test
    void getCommodities() {
        List<CommodityInfo> list;
        list = commodityService.listCommoditiesByType(1L,1,1,1);
        for (CommodityInfo commodityInfo : list) {
            System.out.println(commodityInfo);
        }
    }

    @Test
    void getSoldCommodityByUserId() {
    }
}