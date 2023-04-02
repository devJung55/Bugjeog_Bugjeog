package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessImgVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.BoardBusinessImgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Slf4j
public class BoardImgControllerTest {
    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

//    @Test
//    public void registerTest() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/board/business/write")
////                .param("boardBusinessId", "1")
//        ).andExpect(MockMvcResultMatchers.status().is3xxRedirection());
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/imgs/business/upload")).andReturn().getModelAndView();
//        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/imgs/business/upload")).andReturn().getModelAndView().getModelMap().toString());
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/board/business/write")
////                .param("boardBusinessId", "1")
//        ).andExpect(MockMvcResultMatchers.status().is3xxRedirection());
//    }

//    @Test
//    public void deleteTest() throws Exception {
//        //        mockMvc.perform(MockMvcRequestBuilders.get()).andReturn().getModelAndView();
////        mockMvc.perform(MockMvcRequestBuilders.post("/order/delete")
////                .param("orderId", "2")
////        ).andExpect(MockMvcResultMatchers.status().is3xxRedirection());
//    }

//    @Test
//    public void detailTest() throws Exception {
//        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/business/list")).andReturn().getModelAndView().getModelMap().toString());
//        log.info(mockMvc.perform(MockMvcRequestBuilders.post("/board/business/detail")
//                .param("boardBusinessId", "3")
//        ).andReturn().getModelAndView().getModelMap().toString());
//    }
//
//    @Test
//    public void writeTest() throws Exception {
//        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/business/write")).andReturn().getModelAndView().getModelMap().toString());
//        log.info(mockMvc.perform(MockMvcRequestBuilders.post("/board/business/write")
//                .param("boardBusinessTitle", "title1")
//                .param("boardBusinessContent", "content1")
//                .param("businessId", "3")
//        ).andReturn().getModelAndView().getModelMap().toString());
//    }
//
//    @Test
//    public void uploadTest() throws Exception {
//        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/business/write")).andReturn().getModelAndView().getModelMap().toString());
//        log.info(mockMvc.perform(MockMvcRequestBuilders.post("/board/business/save")
//                .param("boardBusinessImgOriginalName", "imgname1")
//                .param("businessId", "3")
//        ).andReturn().getModelAndView().getModelMap().toString());
//    }
//
//    @Test
//    public void writeOkTest() throws Exception {
//        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/business/write")).andReturn().getModelAndView().getModelMap().toString());
//        log.info(mockMvc.perform(MockMvcRequestBuilders.post("/board/business/write")
//                .param("boardBusinessTitle", "title1")
//                .param("boardBusinessContent", "content1")
//        ).andReturn().getModelAndView().getModelMap().toString());
//    }
}
