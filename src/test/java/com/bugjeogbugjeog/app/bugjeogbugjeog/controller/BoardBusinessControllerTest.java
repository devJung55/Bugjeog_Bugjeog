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
public class BoardBusinessControllerTest {
    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void registerTest() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get()).andReturn().getModelAndView();
//        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/business/write")).andReturn().getModelAndView().getModelMap().toString());
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/board/business/write")
////                .param("boardBusinessId", "1")
//        ).andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void deleteTest() throws Exception {
        //        mockMvc.perform(MockMvcRequestBuilders.get()).andReturn().getModelAndView();
//        mockMvc.perform(MockMvcRequestBuilders.post("/order/delete")
//                .param("orderId", "2")
//        ).andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void detailTest() throws Exception {
        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/business/list")).andReturn().getModelAndView().getModelMap().toString());
        log.info(mockMvc.perform(MockMvcRequestBuilders.post("/board/business/detail")
                .param("boardBusinessId", "3")
        ).andReturn().getModelAndView().getModelMap().toString());
    }

    @Test
    public void writeTest() throws Exception {
        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/business/write")).andReturn().getModelAndView().getModelMap().toString());
        log.info(mockMvc.perform(MockMvcRequestBuilders.post("/board/business/write")
                .param("boardBusinessTitle", "title1")
                .param("boardBusinessContent", "content1")
                .param("businessId", "3")
        ).andReturn().getModelAndView().getModelMap().toString());
    }

    @Test
    public void uploadTest() throws Exception {
        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/business/write")).andReturn().getModelAndView().getModelMap().toString());
        log.info(mockMvc.perform(MockMvcRequestBuilders.post("/board/business/save")
                .param("boardBusinessImgOriginalName", "imgname1")
                .param("businessId", "3")
        ).andReturn().getModelAndView().getModelMap().toString());
    }

    @Test
    public void writeOkTest() throws Exception {
        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/business/write")).andReturn().getModelAndView().getModelMap().toString());
        log.info(mockMvc.perform(MockMvcRequestBuilders.post("/board/business/write")
                .param("boardBusinessTitle", "title1")
                .param("boardBusinessContent", "content1")
        ).andReturn().getModelAndView().getModelMap().toString());
    }
}
