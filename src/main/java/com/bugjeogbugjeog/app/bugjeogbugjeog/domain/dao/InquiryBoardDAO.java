package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.AdminCriteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardInquiryDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.InquiryDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardInquiryVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.InquiryCriteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.InquiryBoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class InquiryBoardDAO {

    private final InquiryBoardMapper inquiryBoardMapper;

    //    문의 작성하기
    public void save(BoardInquiryVO boardInquiryVO, String memberType) {
        switch (memberType) {
            case "business":
                inquiryBoardMapper.insertWithBusinessId(boardInquiryVO);
                break;
            case "member":
                inquiryBoardMapper.insertWithMemberId(boardInquiryVO);
                break;
            default:
                ;
        }
    }

    public void save(BoardInquiryVO boardInquiryVO) {
        inquiryBoardMapper.insert(boardInquiryVO);
    }


    //    문의글 상세보기
    public BoardInquiryDTO findOneByBoardInquiryId(Long boardInquiryId) {
        boolean isMember = inquiryBoardMapper.selectOneIsMember(boardInquiryId).getBusinessId() == null;
        System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆");
        System.out.println(isMember);
        System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆");
        BoardInquiryDTO dto;
        if (isMember) {
            dto = inquiryBoardMapper.selectOneMember(boardInquiryId);
            dto.setWriterType("member");
        } else {
            dto = inquiryBoardMapper.selectOneBusiness(boardInquiryId);
            dto.setWriterType("business");
        }
        return dto;
    }

    //    문의글 상세보기
    public BoardInquiryDTO findOneByBoardInquiryIdBusiness(Long boardInquiryId) {
        return inquiryBoardMapper.selectOneIsMember(boardInquiryId);
    }

    public List<BoardInquiryDTO> findAllByIdToInquire() {
        return inquiryBoardMapper.inquiryList();
    }


    //    문의 작성 목록
    public List<BoardInquiryVO> findAllByIdToInquire(Long memberId, Criteria criteria) {
        return inquiryBoardMapper.inquireList(memberId, criteria);
    }

    //    문의 게시글 작성 갯수
    public Integer getCountToInquire(Long memberId) {
        return inquiryBoardMapper.inquireCount(memberId);
    }

    // 문의 답변 여부
    public Long inquireAnswer(Long boardInquireId) {
        return inquiryBoardMapper.inquireAnswer(boardInquireId);
    }

    // 유통업체 문의 작성 목록
    public List<BoardInquiryVO> findAllToBusiness(@Param("businessId") Long businessId, @Param("criteria") Criteria criteria) {
        return inquiryBoardMapper.businessInquireList(businessId, criteria);
    }

    //    문의 게시글 작성 갯수
    public Integer getBusinessInquireCount(Long businessId) {
        return inquiryBoardMapper.businessInquireCount(businessId);
    }

    /* 관리자 ------------------------------------------------------------------------ */

    // 문의 목록
    public List<BoardInquiryDTO> getInquiryList(AdminCriteria adminCriteria) {
        return inquiryBoardMapper.adminSelectAllInquiry(adminCriteria);
    }

    // 문의 조회
    public InquiryDTO getInquiry(Long boardInquiryId) {
        return inquiryBoardMapper.adminSelectInquiry(boardInquiryId);
    }

    // 문의 삭제
    public void deleteInquiry(Long boardInquiryId) {
        inquiryBoardMapper.delete(boardInquiryId);
    }

    // 문의 카운트
    public Long count() {
        return inquiryBoardMapper.count();
    }

    public List<BoardInquiryVO> findAllList(InquiryCriteria criteria){
        return inquiryBoardMapper.showAllList(criteria);
    }

}
