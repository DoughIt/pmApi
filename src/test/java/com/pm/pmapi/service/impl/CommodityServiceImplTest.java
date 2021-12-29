package com.pm.pmapi.service.impl;

import com.pm.pmapi.dao.CommodityDao;
import com.pm.pmapi.dto.CommodityInfo;
import com.pm.pmapi.dto.CommodityInfos;
import com.pm.pmapi.dto.CommodityParam;
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
        CommodityParam commodityParam = new CommodityParam();
        commodityParam.setFilename("file");
        commodityParam.setName("ccc");
        commodityParam.setAuthor("aaa");
        commodityParam.setPublisher("222");
        commodityParam.setSellerId(1L);
        commodityParam.setLessonId(1L);
        commodityParam.setSinglePrint(true);
        commodityParam.setNewDegree("1");
        commodityParam.setPaperSize("1");
        commodityParam.setPrice(1.1);
        commodityParam.setContent("asd");
        commodityParam.setChapters("chapters");
        System.out.println(commodityService.createCommodity(1L,1,commodityParam));
        List<CommodityInfos> commodityInfos = commodityService.listCommoditiesByTypeAndKey(1L, 1, "c", 1,8);
        System.out.println("数目" + commodityInfos.size());
        for (CommodityInfos commodityInfo : commodityInfos) {
            System.out.println(commodityInfo);
        }
    }

    @Test
    void listCommoditiesByTypeAndKey(){

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
        List<CommodityInfos> list;
        list = commodityService.listCommoditiesByType(1L,1,1,1);
        for (CommodityInfos commodityInfo : list) {
            System.out.println(commodityInfo);
        }
    }
    @Test
    void getSoldCommodityById() {
        System.out.println(commodityService.getCommodityById(1L, 23L));
    }

    @Test
    void getSoldCommodityByUserId() {
    }
}