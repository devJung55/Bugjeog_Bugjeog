package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessImgVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BusinessBoardImgMapper {

//    추가
    void insert(BoardBusinessImgVO boardBusinessImgVO);

    void insertList(List<BoardBusinessImgVO> boardBusinessImgVOs);

//    한 게시글 이미지 전체 조회
    List<BoardBusinessImgVO> selectAllByBoardBusinessId(Long boardBusinessId);


}
