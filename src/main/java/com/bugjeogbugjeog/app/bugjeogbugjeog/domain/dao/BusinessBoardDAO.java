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
    public void save(BoardBusinessDTO boardBusinessDTO){
        businessBoardMapper.insert(boardBusinessDTO);
    }
    //    삭제
//    public void deleteById(Long orderId);
    //    조회(이미지 정보까지)
    public BoardBusinessDTO findById(Long businessId){ return businessBoardMapper.select(businessId);}
    //    목록
    public List<BoardBusinessDTO> findAll(){ return businessBoardMapper.selectAll(); }


}
