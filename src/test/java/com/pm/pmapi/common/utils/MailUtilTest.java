package com.pm.pmapi.common.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MailUtilTest {
    @Autowired
    MailUtil mailUtil;
    @Test
    void sendSimpleMail() {
        mailUtil.sendSimpleMail("fitymistudio@gmail.com","这是测试用邮件","测试");
    }

    @Test
    void sendHtmlMail() {

    }
}