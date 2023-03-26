package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardBusinessDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.BusinessBoardService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BoardBusinessControllerTest {
    @Autowired
    BusinessBoardService businessBoardService;

    @Test
    public void registerTest(){
        BoardBusinessDTO dto = new BoardBusinessDTO();

        dto.setBusinessId(1L);
        dto.setBoardBusinessTitle("제목 0326 1311");
        dto.setBoardBusinessContent("내용 0326 1311");
        businessBoardService.registerBoard(dto);
    }

    @Test
    public void deleteTest() {
        businessBoardService.remove(2L);
    }




}
