package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.AdminCriteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.InquiryDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardInquiryVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InquiryBoardMapper {

    //    문의 작성 목록
    public List<BoardInquiryVO> inquireList(@Param("memberId") Long memberId, @Param("criteria") Criteria criteria);

    //    문의 게시글 작성 갯수
    public Integer inquireCount(Long memberId);

    //    문의 답변 여부
    public Long inquireAnswer(Long boardInquireId);

    // 유통업체 문의 작성 목록
    public List<BoardInquiryVO> businessInquireList(@Param("businessId") Long businessId, @Param("criteria") Criteria criteria);

    //    문의 게시글 작성 갯수
    public Integer businessInquireCount(Long businessId);

    /* 관리자 ------------------------------------------------------------------------ */

    // 문의 목록
    public List<BoardInquiryVO> adminSelectAllInquiry(AdminCriteria adminCriteria);

    // 문의 조회
    public InquiryDTO adminSelectInquiry(Long boardInquiryId);

    // 문의 삭제
    public void delete(Long boardInquiryId);

    // 문의 카운트
    public int count();

}
