package com.pm.pmapi.service;

/**
 * @Description 自定义邮件服务
 *
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-12-10 08:54
 */
public interface MailService {
    /**
     * 发送验证码
     * @param receiver
     * @param code
     */
    void sendAuthCode(String receiver, String code);

    /**
     * 内容为html模板，发送包含账号信息的验证码
     * @param receiver
     * @param code
     * @param minutes
     * @param username
     */
    void sendHtmlAuthCode(String receiver, String code, Integer minutes, String username);
}
