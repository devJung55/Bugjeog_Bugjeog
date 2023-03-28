package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardBusinessDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BusinessReviewDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.BusinessReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BusinessReviewDAO {
    private final BusinessReviewMapper businessReviewMapper;

    //    추가
    public void save(BoardBusinessVO boardBusinessVO) {
//    businessBoardMapper.insert(boardBusinessVO);
    }

    public BusinessReviewDTO findByMemberId(Long memberId){
        return businessReviewMapper.selectByMemberId(memberId);
    }

    //    삭제
    public void deleteById(Long boardBusinessId) {
//    businessBoardMapper.delete(boardBusinessId);
    }

    //    조회(이미지 정보까지)
    public List<BusinessReviewDTO> findById(Long boardBusinessId) {
        return businessReviewMapper.select(boardBusinessId);
    }

    //    목록(대표 이미지 하나)
    public List<BoardBusinessDTO> findAll() {
//        return businessBoardMapper.selectAllList();
        return null;
    }

    //    목록(대표 이미지 하나)
    public List<BoardBusinessDTO> findAll(Map<String, Object> searchMap) {
//        return businessBoardMapper.selectAllListBySearch(searchMap);
        return null;
    }

}
