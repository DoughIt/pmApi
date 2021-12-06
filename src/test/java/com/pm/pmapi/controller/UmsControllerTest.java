package com.pm.pmapi.controller;

import cn.hutool.json.JSONArray;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Random;

@SpringBootTest
@AutoConfigureMockMvc
class UmsControllerTest {
    @Autowired
    private UmsController umsController;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testNotNull() {
        Assertions.assertThat(umsController).isNotNull();
    }

    @Test
    void register() throws Exception {
        Random random = new Random();
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/ums/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("studentId", "" + random.nextInt(1000))
                        .param("password", "123456")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void login() {
    }

    @Test
    void refreshToken() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void logout() {
    }
}