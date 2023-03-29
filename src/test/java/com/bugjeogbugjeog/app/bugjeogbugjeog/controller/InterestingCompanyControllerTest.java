package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@Slf4j
public class InterestingCompanyControllerTest {
    @Autowired
    InterestingCompanyController interestingCompanyController;

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;


    @BeforeEach
    public void setup() { mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();}

    @Test
    public void InterestingCompanyControllerTest1() throws Exception{
        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/mypage/specific/personalFavoriteList")).andReturn().getModelAndView().getModelMap().toString());
    }

}
