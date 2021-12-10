package com.pm.pmapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MailServiceTest {
    @Autowired
    private MailService mailService;
    @Test
    void sendAuthCode() {

    }

    @Test
    void sendHtmlAuthCode() {
        mailService.sendHtmlAuthCode("16302010059@fudan.edu.cn","3342", 5,"16302010059");
    }
}