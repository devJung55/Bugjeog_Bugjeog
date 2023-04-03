package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardBusinessDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardFreeDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardFreeVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface FreeBoardMapper {
    //    게시글 추가
    public void insert(BoardFreeVO boardFreeVO);

    //    게시글 조회
//    public BoardFreeVO select(Long boardFreeId);
    public BoardFreeDTO select(Long boardFreeId);

    //    게시글 수정
    public void update(BoardFreeVO boardFreeVO);

    //    게시글 삭제
    public void delete(Long boardFreeId);

    //    게시글 전체 조회
   public List<BoardFreeVO> selectAll();

//    비즈니스 참고하여 추가

    //    썸네일 포함 목록 = 전체 조회
    public List<BoardFreeVO> selectAllList();

    //    썸네일 포함 목록
    public List<BoardFreeVO> selectAllListBySearch(Map<String, Object> searchMap);

    /*------------------------------------------------------------------------*/
    //  자유 게시판 목록
    public List<BoardFreeDTO> freeList(@Param("memberId") Long memberId, @Param("criteria") Criteria criteria);
//    public List<BoardFreeVO> freeList(@Param("memberId") Long memberId, @Param("criteria") Criteria criteria);

    //    자유 게시판 개수
    public Integer freeCount(Long memberId);

    //  자유 게시판 목록
    public List<BoardFreeVO> businessFreeList(@Param("businessId") Long businessId, @Param("criteria") Criteria criteria);

    //    자유 게시판 개수
    public Integer businessFreeCount(Long businessId);
}

