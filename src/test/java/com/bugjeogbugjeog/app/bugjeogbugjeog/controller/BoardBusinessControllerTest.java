package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardBusinessDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessVO;
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
        BoardBusinessVO vo = new BoardBusinessVO();

        vo.setBusinessId(1L);
        vo.setBoardBusinessTitle("제목 0326 1311");
        vo.setBoardBusinessContent("내용 0326 1311");
        businessBoardService.registerBoard(vo);
    }

    @Test
    public void deleteTest() {
        businessBoardService.remove(2L);
    }




}
