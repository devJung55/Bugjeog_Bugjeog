package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessImgVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.BusinessBoardImgMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BusinessBoardImgDAO {
    private final BusinessBoardImgMapper businessBoardImgMapper;

    public void save(BoardBusinessImgVO boardBusinessImgVO) {
        businessBoardImgMapper.insert(boardBusinessImgVO);
    }
}
