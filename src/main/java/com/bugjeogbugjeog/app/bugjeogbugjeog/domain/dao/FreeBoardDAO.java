package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao;


import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardFreeVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.FreeBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FreeBoardDAO {
    private final FreeBoardMapper freeBoardMapper;

    //    게시글 추가
    public void save(BoardFreeVO boardFreeVO){
        freeBoardMapper.insert(boardFreeVO);
    }

    //    게시글 조회
    public BoardFreeVO findById(Long boardFreeId){
        return freeBoardMapper.select(boardFreeId);
    }

    //    게시글 수정
    public void setBoardVO(BoardFreeVO boardFreeVO){
        freeBoardMapper.update(boardFreeVO);
    }

    //    게시글 삭제
    public void delete(Long boardFreeId){
        freeBoardMapper.delete(boardFreeId);
    }

    //    게시글 전체 조회
    public List<BoardFreeVO> findAll(){
        return freeBoardMapper.selectAll();
    }
}
