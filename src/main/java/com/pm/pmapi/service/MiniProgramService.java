package com.pm.pmapi.service;

import com.pm.pmapi.dto.MiniProgramSession;

public interface MiniProgramService {
    /**
     *  获取openId
     * @param code
     * @return
     */
    MiniProgramSession codeToSession(String code);
}
