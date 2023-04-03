package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.InquiryAnswerDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardInquiryAnswerVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InquiryAnswerService {
    private final InquiryAnswerDAO inquiryAnswerDAO;

    // 문의 작성
    public void addInquire(BoardInquiryAnswerVO boardInquiryAnswerVO){
        inquiryAnswerDAO.setInquiry(boardInquiryAnswerVO);
    }

}
