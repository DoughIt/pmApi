package com.pm.pmapi.dao;

import com.pm.pmapi.dto.CommodityInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CommodityDaoTest {
    @Autowired
    CommodityDao commodityDao;
    @Test
    void listCommodities(){
        List<CommodityInfo> list;
        list = commodityDao.listCommodities();
        for (CommodityInfo commodityInfo : list) {
            System.out.println(commodityInfo);
        }
    }
    @Test
    void listCommoditiesByTypeAndKey() {
        List<CommodityInfo> list;
        list = commodityDao.listCommoditiesByTypeAndKey(1,"d");
        for (CommodityInfo commodityInfo : list) {
            System.out.println(commodityInfo);
        }
    }

    @Test
    void listCommoditiesBySellerIdAndType() {
        List<CommodityInfo> list;
        list = commodityDao.listCommoditiesBySellerIdAndType(1L,1);
        for (CommodityInfo commodityInfo : list) {
            System.out.println(commodityInfo);
        }
    }

    @Test
    void listCommoditiesByType() {
        List<CommodityInfo> list;
        list = commodityDao.listCommoditiesByType(1);
        for (CommodityInfo commodityInfo : list) {
            System.out.println(commodityInfo);
        }
    }
}