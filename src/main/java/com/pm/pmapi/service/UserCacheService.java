package com.pm.pmapi.service;


import com.pm.pmapi.mbg.model.TabUser;

/**
 * @Description 用户缓存
 *
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-12-04 11:39
 */
public interface UserCacheService {
    /**
     * 删除后台用户缓存
     */
    void delUser(Long userId);

    /**
     * 获取缓存后台用户信息
     */
    TabUser getUser(Long userId);

    /**
     * 设置缓存后台用户信息
     */
    void setUser(TabUser user);

}
