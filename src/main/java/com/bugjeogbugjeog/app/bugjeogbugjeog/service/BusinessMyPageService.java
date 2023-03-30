package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.*;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardFreeLikeDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardReplyDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MyPageInquireDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BusinessMyPageService {
    private final MemberDAO memberDAO;
    private final FreeLikeDAO freeLikeDAO;
    private final InquiryBoardDAO inquiryBoardDAO;
    private final FreeBoardDAO freeBoardDAO;
    private final ReplyDAO replyDAO;
    private final BusinessDAO businessDAO;

    //    유통업체 파일 저장
    public void businessFileSave(BusinessVO business){
        BusinessVO businessVO = businessDAO.findByIdToBusiness(business.getBusinessId());

        businessVO.setBusinessImgOriginalName(business.getBusinessImgOriginalName());
        businessVO.setBusinessImgPath(business.getBusinessImgPath());
        businessVO.setBusinessImgUuid(business.getBusinessImgUuid());

        businessDAO.updateLocation(businessVO);
    }

    //    유통업체 이름 변경
    public void updateBusinessCeoName(Long businessId, String businessCeoName){
        BusinessVO businessVO = businessDAO.findByIdToBusiness(businessId);
        businessVO.setBusinessCeoName(businessCeoName);
        businessDAO.updateLocation(businessVO);
    }

    //    유통업자 핸드폰 번호 변경
    public void updateBusinessPhoneNumber(Long businessId , String businessPhoneNumber){
        BusinessVO businessVO =businessDAO.findByIdToBusiness(businessId);
        businessVO.setBusinessPhoneNumber(businessPhoneNumber);
        businessDAO.updateLocation(businessVO);
    }

    // 유통업자 회사명 변경
    public void updateBusinessCompanyName(Long businessId , String businessCompanyName){
        BusinessVO businessVO =businessDAO.findByIdToBusiness(businessId);
        businessVO.setBusinessCompanyName(businessCompanyName);
        businessDAO.updateLocation(businessVO);
    }

    // 사업자 번호 중복검사
    public Boolean businessNumberCheck(String businessNumber){
        List<String> businessNumbers = businessDAO.findAllToBusinessNumber();
        boolean check = false;
        for (int i = 0; i < businessNumbers.size(); i++) {
            if(businessNumbers.get(i).equals(businessNumber)){
                check = true;
            }
        }
        return check;
    }

    // 사업자 번호 변경
    public void businessNumberUpdate(Long businessId , String businessNumber){
        BusinessVO businessVO =businessDAO.findByIdToBusiness(businessId);
        businessVO.setBusinessNumber(businessNumber);
        businessDAO.updateLocation(businessVO);
    }

    // 유통업자 비밀번호 변경
    public void businessPasswordUpdate(Long businessId , String businessPassword){
        BusinessVO businessVO =businessDAO.findByIdToBusiness(businessId);
        businessVO.setBusinessPassword(businessPassword);
        businessDAO.updateLocation(businessVO);
    }

    // 유통 조회
    public BusinessVO businessInfo(Long businessId){
        return businessDAO.findByIdToBusiness(businessId);
    }

    // 유통업자 회원 탈퇴
    public void businessWithdraw(Long businessId){
        businessDAO.removeById(businessId);
    }

    // 유통업자 자유게시판 목록 가져오기
    public List<BoardFreeVO> businessFreeBoardList(Long businessId, Criteria criteria){
        return freeBoardDAO.findByIdBusinessBoardFreeVO(businessId, criteria);
    }

    // 유통업자 자유게시판 개수
    public Integer businessFreeCount(Long businessId){
        return freeBoardDAO.getFreeBoardBusinessTotal(businessId);
    }

    //  유통업자 댓글 단 게시물 목록
    public BoardReplyDTO businessReplyBoardFreeList(Long businessId, Criteria criteria){
        BoardReplyDTO boardReplyDTO = new BoardReplyDTO();
        List<MemberVO> memberVOs = new ArrayList<>();
        List<BusinessVO> businessVOS = new ArrayList<>();
        List<BoardFreeVO> boardFreeVOS = replyDAO.findAllBoardFreeToBusiness(businessId,criteria);

        boardFreeVOS.stream().map(data -> data.getMemberId()).forEach(data -> memberVOs.add(memberDAO.findById(data)));
        boardFreeVOS.stream().map(data -> data.getBusinessId()).forEach(data -> businessVOS.add(businessDAO.findByIdToBusiness(data)));

        boardReplyDTO.setBusinessVOS(businessVOS);
        boardReplyDTO.setMemberVOS(memberVOs);
        boardReplyDTO.setBoardFreeVOS(boardFreeVOS);

        return boardReplyDTO;
    }

    // 댓글 단 게시판의 갯수
    public Integer businessReplyBoardFreeCount(Long businessId, Criteria criteria){
        return replyDAO.findAllBoardFreeToBusiness(businessId,criteria).size();
    }

    // 유통업자의 각 보드의 댓글 리스트
    public List<FreeReplyVO> businessReplyList(Long businessId, Long boardFreeId){
        return replyDAO.findAllFreeReplyToBusiness(businessId, boardFreeId);
    }

    // 유통업자가 쓴 댓글 갯수
    public Integer businessReplyCount(Long businessId){
        return replyDAO.getBusinessReplyTotal(businessId);
    };

    // 좋아요 한 게시물 목록
    public BoardFreeLikeDTO businessLikeList(Long businessId, Criteria criteria){
        BoardFreeLikeDTO boardFreeLikeDTO = new BoardFreeLikeDTO();
        List<MemberVO> memberVOs = new ArrayList<>();
        List<BusinessVO> businessVOS = new ArrayList<>();
        List<Integer> boardReplyCounts = new ArrayList<>();
        List<BoardFreeVO> boardFreeVOS = freeLikeDAO.findAllToBusinessLike(businessId,criteria);

        boardFreeVOS.stream().map(data -> data.getBoardFreeId()).forEach(data -> boardReplyCounts.add(replyDAO.getReplyCount(data)));
        boardFreeVOS.stream().map(data -> data.getMemberId()).forEach(data -> memberVOs.add(memberDAO.findById(data)));
        boardFreeVOS.stream().map(data -> data.getBusinessId()).forEach(data -> businessVOS.add(businessDAO.findByIdToBusiness(data)));

        boardFreeLikeDTO.setBoardReplyCounts(boardReplyCounts);
        boardFreeLikeDTO.setBusinessVOS(businessVOS);
        boardFreeLikeDTO.setMemberVOs(memberVOs);
        boardFreeLikeDTO.setBoardFreeVOs(boardFreeVOS);

        return boardFreeLikeDTO;
    }

    // 좋아요 게시물 갯수
    public Integer businessLikeCount(Long businessId){
        return freeLikeDAO.getBusinessCountToLike(businessId);
    }

    // 유통업체 문의 게시판 개수
    public Integer businessInquireCount(Long businessId){
        return inquiryBoardDAO.getBusinessInquireCount(businessId);
    };

    // 유통업체 문의 게시판 목록
    public MyPageInquireDTO businessInquireList(Long businessId, Criteria criteria){
        List<BoardInquiryVO> inquires = inquiryBoardDAO.findAllToBusiness(businessId,criteria);
        List<Long> status = new ArrayList<>();
        MyPageInquireDTO myPageInquireDTO = new MyPageInquireDTO();

        for(int i =0; i < inquires.size(); i++){
            Long inquiryBoardId = inquires.get(i).getBoardInquiryId();
            status.add(inquiryBoardDAO.inquireAnswer(inquiryBoardId));
        }

        myPageInquireDTO.setAnswerStatus(status);
        myPageInquireDTO.setInquire(inquires);

        return myPageInquireDTO;
    }

    // 게시판 각각의 개수
    public Map<String, Object> businessAllCount(Long businessId){
        Map<String, Object> allCount = new HashMap<>();

        allCount.put("freeBoardCount",freeBoardDAO.getFreeBoardBusinessTotal(businessId));
        allCount.put("replyCount", replyDAO.getBusinessReplyTotal(businessId));
        allCount.put("likeBoardCount", freeLikeDAO.getBusinessCountToLike(businessId));
        allCount.put("inquireCount", inquiryBoardDAO.getBusinessInquireCount(businessId));
        allCount.put("reviewGrade", businessDAO.getReviewGrade(businessId));
        allCount.put("reviewCount", businessDAO.getReviewCount(businessId));

        return allCount;
    }

    //    유통 분야 설정 수정
    public void updateLocation(BusinessVO businessVO) {
        businessDAO.updateLocation(businessVO);
    }

}
