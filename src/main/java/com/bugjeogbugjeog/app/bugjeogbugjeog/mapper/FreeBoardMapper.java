package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardFreeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
    public List<BoardFreeVO> selectAll();
}

