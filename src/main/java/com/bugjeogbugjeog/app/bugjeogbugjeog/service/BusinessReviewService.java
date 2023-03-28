package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.BusinessReviewDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BusinessReviewDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessReviewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("review")
@Primary
@RequiredArgsConstructor
public class BusinessReviewService {
    private final BusinessReviewDAO businessReviewDAO;

    public List<BusinessReviewDTO> getReply(long boardBusinessId) {
        return businessReviewDAO.findById(boardBusinessId);
    }

    public BusinessReviewDTO getMember(Long memberId){
        return businessReviewDAO.findByMemberId(memberId);
    }

    //    추가
//    public void registerBoard(BoardBusinessVO boardBusinessVO) {
//        businessBoardDAO.save(boardBusinessVO);
//    }

    //    삭제
//    public void remove(Long businessId) {
//        businessBoardDAO.deleteById(businessId);
//    }

    //    조회(이미지들까지)
//    public BoardBusinessDTO getBoard(Long boardBusinessId) {
//        return businessBoardDAO.findById(boardBusinessId);
//    }

    //    목록(대표 이미지 하나)
//    public List<BoardBusinessDTO> getList() {
//        return businessBoardDAO.findAll();
//    }

    //    목록(대표 이미지 하나)
//    public List<BoardBusinessDTO> getList(Map<String, Object> searchMap) {
//        return businessBoardDAO.findAll(searchMap);
//    }

}
