package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardBusinessDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.BusinessBoardImgMapper;
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.BusinessBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BusinessBoardDAO {
    private final BusinessBoardMapper businessBoardMapper;
    private final BusinessBoardImgMapper businessBoardImgMapper;

    //    추가
//    public void insert(OrderVO orderVO);
    //    삭제
//    public void delete(Long orderId);
    //    조회(총 결제 금액까지)
//    public OrderDTO select(Long orderId);
    //    목록(총 결제 금액까지)
    public List<BoardBusinessDTO> findAll(){ return businessBoardMapper.selectAll(); }


}
