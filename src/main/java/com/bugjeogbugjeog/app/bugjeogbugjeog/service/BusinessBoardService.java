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
//    public void insert(OrderVO orderVO);
    //    삭제
//    public void delete(Long orderId);
    //    조회(총 결제 금액까지)
//    public OrderDTO select(Long orderId);
    //    목록(총 결제 금액까지)
    public List<BoardBusinessDTO> getList(){ return businessBoardDAO.findAll(); }
}
