package com.bugjeogbugjeog.app.bugjeogbugjeog.service;


import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardBusinessDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BusinessBoardServiceTests {

    @Autowired
    BusinessBoardService businessBoardService;

    @Test
    public void registerTest(){
        BoardBusinessDTO dto = new BoardBusinessDTO();
        dto.setBusinessId(1L);
        dto.setBoardBusinessContent("내용 1459");
        dto.setBoardBusinessTitle("제목 1459");
        businessBoardService.registerBoard(dto);
    }


}
