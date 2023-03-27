package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;


import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardBusinessDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BusinessBoardMapperTest {

    @Autowired
    BusinessBoardMapper businessBoardMapper;

//    게시글 등록
    @Test
    public void insertTest(){
        BoardBusinessVO vo = new BoardBusinessVO();
        vo.setBusinessId(1L);
        vo.setBoardBusinessContent("내용3");
        vo.setBoardBusinessTitle("제목3");

        businessBoardMapper.insert(vo);
    }

//    게시글 삭제
    @Test
    public void deleteTest(){
        businessBoardMapper.delete(1L);
    }

//  게시글 목록
    @Test
    public void selectAllTest(){ businessBoardMapper.selectAll(); }


}
