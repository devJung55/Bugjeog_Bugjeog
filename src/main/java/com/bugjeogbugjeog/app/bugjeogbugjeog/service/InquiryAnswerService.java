package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.InquiryAnswerDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardInquiryAnswerVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardInquiryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("inquiryBoard")
@Primary
@RequiredArgsConstructor
public class InquiryAnswerService {
    private final InquiryAnswerDAO inquiryAnswerDAO;

    //    추가
//    public void registerBoard(BoardBusinessVO boardBusinessVO) {
//        businessBoardDAO.save(boardBusinessVO);
//    }

    //    삭제
//    public void remove(Long businessId) { businessBoardDAO.deleteById(businessId); }

    //    조회
    public List<BoardInquiryVO> getAnswersByBoardInquiryId(Long boardInquiryId) {
        return inquiryAnswerDAO.findByBoardInquiryId(boardInquiryId);
    }

//    public List<BoardBusinessDTO> getBoardsByBusinessId(Long businessId) {
//        return businessBoardDAO.findByBusinessId(businessId);
//    }

    //    목록(대표 이미지 하나)
//    public List<BoardBusinessDTO> getList() {
//        return businessBoardDAO.findAll();
//    }

    //    목록(대표 이미지 하나 + 검색)
//    public List<BoardBusinessDTO> getList(Map<String, Object> searchMap) {
//        return businessBoardDAO.findAll(searchMap);
//    }


    // 문의 작성
    public void addInquire(BoardInquiryAnswerVO boardInquiryAnswerVO){
        inquiryAnswerDAO.setInquiry(boardInquiryAnswerVO);
    }

}
