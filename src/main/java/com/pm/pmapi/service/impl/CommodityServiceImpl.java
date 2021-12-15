package com.pm.pmapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.pm.pmapi.dao.CommodityDao;
import com.pm.pmapi.dao.FavoriteDao;
import com.pm.pmapi.dao.SoldCommodityDao;
import com.pm.pmapi.dao.UserDao;
import com.pm.pmapi.dto.CommodityInfo;
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
    FavoriteDao favoriteDao;
    @Autowired
    CommodityDao commodityDao;

    @Autowired
    SoldCommodityDao soldCommodityDao;

    @Autowired
    TabSoldCommodityMapper soldCommodityMapper;

    @Override
    public CommodityInfo getCommodityById(Long userId, Long id) {
        CommodityInfo commodityInfoToReturn = new CommodityInfo();
        if (null != commodityDao.getCommodityById(id)){
            commodityInfoToReturn = commodityDao.getCommodityById(id);
//            commodityInfoToReturn.setSeller(userDao.selectSimpleUserByPrimaryKey(commodityInfoToReturn.getSellerId()));
            // TODO: 课程
        }else if (null != commodityMapper.selectByPrimaryKey(id)){
            BeanUtils.copyProperties(commodityMapper.selectByPrimaryKey(id),commodityInfoToReturn);
//            commodityInfoToReturn.setSeller(userDao.selectSimpleUserByPrimaryKey(commodityMapper.selectByPrimaryKey(id).getSellerId()));
            // TODO: 课程
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
        ArrayList<CommodityInfo> infos = new ArrayList<>();
        infos.add(commodityInfoToReturn);
        append(userId, infos);
        return infos.get(0);
    }

    @Override
    public CommodityInfo createCommodity(Long userId, Integer type, CommodityParam commodityParam) {
        TabCommodity commodity = new TabCommodity();
        BeanUtils.copyProperties(commodityParam, commodity);
        commodity.setSellerId(userId);
        commodityMapper.insert(commodity);
        SimpleUserInfo simpleUserInfo = userDao.selectSimpleUserByPrimaryKey(userId);
        CommodityInfo info = new CommodityInfo();
        BeanUtils.copyProperties(commodityMapper.selectByPrimaryKey(commodity.getId()), info);
//        info.setSeller(simpleUserInfo);
        return info;
    }

    @Override
    public List<CommodityInfo> listCommoditiesByTypeAndKey(Long userId, Integer type, String key, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TabCommodityExample example = new TabCommodityExample();
        TabCommodityExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike(key)
                .andTypeEqualTo(type);
        List<TabCommodity> commodities = commodityMapper.selectByExample(example);
        List<CommodityInfo> commodityInfos = new ArrayList<>();
        for (TabCommodity commodity : commodities) {
            CommodityInfo tmp = new CommodityInfo();
            BeanUtils.copyProperties(commodity, tmp);
//            tmp.setSeller(userDao.selectSimpleUserByPrimaryKey(commodity.getSellerId()));
            // TODO: 课程
            commodityInfos.add(tmp);

        }
        return commodityInfos;
    }

    @Override
    public List<CommodityInfo> listCommoditiesByType(Long userId, Integer type, Integer pageNum, Integer pageSize) {
        List<CommodityInfo> list = commodityDao.listCommoditiesByType(type);
        append(userId, list);
        return list;
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
    public List<CommodityInfo> listFavoriteCommodities(Long user_id) {
        List<TabFavorite> favorites = favoriteDao.getFavoriteByUserId(user_id);
        List<CommodityInfo> toReturn = new ArrayList<>();
        for (TabFavorite favorite : favorites) {
            CommodityInfo commodityInfo = commodityDao.getCommodityById(favorite.getCommodityId());
            toReturn.add(commodityInfo);
        }
        append(user_id, toReturn);
        return toReturn;
    }

    @Override
    public List<CommodityInfo> getCommodities(Long userId, Integer type, String lessonId, Boolean isSold, Boolean isMine, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if (isSold){
            if (isMine){
                if (null == lessonId){
                    if (type == 0){
                        return append(userId, soldCommodityDao.listCommoditiesBySellerId(userId));
                    }else{
                        return append(userId, soldCommodityDao.listCommoditiesBySellerIdAndType(userId, type));
                    }
                }else{
                    if (type == 0){
                        return append(userId, soldCommodityDao.listCommoditiesBySellerIdAndLessonId(userId, lessonId));
                    }else{
                        return append(userId, soldCommodityDao.listCommoditiesBySellerIdAndLessonIdAndType(userId, lessonId, type));
                    }
                }
            }else{
                //查看所有
                if (null == lessonId){
                    if (type == 0){
                        return append(userId, mix(soldCommodityDao.listCommoditiesByType(1), soldCommodityDao.listCommoditiesByType(2), soldCommodityDao.listCommoditiesByType(3)));
                    }else{
                        return append(userId, soldCommodityDao.listCommoditiesByType(type));
                    }
                }else{
                    if (type == 0){
                        return append(userId, soldCommodityDao.listCommoditiesByLessonId(lessonId));
                    }else{
                        return append(userId, soldCommodityDao.listCommoditiesByLessonIdAndType(lessonId, type));
                    }
                }
            }
        }else{
            if (isMine){
                if (null == lessonId){
                    if (type == 0){
                        return append(userId, commodityDao.listCommoditiesBySellerId(userId));
                    }else{
                        return append(userId, commodityDao.listCommoditiesBySellerIdAndType(userId, type));
                    }
                }else{
                    if (type == 0){
                        return append(userId, commodityDao.listCommoditiesBySellerIdAndLessonId(userId, lessonId));
                    }else{
                        return append(userId, commodityDao.listCommoditiesBySellerIdAndLessonIdAndType(userId, lessonId, type));
                    }
                }
            }else{
                //查看所有
                if (null == lessonId){
                    if (type == 0){
                        return append(userId, mix(commodityDao.listCommoditiesByType(1), commodityDao.listCommoditiesByType(2), commodityDao.listCommoditiesByType(3)));
                    }else{
                        return append(userId, commodityDao.listCommoditiesByType(type));
                    }
                }else{
                    if (type == 0){
                        return append(userId, commodityDao.listCommoditiesByLessonId(lessonId));
                    }else{
                        return append(userId, commodityDao.listCommoditiesByLessonIdAndType(lessonId, type));
                    }
                }
            }
        }
    }

    @Override
    public List<CommodityInfo> getSoldCommodityByUserId(Long user_id,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return append(user_id, soldCommodityDao.listCommoditiesBySellerId(user_id));
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
            tabCommodity.setSinglePrintChinese(tabCommodity.getSinglePrint() ? "是" : "否");
            tabCommodity.setSinglePrint(null);
            if ("".equals(tabCommodity.getAuthor())) tabCommodity.setAuthor(null);
            if ("".equals(tabCommodity.getPublisher())) tabCommodity.setPublisher(null);
            if ("".equals(tabCommodity.getCoverPercentage())) tabCommodity.setCoverPercentage(null);
            if ("".equals(tabCommodity.getImageUrl())) tabCommodity.setImageUrl(null);
            if ("".equals(tabCommodity.getContent())) tabCommodity.setContent(null);
            if ("".equals(tabCommodity.getPaperSize())) tabCommodity.setPaperSize(null);
            if ("".equals(tabCommodity.getNewDegree())) tabCommodity.setNewDegree(null);
            if ("".equals(tabCommodity.getUnit())) tabCommodity.setUnit(null);
            if ("".equals(tabCommodity.getChapters())) tabCommodity.setChapters(null);
            tabCommodity.setType(null);
            if (null == favoriteDao.getFavoriteByUserIdAndCommodityId(userId, tabCommodity.getId())){
                tabCommodity.setIsFavorite(false);
            }else{
                tabCommodity.setIsFavorite(true);
            }
            // TODO: 课程
        }
        return tabCommodities;
    }

    private List<CommodityInfo> mix(List<CommodityInfo> list1, List<CommodityInfo> list2, List<CommodityInfo> list3){
        List<CommodityInfo> allInfo = new ArrayList<>();
        allInfo.addAll(list1);
        allInfo.addAll(list2);
        allInfo.addAll(list3);
        return allInfo;
    }
}
