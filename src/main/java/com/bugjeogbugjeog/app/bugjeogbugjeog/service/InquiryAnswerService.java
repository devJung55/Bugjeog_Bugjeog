package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.InquiryAnswerDAO;
<<<<<<< HEAD
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardBusinessDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardInquiryDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardInquiryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
=======
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardInquiryAnswerVO;
import lombok.RequiredArgsConstructor;
>>>>>>> ddc8d66d5f91b5b6d83e4425ffeaae58a0fd8a4e
import org.springframework.stereotype.Service;

import java.util.List;

@Service
<<<<<<< HEAD
@Qualifier("inquiryBoard")
@Primary
=======
>>>>>>> ddc8d66d5f91b5b6d83e4425ffeaae58a0fd8a4e
@RequiredArgsConstructor
public class InquiryAnswerService {
    private final InquiryAnswerDAO inquiryAnswerDAO;

<<<<<<< HEAD
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


=======
    // 문의 작성
    public void addInquire(BoardInquiryAnswerVO boardInquiryAnswerVO){
        inquiryAnswerDAO.setInquiry(boardInquiryAnswerVO);
    }

>>>>>>> ddc8d66d5f91b5b6d83e4425ffeaae58a0fd8a4e
}
