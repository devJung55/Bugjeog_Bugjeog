package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardBusinessDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BusinessBoardMapper {
    //    추가
//    public void insert(OrderVO orderVO);
    //    삭제
//    public void delete(Long orderId);
    //    조회(총 결제 금액까지)
//    public OrderDTO select(Long orderId);
    //    목록(총 결제 금액까지)
    public List<BoardBusinessDTO> selectAll();
}
