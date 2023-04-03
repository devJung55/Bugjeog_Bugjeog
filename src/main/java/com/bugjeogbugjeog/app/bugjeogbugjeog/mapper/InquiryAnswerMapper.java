package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardInquiryAnswerVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardInquiryVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InquiryAnswerMapper {

    // 문의 작성
    public void insert(BoardInquiryAnswerVO boardInquiryAnswerVO);

}
