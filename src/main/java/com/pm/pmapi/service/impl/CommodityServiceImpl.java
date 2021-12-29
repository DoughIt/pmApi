package com.pm.pmapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.pm.pmapi.dao.*;
import com.pm.pmapi.dto.CommodityInfo;
import com.pm.pmapi.dto.CommodityInfos;
import com.pm.pmapi.dto.CommodityParam;
import com.pm.pmapi.dto.SimpleUserInfo;
import com.pm.pmapi.mbg.mapper.TabCommodityMapper;
import com.pm.pmapi.mbg.mapper.TabFavoriteMapper;
import com.pm.pmapi.mbg.mapper.TabSoldCommodityMapper;
import com.pm.pmapi.mbg.mapper.TabUserMapper;
import com.pm.pmapi.mbg.model.*;
import com.pm.pmapi.service.CommodityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.enterprise.inject.spi.Bean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: pmApi
 * @description: 实现类
 * @author: Shen Zhengyu
 * @create: 2021-12-07 16:23
 **/
@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    TabCommodityMapper commodityMapper;

    @Autowired
    TabFavoriteMapper favoriteMapper;

    @Autowired
    UserDao userDao;
    @Autowired
    TabUserMapper userMapper;

    @Autowired
    LessonDao lessonDao;
    @Autowired
    FavoriteDao favoriteDao;
    @Autowired
    CommodityDao commodityDao;

    @Autowired
    SoldCommodityDao soldCommodityDao;

    @Autowired
    TabSoldCommodityMapper soldCommodityMapper;

    @Override
    public CommodityInfos getCommodityById(Long userId, Long id) {
        CommodityInfo commodityInfoToReturn = new CommodityInfo();
        if (null != commodityDao.getCommodityById(id)){
            commodityInfoToReturn = commodityDao.getCommodityById(id);
        }else if (null != commodityMapper.selectByPrimaryKey(id)){
            BeanUtils.copyProperties(commodityMapper.selectByPrimaryKey(id),commodityInfoToReturn);
        }else{
            TabSoldCommodityExample example = new TabSoldCommodityExample();
            TabSoldCommodityExample.Criteria criteria = example.createCriteria();
            criteria.andIdEqualTo(id);
            List<TabSoldCommodity> list = soldCommodityMapper.selectByExample(example);
            if (list.isEmpty()){
                BeanUtils.copyProperties(list.get(0),commodityInfoToReturn);
            }
        }
        if (null == commodityInfoToReturn) return null;
        List<CommodityInfo> infos = new ArrayList<>();
        infos.add(commodityInfoToReturn);
        List<CommodityInfos> enhancedInfo = appendInfos(userId, infos);
        return enhancedInfo.get(0);
    }

    @Override
    public CommodityInfo createCommodity(Long userId, Integer type, CommodityParam commodityParam) {
        TabCommodity commodity = new TabCommodity();
        BeanUtils.copyProperties(commodityParam, commodity);
        commodity.setSellerId(userId);
        commodity.setType(type);
        commodityMapper.insert(commodity);
        SimpleUserInfo simpleUserInfo = userDao.selectSimpleUserByPrimaryKey(userId);
        CommodityInfo info = new CommodityInfo();
        BeanUtils.copyProperties(commodityMapper.selectByPrimaryKey(commodity.getId()), info);
//        info.setSeller(simpleUserInfo);
        return info;
    }

    @Override
    public List<CommodityInfos> listCommoditiesByTypeAndKey(Long userId, Integer type, String key, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TabCommodity> commodities = new ArrayList<>();
        List<CommodityInfo> commodityInfos = new ArrayList<>();
        if (null == key || "".equals(key)){
            commodityInfos = commodityDao.listCommoditiesByType(type);
        }else{
            TabCommodityExample example = new TabCommodityExample();
            TabCommodityExample.Criteria criteria = example.createCriteria();
            criteria.andNameLike("%" + key + "%")
                    .andTypeEqualTo(type);
            commodities = commodityMapper.selectByExample(example);
            for (TabCommodity commodity : commodities) {
                CommodityInfo tmp = new CommodityInfo();
                BeanUtils.copyProperties(commodity, tmp);
                commodityInfos.add(tmp);
            }
        }
        List<CommodityInfos> enhancedCommodityInfos = new ArrayList<>();
        enhancedCommodityInfos = appendInfos(userId, commodityInfos);
        return enhancedCommodityInfos;
    }

    @Override
    public List<CommodityInfos> listCommoditiesByType(Long userId, Integer type, Integer pageNum, Integer pageSize) {
        List<CommodityInfo> list = commodityDao.listCommoditiesByType(type);
        return appendInfos(userId, list);
    }

    @Override
    public CommodityInfo updateCommodityByPrimaryKey(Long userId, Long id, CommodityParam commodityParam) {
        TabCommodity commodity = commodityMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(commodityParam, commodity);
        commodity.setModifyTime(new Date());
        commodityMapper.updateByPrimaryKey(commodity);
        CommodityInfo info = new CommodityInfo();
        BeanUtils.copyProperties(commodityMapper.selectByPrimaryKey(id), info);
        return info;
    }



    @Override
    public void deleteCommodityById(Long userId, Long id) {
        TabCommodity tabCommodity = commodityMapper.selectByPrimaryKey(id);
        TabSoldCommodity tabSoldCommodity = new TabSoldCommodity();
        BeanUtils.copyProperties(tabCommodity,tabSoldCommodity);
        tabSoldCommodity.setCommodityId(tabCommodity.getId());
        soldCommodityMapper.insert(tabSoldCommodity);
        commodityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public boolean deleteFavoriteCommodity(Long user_id, Long commodity_id) {
        TabFavorite favorite = favoriteDao.getFavoriteByUserIdAndCommodityId(user_id, commodity_id);
        if (null != favorite){
            favoriteMapper.deleteByPrimaryKey(favorite.getId());
        }else{
            return false;
        }
        return true;
    }

    @Override
    public boolean addFavoriteCommodity(Long user_id, Long commodity_id) {
        TabFavorite favorite = favoriteDao.getFavoriteByUserIdAndCommodityId(user_id, commodity_id);
        if (null != favorite){
            return false;
        }else{
            TabFavorite tmp = new TabFavorite();
            tmp.setCommodityId(commodity_id);
            tmp.setUserId(user_id);
            favoriteMapper.insert(tmp);
        }
        return true;
    }

    @Override
    public List<CommodityInfos> listFavoriteCommodities(Long user_id, Integer pageNum, Integer pageSize) {
        List<TabFavorite> favorites = favoriteDao.getFavoriteByUserId(user_id);
        List<Long> commodityIds = new ArrayList<>();
        for (TabFavorite favorite : favorites) {
            commodityIds.add(favorite.getCommodityId());
        }
        if (-1 == pageNum && -1 == pageSize){

        }else{
            PageHelper.startPage(pageNum, pageSize);
        }
        List<CommodityInfo> toReturn = new ArrayList<>();
        TabCommodityExample tabCommodityExample = new TabCommodityExample();
        TabCommodityExample.Criteria criteria = tabCommodityExample.createCriteria();
        criteria.andCommodityIdIn(commodityIds);
        List<TabCommodity> tabCommodities = commodityMapper.selectByExample(tabCommodityExample);
        if (null == tabCommodities){
            return new ArrayList<CommodityInfos>();
        }else{
            for (TabCommodity tabCommodity : tabCommodities) {
                CommodityInfo tmp = new CommodityInfo();
                BeanUtils.copyProperties(tabCommodity, tmp);
                toReturn.add(tmp);
            }
            return appendInfos(user_id, toReturn);
        }

    }

    @Override
    public List<CommodityInfos> getCommodities(Long userId, Integer type, String lessonId, Boolean isSold, Boolean isMine, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if (isSold){
            if (isMine){
                if (null == lessonId){
                    if (type == 0){
                        return appendInfos(userId, soldCommodityDao.listCommoditiesBySellerId(userId));
                    }else{
                        return appendInfos(userId, soldCommodityDao.listCommoditiesBySellerIdAndType(userId, type));
                    }
                }else{
                    if (type == 0){
                        return appendInfos(userId, soldCommodityDao.listCommoditiesBySellerIdAndLessonId(userId, lessonId));
                    }else{
                        return appendInfos(userId, soldCommodityDao.listCommoditiesBySellerIdAndLessonIdAndType(userId, lessonId, type));
                    }
                }
            }else{
                //查看所有
                if (null == lessonId){
                    if (type == 0){
                        return appendInfos(userId, mix(soldCommodityDao.listCommoditiesByType(1), soldCommodityDao.listCommoditiesByType(2), soldCommodityDao.listCommoditiesByType(3)));
                    }else{
                        return appendInfos(userId, soldCommodityDao.listCommoditiesByType(type));
                    }
                }else{
                    if (type == 0){
                        return appendInfos(userId, soldCommodityDao.listCommoditiesByLessonId(lessonId));
                    }else{
                        return appendInfos(userId, soldCommodityDao.listCommoditiesByLessonIdAndType(lessonId, type));
                    }
                }
            }
        }else{
            if (isMine){
                if (null == lessonId){
                    if (type == 0){
                        return appendInfos(userId, commodityDao.listCommoditiesBySellerId(userId));
                    }else{
                        return appendInfos(userId, commodityDao.listCommoditiesBySellerIdAndType(userId, type));
                    }
                }else{
                    if (type == 0){
                        return appendInfos(userId, commodityDao.listCommoditiesBySellerIdAndLessonId(userId, lessonId));
                    }else{
                        return appendInfos(userId, commodityDao.listCommoditiesBySellerIdAndLessonIdAndType(userId, lessonId, type));
                    }
                }
            }else{
                //查看所有
                if (null == lessonId){
                    if (type == 0){
                        return appendInfos(userId, mix(commodityDao.listCommoditiesByType(1), commodityDao.listCommoditiesByType(2), commodityDao.listCommoditiesByType(3)));
                    }else{
                        return appendInfos(userId, commodityDao.listCommoditiesByType(type));
                    }
                }else{
                    if (type == 0){
                        return appendInfos(userId, commodityDao.listCommoditiesByLessonId(lessonId));
                    }else{
                        return appendInfos(userId, commodityDao.listCommoditiesByLessonIdAndType(lessonId, type));
                    }
                }
            }
        }
    }

    @Override
    public List<CommodityInfos> getSoldCommodityByUserId(Long user_id) {
        return appendInfos(user_id, soldCommodityDao.listCommoditiesBySellerId(user_id));
    }

    private List<CommodityInfo> convert(List<TabCommodity> tabCommodities, List<TabSoldCommodity> tabSoldCommodities){
        List<CommodityInfo> result = new ArrayList<>();
        if (null == tabCommodities){
            CommodityInfo tmp = new CommodityInfo();
            for (TabCommodity tabCommodity : tabCommodities) {
                BeanUtils.copyProperties(tabCommodity, tmp);
                result.add(tmp);
            }
            return result;
        }else{
            CommodityInfo tmp = new CommodityInfo();
            for (TabSoldCommodity tabCommodity : tabSoldCommodities) {
                BeanUtils.copyProperties(tabCommodity, tmp);
                result.add(tmp);
            }
            return result;
        }
    }

    private List<CommodityInfo> append(Long userId, List<CommodityInfo> tabCommodities){
        for (CommodityInfo tabCommodity : tabCommodities) {
            Long id = tabCommodity.getSellerId();
//            tabCommodity.setSeller(userDao.selectSimpleUserByPrimaryKey(id));
            if (null != tabCommodity.getSinglePrint()){
                tabCommodity.setSinglePrintChinese(tabCommodity.getSinglePrint() ? "是" : "否");
            }
            if (null != tabCommodity.getAuthor() && "".equals(tabCommodity.getAuthor())) tabCommodity.setAuthor(null);
            if (null != tabCommodity.getPublisher() && "".equals(tabCommodity.getPublisher())) tabCommodity.setPublisher(null);
            if (null != tabCommodity.getCoverPercentage() && "".equals(tabCommodity.getCoverPercentage())) tabCommodity.setCoverPercentage(null);
            if (null != tabCommodity.getFilename() && "".equals(tabCommodity.getFilename())) tabCommodity.setFilename(null);
            if (null != tabCommodity.getContent() && "".equals(tabCommodity.getContent())) tabCommodity.setContent(null);
            if (null != tabCommodity.getPaperSize() && "".equals(tabCommodity.getPaperSize())) tabCommodity.setPaperSize(null);
            if (null != tabCommodity.getNewDegree() && "".equals(tabCommodity.getNewDegree())) tabCommodity.setNewDegree(null);
            if (null != tabCommodity.getUnit() && "".equals(tabCommodity.getUnit())) tabCommodity.setUnit(null);
            if (null != tabCommodity.getChapters() && "".equals(tabCommodity.getChapters())) tabCommodity.setChapters(null);
            if (null == tabCommodity.getType()){
                tabCommodity.setType(1);
            }
            if (null == favoriteDao.getFavoriteByUserIdAndCommodityId(userId, tabCommodity.getId())){
                tabCommodity.setIsFavorite(false);
            }else{
                tabCommodity.setIsFavorite(true);
            }
        }
        return tabCommodities;
    }

    private List<CommodityInfos> appendInfos(Long userId, List<CommodityInfo> commodities){
        List<CommodityInfos> list = new ArrayList<>();
        List<CommodityInfo> tabCommodities = append(userId, commodities);
        for (CommodityInfo tabCommodity : tabCommodities) {
            CommodityInfos tmp = new CommodityInfos();
            BeanUtils.copyProperties(tabCommodity, tmp);
            tmp.setSeller(userDao.selectSimpleUserByPrimaryKey(tabCommodity.getSellerId()));
            tmp.setLesson(lessonDao.getLessonByLessonId(tabCommodity.getLessonId()));
            list.add(tmp);
        }
        return list;
    }

    private List<CommodityInfo> mix(List<CommodityInfo> list1, List<CommodityInfo> list2, List<CommodityInfo> list3){
        List<CommodityInfo> allInfo = new ArrayList<>();
        allInfo.addAll(list1);
        allInfo.addAll(list2);
        allInfo.addAll(list3);
        return allInfo;
    }
}
