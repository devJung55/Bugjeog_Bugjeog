package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@Slf4j
public class MyPageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
//    요청을 처리해주는 WebApplicationContext를 불러온다.
    private WebApplicationContext webApplicationContext;

    @Autowired
    MyPageController myPageController;

}
