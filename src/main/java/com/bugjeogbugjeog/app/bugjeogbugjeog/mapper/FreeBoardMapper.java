package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardBusinessDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardFreeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FreeBoardMapper {
    //    게시글 추가
    public void insert(BoardFreeVO boardFreeVO);

    //    게시글 조회
    public BoardFreeVO select(Long boardFreeId);

    //    게시글 수정
    public void update(BoardFreeVO boardFreeVO);

    //    게시글 삭제
    public void delete(Long boardFreeId);

    //    게시글 전체 조회
//    public List<BoardFreeVO> selectAll();

//    비즈니스 참고하여 추가

    //    썸네일 포함 목록 = 전체 조회
    public List<BoardFreeVO> selectAllList();

    //    썸네일 포함 목록
    public List<BoardFreeVO> selectAllListBySearch(Map<String, Object> searchMap);
}

