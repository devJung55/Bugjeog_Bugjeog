package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;


import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardFreeImgVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
public class FreeBoardImgMapperTest {
    @Autowired
    FreeBoardImgMapper freeBoardImgMapper;
    @Autowired
    BoardFreeImgVO boardFreeImgVO;

    @BeforeEach
    void setBoardFreeImgVO(){
        boardFreeImgVO.setBoardFreeId(2L);
        boardFreeImgVO.setBoardFreeImgOriginalName("테스트");
        boardFreeImgVO.setBoardFreeImgPath("테스트");
        boardFreeImgVO.setBoardFreeImgUuid("테스트");
    }

    @Test
    void insert() {
        freeBoardImgMapper.insert(boardFreeImgVO);
    }

    @Test
    void select() {
        freeBoardImgMapper.insert(boardFreeImgVO);
        freeBoardImgMapper.selectAll(2L);
    }

    @Test
    void delete() {
        freeBoardImgMapper.insert(boardFreeImgVO);
        freeBoardImgMapper.delete(2L);
    }
}
