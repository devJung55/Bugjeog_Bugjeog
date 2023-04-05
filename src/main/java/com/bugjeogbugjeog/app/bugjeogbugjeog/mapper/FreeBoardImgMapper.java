package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardFreeImgVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FreeBoardImgMapper {

    /* 추가 */
    public void insert(BoardFreeImgVO boardFreeImgVO);

    /* 조회 */
    public List<BoardFreeImgVO> selectAll(Long boardFreeId);

    /* 삭제 */
    public void delete(Long boardFreeId);
}
