package com.bugjeogbugjeog.app.bugjeogbugjeog.service;


import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BusinessBoardServiceTests {

    @Autowired
    BoardBusinessService businessBoardService;

    @Test
    public void registerTest(){
        BoardBusinessVO vo = new BoardBusinessVO();
        vo.setBusinessId(1L);
        vo.setBoardBusinessContent("내용 1459");
        vo.setBoardBusinessTitle("제목 1459");
        businessBoardService.registerBoard(vo);
    }

    @Test
    public void selectTest(){
        log.info(businessBoardService.getBoard(1L).toString());
    }


}
