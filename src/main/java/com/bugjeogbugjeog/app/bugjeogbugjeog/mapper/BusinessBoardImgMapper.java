package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessImgVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BusinessBoardImgMapper {

//    추가
    void insert(BoardBusinessImgVO boardBusinessImgVO);
}
