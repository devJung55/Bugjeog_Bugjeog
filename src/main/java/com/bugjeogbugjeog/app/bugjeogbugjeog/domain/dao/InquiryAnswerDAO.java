package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardInquiryDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardInquiryVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.InquiryAnswerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InquiryAnswerDAO {
    private final InquiryAnswerMapper inquiryAnswerMapper;

    public List<BoardInquiryVO> findByBoardInquiryId(Long boardInquiryId){
        return inquiryAnswerMapper.selectAllByBoardInquiryId(boardInquiryId);
    }

}
