package com.pm.pmapi.component;

import org.springframework.security.core.Authentication;

/**
 * @Description 自定义接口获取认证信息
 *
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-12-07 11:01
 */
public interface IAuthenticationFacade {
    Authentication getAuthentication();
}
