package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardFreeImgVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.FreeBoardImgMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FreeBoardImgDAO {
    private final FreeBoardImgMapper freeBoardImgMapper;

    /* 추가 */
    public void save(BoardFreeImgVO boardFreeImgVO){
        freeBoardImgMapper.insert(boardFreeImgVO);
    }

    /* 조회 */
    public List<BoardFreeImgVO> findListByBoardId(Long boardFreeId){
        return freeBoardImgMapper.selectAll(boardFreeId);
    }

    /* 삭제 */
    public void deleteByBoardId(Long boardFreeId){
        freeBoardImgMapper.delete(boardFreeId);
    }
}
