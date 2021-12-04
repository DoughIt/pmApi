package com.pm.pmapi.service.impl;

import com.pm.pmapi.mbg.model.TabUser;
import com.pm.pmapi.service.RedisService;
import com.pm.pmapi.service.UserCacheService;
import com.pm.pmapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserCacheServiceImpl implements UserCacheService {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;

    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.user}")
    private String REDIS_KEY_USER;

    /**
     * 删除后台用户缓存
     *
     * @param userId
     */
    @Override
    public void delUser(Long userId) {
        TabUser user = userService.getUserById(userId);
        if (user != null) {
            String key = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + user.getId();
            redisService.del(key);
        }
    }

    /**
     * 获取缓存后台用户信息
     *
     * @param userId
     */
    @Override
    public TabUser getUser(Long userId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + userId;
        return (TabUser) redisService.get(key);
    }

    /**
     * 设置缓存后台用户信息
     *
     * @param user
     */
    @Override
    public void setUser(TabUser user) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + user.getId();
        redisService.set(key, user, REDIS_EXPIRE);
    }
}
