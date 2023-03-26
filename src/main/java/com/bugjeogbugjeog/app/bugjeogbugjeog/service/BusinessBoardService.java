package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.BusinessBoardDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.BusinessBoardImgDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardBusinessDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("businessBoard")
@Primary
@RequiredArgsConstructor
public class BusinessBoardService {
    private final BusinessBoardDAO businessBoardDAO;
    private final BusinessBoardImgDAO businessBoardImgDAO;

    //    추가
    public void registerBoard(BoardBusinessDTO boardBusinessDTO){
        businessBoardDAO.save(boardBusinessDTO);
    }
    //    삭제
//    public void remove(Long orderId);
    
    //    조회(총 결제 금액까지)
    public BoardBusinessDTO getBoard(Long businessId){
        return businessBoardDAO.findById(businessId);
    }
    //    목록(총 결제 금액까지)
    public List<BoardBusinessDTO> getList(){ return businessBoardDAO.findAll(); }
}
