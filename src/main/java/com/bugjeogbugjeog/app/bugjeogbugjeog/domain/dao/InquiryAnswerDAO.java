package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao;

<<<<<<< HEAD
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardInquiryDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardInquiryVO;
=======
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardInquiryAnswerVO;
>>>>>>> ddc8d66d5f91b5b6d83e4425ffeaae58a0fd8a4e
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.InquiryAnswerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InquiryAnswerDAO {
<<<<<<< HEAD
    private final InquiryAnswerMapper inquiryAnswerMapper;

    public List<BoardInquiryVO> findByBoardInquiryId(Long boardInquiryId){
        return inquiryAnswerMapper.selectAllByBoardInquiryId(boardInquiryId);
    }
=======

    private final InquiryAnswerMapper inquiryAnswerMapper;

    // 문의 작성
    public void setInquiry(BoardInquiryAnswerVO boardInquiryAnswerVO){inquiryAnswerMapper.insert(boardInquiryAnswerVO);}
>>>>>>> ddc8d66d5f91b5b6d83e4425ffeaae58a0fd8a4e

}
