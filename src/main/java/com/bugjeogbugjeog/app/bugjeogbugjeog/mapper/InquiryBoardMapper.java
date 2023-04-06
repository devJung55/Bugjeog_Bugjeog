package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.AdminCriteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardInquiryDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.InquiryDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardInquiryVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.InquiryCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InquiryBoardMapper {

    //    문의 작성하기
    public void insertWithMemberId(BoardInquiryVO boardInquiryVO);

    public void insertWithBusinessId(BoardInquiryVO boardInquiryVO);

    public void insert(BoardInquiryVO boardInquiryVO);

//    문의글 상세보기(작성자 타입 확인)
    public BoardInquiryDTO selectOneIsMember(Long boardInquiryId);

//    문의글 상세보기(일반회원)
    public BoardInquiryDTO selectOneMember(Long boardInquiryId);

//    문의글 상세보기(유통업자)
    public BoardInquiryDTO selectOneBusiness(Long boardInquiryId);

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

    /* 관리자 ------------------------------------------------------------------------ */

    // 문의 목록
    public List<BoardInquiryDTO> adminSelectAllInquiry(@Param("adminCriteria") AdminCriteria adminCriteria);

    // 문의 조회
    public InquiryDTO adminSelectInquiry(Long boardInquiryId);

    // 문의 삭제
    public void delete(Long boardInquiryId);

    // 문의 카운트
    public Long count();

    public List<BoardInquiryVO> showAllList(@Param("cri") InquiryCriteria criteria);

}
