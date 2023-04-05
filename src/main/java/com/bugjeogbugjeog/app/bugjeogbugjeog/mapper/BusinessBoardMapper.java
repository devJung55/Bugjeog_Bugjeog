package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.AdminCriteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardBusinessDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.PageDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BusinessBoardMapper {

    //    추가
    public void insert(BoardBusinessVO boardBusinessVO);

    //    삭제
    public void delete(Long orderId);

    //    조회(이미지까지)
    public BoardBusinessDTO select(Long boardBusinessId);

    //    목록
    public List<BoardBusinessDTO> selectAll();

    public List<BoardBusinessDTO> selectAllByBusinessId(Long businessId);

    //    썸네일 포함 목록
    public List<BoardBusinessDTO> selectAllList(PageDTO pageDTO);

    //    썸네일 포함 목록
    public List<BoardBusinessDTO> selectAllListBySearch(Map<String, Object> searchMap);

    //    관리자 페이지 목록
    public List<BoardBusinessDTO> selectAllBusinessBoard(@Param("adminCriteria") AdminCriteria adminCriteria);

    //    pr게시판 목록 개수
    public Long selectBoardCount();

    //    관리자 페이지 상세보기
    public BoardBusinessDTO selectBoard(Long boardBusinessId);

    //    보드아이디에 맞춰 이미지 정보 가져오기
    public List<BoardBusinessDTO> selectBoardImages(Long boardBusinessId);
}
