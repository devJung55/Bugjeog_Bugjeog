package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.AdminCriteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardBusinessDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.BusinessBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BusinessBoardDAO {
    private final BusinessBoardMapper businessBoardMapper;

    //    게시글 목록
    public List<BoardBusinessDTO> findAll(Criteria criteria, String sort, String category){
        return businessBoardMapper.selectAll(criteria, sort, category);
    }

    //    게시글 추가
    public void save(BoardBusinessVO boardBusinessVO){
        businessBoardMapper.insert(boardBusinessVO);
    }

    //    현재 시퀀스 가져오기
    public Long findLastInsertId(){
        return businessBoardMapper.selectSequence();
    }

    //    관리자 페이지 목록
    public List<BoardBusinessDTO> findAllByPage(AdminCriteria adminCriteria){
        return businessBoardMapper.selectAllBusinessBoard(adminCriteria);
    }

    //    pr게시판 목록 개수
    public int findCount(){
        return businessBoardMapper.selectBoardCount();
    }

    //    관리자 페이지 상세보기
    public BoardBusinessDTO findBoardById(Long boardBusinessId){
        return businessBoardMapper.selectBoard(boardBusinessId);
    }

    //    보드아이디에 맞춰 이미지 정보 가져오기
    public List<BoardBusinessDTO> findImages(Long boardBusinessId){
        return businessBoardMapper.selectBoardImages(boardBusinessId);
    }

    //    삭제
    public void deleteFindById(Long boardBusinessId){businessBoardMapper.delete(boardBusinessId);}
}
