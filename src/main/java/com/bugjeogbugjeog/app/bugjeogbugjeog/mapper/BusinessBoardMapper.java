package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.AdminCriteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardBusinessDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.PageDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BusinessBoardMapper {

    //    게시글 목록
    public List<BoardBusinessDTO> selectAll(@Param("cri") Criteria criteria, @Param("sort") String sort, @Param("category") String category);

    //    게시글 추가
    public void insert(BoardBusinessVO boardBusinessVO);

    //    현재 시퀀스 가져오기
    public Long selectSequence();

    //    썸네일 포함 목록
    public List<BoardBusinessDTO> selectAllList(PageDTO pageDTO);

    //    관리자 페이지 목록
    public List<BoardBusinessDTO> selectAllBusinessBoard(@Param("adminCriteria") AdminCriteria adminCriteria);

    //    pr게시판 목록 개수
    public int selectBoardCount();

    //    관리자 페이지 상세보기
    public BoardBusinessDTO selectBoard(Long boardBusinessId);

    //    보드아이디에 맞춰 이미지 정보 가져오기
    public List<BoardBusinessDTO> selectBoardImages(Long boardBusinessId);

    //    삭제
    public void delete(Long boardBusinessId);

}
