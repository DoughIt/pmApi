package com.pm.pmapi.service;

import com.pm.pmapi.dto.MiniProgramSession;

/**
 * @Description 自定义小程序服务
 *
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-12-10 08:54
 */
public interface MiniProgramService {
    /**
     *  获取openId
     * @param code
     * @return
     */
    MiniProgramSession codeToSession(String code);
}
