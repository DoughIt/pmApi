package com.pm.pmapi.service.impl;

import com.pm.pmapi.common.utils.MailUtil;
import com.pm.pmapi.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @author Jerry Zhang <https://github.com/doughit>
 * @Description 自定义邮件服务实现类
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @date 2021-12-10 09:09
 */
@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private MailUtil mailUtil;
    @Autowired
    private TemplateEngine engine;

    /**
     * 发送验证码
     *
     * @param receiver
     * @param code
     */
    @Override
    public void sendAuthCode(String receiver, String code) {
        mailUtil.sendSimpleMail(receiver, "验证码", "这是验证码：" + code + "，5分钟内有效。");
    }

    /**
     * 内容为html模板，发送包含账号信息的验证码
     *
     * @param receiver
     * @param code
     * @param minutes
     * @param username
     */
    @Override
    public void sendHtmlAuthCode(String receiver, String code, Integer minutes, String username) {
        Context context = new Context();
        context.setVariable("minutes", minutes);
        context.setVariable("username", username);
        context.setVariable("authCode", code);
        String emailContent = engine.process("authCodeTemplate", context);
        mailUtil.sendHtmlMail(receiver, "复旦邮箱验证码", emailContent);
    }
}
