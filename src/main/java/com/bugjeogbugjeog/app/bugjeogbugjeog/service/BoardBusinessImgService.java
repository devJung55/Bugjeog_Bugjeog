package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.BusinessBoardImgDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessImgVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("businessBoardImg")
@Primary
@RequiredArgsConstructor
public class BoardBusinessImgService {
    private final BusinessBoardImgDAO businessBoardImgDAO;

    public void writeList(List<BoardBusinessImgVO> boardBusinessImgVOs){
//        businessBoardImgDAO.saveList(boardBusinessImgVOs);
        boardBusinessImgVOs.stream().forEach(boardBusinessImgVO -> businessBoardImgDAO.save(boardBusinessImgVO));
    }


    public void registerImg(BoardBusinessImgVO boardBusinessImgVO) {
        businessBoardImgDAO.save(boardBusinessImgVO);
    }

    public List<BoardBusinessImgVO> getList(Long boardBusinessId){
        return businessBoardImgDAO.findAllByBoardBusinessId(boardBusinessId);
    }
}
