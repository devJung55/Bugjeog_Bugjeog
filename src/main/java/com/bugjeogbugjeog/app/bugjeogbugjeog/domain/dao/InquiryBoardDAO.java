package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardInquiryVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.InquiryBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InquiryBoardDAO {

    private final InquiryBoardMapper inquiryBoardMapper;

    //    문의 작성 목록
    public List<BoardInquiryVO> findAllByIdToInquire(Long memberId, Criteria criteria){
        return inquiryBoardMapper.inquireList(memberId,criteria);
    };

    //    문의 게시글 작성 갯수
    public Integer getCountToInquire(Long memberId){
        return inquiryBoardMapper.inquireCount(memberId);
    };

    // 문의 답변 여부
    public Long inquireAnswer(Long boardInquireId){
        return inquiryBoardMapper.inquireAnswer(boardInquireId);
    }
}
