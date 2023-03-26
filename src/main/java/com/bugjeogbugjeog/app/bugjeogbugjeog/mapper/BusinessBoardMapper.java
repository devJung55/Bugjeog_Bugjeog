package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardBusinessDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BusinessBoardMapper {
    //    추가
    public void insert(BoardBusinessDTO boardBusinessDTO);
    //    삭제
    public void delete(Long orderId);
    //    조회(이미지까지)
    public BoardBusinessDTO select(Long businessId);
    //    목록
    public List<BoardBusinessDTO> selectAll();
}
