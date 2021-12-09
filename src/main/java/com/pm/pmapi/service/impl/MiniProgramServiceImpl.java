package com.pm.pmapi.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import com.pm.pmapi.dto.MiniProgramSession;
import com.pm.pmapi.mbg.mapper.TabMiniProgramMapper;
import com.pm.pmapi.mbg.model.TabMiniProgram;
import com.pm.pmapi.mbg.model.TabMiniProgramExample;
import com.pm.pmapi.service.MiniProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MiniProgramServiceImpl implements MiniProgramService {

    @Autowired
    private TabMiniProgramMapper miniProgramMapper;

    /**
     * 获取openId
     *
     * @param code
     * @return
     */
    @Override
    public MiniProgramSession codeToSession(String code) {
        Map<String, String> data = new HashMap<>();
        TabMiniProgram miniProgram = getMiniProgram();
        data.put("appid", miniProgram.getAppId());
        data.put("secret", miniProgram.getAppSecret());
        data.put("js_code", code);
        data.put("grant_type", "authorization_code");
        String response = HttpRequest.get("https://api.weixin.qq.com/sns/jscode2session").form(data).body();
        return JSONObject.parseObject(response, MiniProgramSession.class);
    }

    private TabMiniProgram getMiniProgram() {
        TabMiniProgramExample miniProgramExample = new TabMiniProgramExample();
        miniProgramExample.createCriteria().andAppIdIsNotNull();
        List<TabMiniProgram> miniProgramList = miniProgramMapper.selectByExample(miniProgramExample);
        if (miniProgramList != null && miniProgramList.size() > 0) {
            return miniProgramList.get(0);
        }
        return null;
    }
}
