package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardInquiryDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardInquiryVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InquiryBoardMapper {

    //    문의 작성하기
    public void insert(BoardInquiryVO boardInquiryVO);

    //    문의 작성 목록(게시글용 전체)
    public List<BoardInquiryDTO> inquiryList();

    //    문의 작성 목록(멤버용)
    public List<BoardInquiryVO> inquireList(@Param("memberId") Long memberId, @Param("criteria") Criteria criteria);

    //    문의 게시글 작성 갯수
    public Integer inquireCount(Long memberId);

    //    문의 답변 여부
    public Long inquireAnswer(Long boardInquireId);

    // 유통업체 문의 작성 목록
    public List<BoardInquiryVO> businessInquireList(@Param("businessId") Long businessId, @Param("criteria") Criteria criteria);

    //    문의 게시글 작성 갯수
    public Integer businessInquireCount(Long businessId);
}
