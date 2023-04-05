package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;


import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.AdminCriteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardBusinessDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.PageDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.SearchDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.enums.SearchEnum;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
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

//  게시글 목록 조회 페이징
    @Test
    public void selectAllListTest(){
        AdminCriteria adminCriteria = new AdminCriteria();
        adminCriteria.create(1, 5, 10, 1);
        businessBoardMapper.selectAllBusinessBoard(adminCriteria);
    }

//  게시글 상세조회
    @Test
    public void selectBoardTest(){
        businessBoardMapper.selectBoard(1L).toString();
    }

    @Test
    void selectAllListTestPaging () {
        Long total = businessBoardMapper.selectBoardCount();
        Criteria criteria = new Criteria(1, 5);
        businessBoardMapper.selectAllList(new PageDTO(criteria, total.intValue())
                .setSearchDTO(new SearchDTO().setOrderColumn(SearchEnum.BOARD_BUSINESS_GRADE_AVERAGE)));
    }
}
