package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardInquiryAnswerVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.InquiryAnswerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InquiryAnswerDAO {

    private final InquiryAnswerMapper inquiryAnswerMapper;

    // 문의 작성
    public void setInquiry(BoardInquiryAnswerVO boardInquiryAnswerVO){inquiryAnswerMapper.insert(boardInquiryAnswerVO);}

}
