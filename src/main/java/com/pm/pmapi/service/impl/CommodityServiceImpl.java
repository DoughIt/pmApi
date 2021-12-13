package com.pm.pmapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.pm.pmapi.dao.CommodityDao;
import com.pm.pmapi.dao.SoldCommodityDao;
import com.pm.pmapi.dao.UserDao;
import com.pm.pmapi.dto.CommodityInfo;
import com.pm.pmapi.dto.CommodityParam;
import com.pm.pmapi.dto.SimpleUserInfo;
import com.pm.pmapi.mbg.mapper.TabCommodityMapper;
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
    UserDao userDao;
    @Autowired
    TabUserMapper userMapper;

    @Autowired
    CommodityDao commodityDao;

    @Autowired
    SoldCommodityDao soldCommodityDao;

    @Autowired
    TabSoldCommodityMapper soldCommodityMapper;

    @Override
    public CommodityInfo getCommodityById(Long id) {
        CommodityInfo commodityInfoToReturn = new CommodityInfo();
        if (null != commodityDao.getCommodityById(id)){
            commodityInfoToReturn = commodityDao.getCommodityById(id);
            commodityInfoToReturn.setSeller(userDao.selectSimpleUserByPrimaryKey(commodityInfoToReturn.getSellerId()));
            // TODO: 课程
            return commodityInfoToReturn;
        }else if (null != commodityMapper.selectByPrimaryKey(id)){
            BeanUtils.copyProperties(commodityMapper.selectByPrimaryKey(id),commodityInfoToReturn);
            commodityInfoToReturn.setSeller(userDao.selectSimpleUserByPrimaryKey(commodityMapper.selectByPrimaryKey(id).getSellerId()));
            // TODO: 课程
            return commodityInfoToReturn;
        }else{
            TabSoldCommodityExample example = new TabSoldCommodityExample();
            TabSoldCommodityExample.Criteria criteria = example.createCriteria();
            criteria.andIdEqualTo(id);
            List<TabSoldCommodity> list = soldCommodityMapper.selectByExample(example);
            if (list.size() > 0){
                BeanUtils.copyProperties(list.get(0),commodityInfoToReturn);
                commodityInfoToReturn.setSeller(userDao.selectSimpleUserByPrimaryKey(list.get(0).getSellerId()));
                // TODO: 课程
                return commodityInfoToReturn;
            }else{
                return null;
            }
        }
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
        info.setSeller(simpleUserInfo);
        return info;
    }

    @Override
    public List<CommodityInfo> listCommoditiesByTypeAndKey(Integer type, String key, Integer pageNum, Integer pageSize) {
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
            tmp.setSeller(userDao.selectSimpleUserByPrimaryKey(commodity.getSellerId()));
            // TODO: 课程
            commodityInfos.add(tmp);

        }
        return commodityInfos;
    }

    @Override
    public List<CommodityInfo> listCommoditiesByType(Integer type, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CommodityInfo> list = commodityDao.listCommoditiesByType((long) type);
        for (CommodityInfo commodityInfo : list) {
            commodityInfo.setSeller(userDao.selectSimpleUserByPrimaryKey(commodityInfo.getSellerId()));
            // TODO: 课程
        }
        return list;
    }

    @Override
    public CommodityInfo updateCommodityByPrimaryKey(Long id, CommodityParam commodityParam) {
        TabCommodity commodity = commodityMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(commodityParam, commodity);
        commodity.setModifyTime(new Date());
        commodityMapper.updateByPrimaryKey(commodity);
        CommodityInfo info = new CommodityInfo();
        BeanUtils.copyProperties(commodityMapper.selectByPrimaryKey(id), info);
        return info;
    }



    @Override
    public void deleteCommodityById(Long id) {
        TabCommodity tabCommodity = commodityMapper.selectByPrimaryKey(id);
        TabSoldCommodity tabSoldCommodity = new TabSoldCommodity();
        BeanUtils.copyProperties(tabCommodity,tabSoldCommodity);
        tabSoldCommodity.setCommodityId(tabCommodity.getId());
        soldCommodityMapper.insert(tabSoldCommodity);
        commodityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<CommodityInfo> getCommodities(Long userId, String lessonId, Boolean isSold, Boolean isMine, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if (isSold){
            if (isMine){
                if (null == lessonId){
                    return append(soldCommodityDao.listCommoditiesBySellerId(userId));
                }else{
                    return append(soldCommodityDao.listCommoditiesBySellerIdAndLessonId(userId, lessonId));
                }
            }else{
                //查看所有
                if (null == lessonId){
                    return append(soldCommodityDao.listCommodities());
                }else{
                    return append(soldCommodityDao.listCommoditiesByLessonId(lessonId));
                }
            }
        }else{
            if (isMine){
                if (null == lessonId){
                    return append(commodityDao.listCommoditiesBySellerId(userId));
                }else{
                    return append(commodityDao.listCommoditiesBySellerIdAndLessonId(userId, lessonId));
                }
            }else{
                //查看所有
                if (null == lessonId){
                    return append(commodityDao.listCommodities());
                }else{
                    return append(commodityDao.listCommoditiesByLessonId(lessonId));
                }
            }
        }
    }

    @Override
    public List<CommodityInfo> getSoldCommodityByUserId(Long user_id,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TabSoldCommodityExample example = new TabSoldCommodityExample();
        TabSoldCommodityExample.Criteria criteria = example.createCriteria();
        criteria.andSellerIdEqualTo(user_id);
        List<TabSoldCommodity> list = soldCommodityMapper.selectByExample(example);
        return convert(null, list);
    }

    private List<CommodityInfo> convert(List<TabCommodity> tabCommodities, List<TabSoldCommodity> tabSoldCommodities){
        List<CommodityInfo> result = new ArrayList<>();
        if (null == tabCommodities){
            CommodityInfo tmp = new CommodityInfo();
            for (TabCommodity tabCommodity : tabCommodities) {
                BeanUtils.copyProperties(tabCommodity, tmp);
                tmp.setSeller(userDao.selectSimpleUserByPrimaryKey(tabCommodity.getSellerId()));
                // TODO: 课程
                result.add(tmp);
            }
            return result;
        }else{
            CommodityInfo tmp = new CommodityInfo();
            for (TabSoldCommodity tabCommodity : tabSoldCommodities) {
                BeanUtils.copyProperties(tabCommodity, tmp);
                tmp.setSeller(userDao.selectSimpleUserByPrimaryKey(tabCommodity.getSellerId()));
                // TODO: 课程
                result.add(tmp);
            }
            return result;
        }
    }

    private List<CommodityInfo> append(List<CommodityInfo> tabCommodities){
        for (CommodityInfo tabCommodity : tabCommodities) {
            tabCommodity.setSeller(userDao.selectSimpleUserByPrimaryKey(tabCommodity.getSellerId()));
            // TODO: 课程
        }
        return tabCommodities;
    }
}
