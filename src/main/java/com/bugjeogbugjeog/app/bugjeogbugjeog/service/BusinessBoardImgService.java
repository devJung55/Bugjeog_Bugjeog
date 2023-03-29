package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.BusinessBoardImgDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessImgVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Qualifier("businessBoardImg")
@Primary
@RequiredArgsConstructor
public class BusinessBoardImgService {
    private final BusinessBoardImgDAO businessBoardImgDAO;

    public void registerImg(BoardBusinessImgVO boardBusinessImgVO) {
        businessBoardImgDAO.save(boardBusinessImgVO);
    }
}
