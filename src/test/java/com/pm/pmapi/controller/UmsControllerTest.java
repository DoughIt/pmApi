package com.pm.pmapi.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
    void register() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/ums/register"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(""));
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