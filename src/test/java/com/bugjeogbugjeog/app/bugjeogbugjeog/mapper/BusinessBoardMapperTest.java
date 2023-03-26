package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;


import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardBusinessDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BusinessBoardMapperTest {

    @Autowired
    BusinessBoardMapper businessBoardMapper;

    @Test
    public void insertTest(){
        BoardBusinessDTO dto = new BoardBusinessDTO();
        dto.setBusinessId(1L);
        dto.setBoardBusinessContent("내용3");
        dto.setBoardBusinessTitle("제목3");

        businessBoardMapper.insert(dto);
    }

//
    @Test
    public void selectAllTest(){ log.info(businessBoardMapper.selectAll().toString()); }


}
