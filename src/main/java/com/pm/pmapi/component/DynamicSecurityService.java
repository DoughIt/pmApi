package com.pm.pmapi.component;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * @Description 动态权限相关业务接口
 *
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-10-15 19:38
 */
public interface DynamicSecurityService {

    /**
     * 加载资源ANT通配符和资源对应MAP
     */
    Map<String, ConfigAttribute> loadDataSource();
}
