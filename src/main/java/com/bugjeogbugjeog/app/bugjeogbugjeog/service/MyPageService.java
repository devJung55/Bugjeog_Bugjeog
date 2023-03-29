package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.*;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardFreeLikeDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardReplyDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MyPageInquireDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MyPageReplyDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.*;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final MemberDAO memberDAO;
    private final FreeLikeDAO freeLikeDAO;
    private final InquiryBoardDAO inquiryBoardDAO;
    private final FreeBoardDAO freeBoardDAO;
    private final ReplyDAO replyDAO;
    private final BusinessDAO businessDAO;

    //    회원 정보 조회
    public MemberVO memberInfo(Long memberId){
        return memberDAO.findById(memberId);
    };

    //    회원 정보 수정
    public void memberUpdate(MemberVO memberVO) {
        memberDAO.updateById(memberVO);
    }

    //    회원 탈퇴
    public void memberWithdraw(Long memberId) { memberDAO.deleteById(memberId);}

    //    파일 저장
    public void fileSave(MemberVO member){
        MemberVO memberVO = memberDAO.findById(member.getMemberId());

        memberVO.setMemberImgOriginalName(member.getMemberImgOriginalName());
        memberVO.setMemberImgPath(member.getMemberImgPath());
        memberVO.setMemberImgUuid(member.getMemberImgUuid());

        memberDAO.updateById(memberVO);
    }

    //    이름 변경
    public void updateName(Long memberId, String memberName){
        MemberVO memberVO = memberDAO.findById(memberId);
        memberVO.setMemberName(memberName);
        memberDAO.updateById(memberVO);
    }

    //    핸드폰 번호 변경
    public void updatePhoneNumber(Long memberId , String memberPhoneNumber){
        MemberVO memberVO = memberDAO.findById(memberId);
        memberVO.setMemberPhoneNumber(memberPhoneNumber);
        memberDAO.updateById(memberVO);
    }

    // sms 발송 서비스
    public String memberSMS(String memberPhoneNumber){
        String apiKey = "";
        String apiSecret = "";
        String fromNumber = "";
        String code = "";

        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            code += random.nextInt(10);
        }
        Message coolsms = new Message(apiKey, apiSecret);

        HashMap<String, String> params = new HashMap<>();
        params.put("to", memberPhoneNumber);
        params.put("from", fromNumber);
        params.put("type", "sms");
        params.put("text", "[북적북적] 인증번호 "+ code +" 를 입력하세요.");
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
        return code;
    }

    //    핸드폰 중복 검사
    public Boolean phoneNumberCheck(String PhoneNumber){
        List<String> phoneNumbers = memberDAO.findAllToMemberPhoneNumber();
        boolean check = false;

        for (int i = 0; i < phoneNumbers.size(); i++){
            if(phoneNumbers.get(i).equals(PhoneNumber)){
                check = true;
            }
        }
        return check;
    }

    // 비밀 번호 변경
    public void updatePassword(Long memberId, String memberPassword){
        MemberVO memberVO = memberDAO.findById(memberId);
        memberVO.setMemberPassword(memberPassword);
        memberDAO.updateById(memberVO);
    }

    // 문의 게시판 목록
    public MyPageInquireDTO inquireList(Long memberId, Criteria criteria){
        List<BoardInquiryVO> inquires = inquiryBoardDAO.findAllByIdToInquire(memberId,criteria);
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

    // 문의 게시판 개수
    public Integer inquireCount(Long memberId){
        return inquiryBoardDAO.getCountToInquire(memberId);
    };

    // 좋아요 한 게시물 목록
    public BoardFreeLikeDTO likeList(Long memberId, Criteria criteria){
        BoardFreeLikeDTO boardFreeLikeDTO = new BoardFreeLikeDTO();
        List<MemberVO> memberVOs = new ArrayList<>();
        List<BusinessVO> businessVOS = new ArrayList<>();
        List<Integer> boardReplyCounts = new ArrayList<>();
        List<BoardFreeVO> boardFreeVOS = freeLikeDAO.findAllToLike(memberId,criteria);

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
    public Integer likeCount(Long memberId){
        return freeLikeDAO.getCountToLike(memberId);
    }

    //  댓글 단 게시물 목록
    public BoardReplyDTO replyList(Long memberId, Criteria criteria){
        BoardReplyDTO boardReplyDTO = new BoardReplyDTO();
        List<MemberVO> memberVOs = new ArrayList<>();
        List<BusinessVO> businessVOS = new ArrayList<>();
        List<MyPageReplyDTO> myPageReplyDTOS = replyDAO.findAllMyPageReplyDTO(memberId, criteria);

        myPageReplyDTOS.stream().map(data -> data.getMemberId()).forEach(data -> memberVOs.add(memberDAO.findById(data)));
        myPageReplyDTOS.stream().map(data -> data.getBusinessId()).forEach(data -> businessVOS.add(businessDAO.findByIdToBusiness(data)));

        boardReplyDTO.setBusinessVOS(businessVOS);
        boardReplyDTO.setMemberVOs(memberVOs);
        boardReplyDTO.setMyPageReplyDTOS(myPageReplyDTOS);

        return boardReplyDTO;
    };

    // 댓글 갯수
    public Integer replyCount(Long memberId){
        return replyDAO.getReplyTotal(memberId);
    };

    // 자유게시판 목록 가져오기
    public List<BoardFreeVO> freeList(Long memberId, Criteria criteria){
        return freeBoardDAO.findByIdBoardFreeVO(memberId, criteria);
    }

    // 자유게시판 개수
    public Integer freeCount(Long memberId){
        return freeBoardDAO.getFreeBoardTotal(memberId);
    }

    // 게시판 각각의 개수
    public Map<String, Integer> allcount(Long memberId){
        Map<String, Integer> allCount = new HashMap<>();

        allCount.put("freeBoardCount",freeBoardDAO.getFreeBoardTotal(memberId));
        allCount.put("replyCount", replyDAO.getReplyTotal(memberId));
        allCount.put("likeBoardCount", freeLikeDAO.getCountToLike(memberId));
        allCount.put("inquireCount", inquiryBoardDAO.getCountToInquire(memberId));
        allCount.put("interestingCount",memberDAO.getInterestingCountById(memberId));

        return allCount;
    }

//-----------------------------------------------------------------------------------------------------------------

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
    public BoardReplyDTO businessReplyList(Long businessId, Criteria criteria){
        BoardReplyDTO boardReplyDTO = new BoardReplyDTO();
        List<MemberVO> memberVOs = new ArrayList<>();
        List<BusinessVO> businessVOS = new ArrayList<>();
        List<MyPageReplyDTO> myPageReplyDTOS = replyDAO.findAllBusinessMyPageReplyDTO(businessId, criteria);

        myPageReplyDTOS.stream().map(data -> data.getMemberId()).forEach(data -> memberVOs.add(memberDAO.findById(data)));
        myPageReplyDTOS.stream().map(data -> data.getBusinessId()).forEach(data -> businessVOS.add(businessDAO.findByIdToBusiness(data)));

        boardReplyDTO.setBusinessVOS(businessVOS);
        boardReplyDTO.setMemberVOs(memberVOs);
        boardReplyDTO.setMyPageReplyDTOS(myPageReplyDTOS);

        return boardReplyDTO;
    };

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

    // 좋아요 추가
    public void likeInsert(FreeLikeVO freeLikeVO) {
        freeLikeDAO.saveFreeLike(freeLikeVO);
    }

    // 좋아요 제거
    public void likeDown(FreeLikeVO freeLikeVO) {
        freeLikeDAO.removeFreeLike(freeLikeVO);
    }
    
    // 좋아요 갯수 업데이트
    public void countUp(Long boardFreeId){
        freeLikeDAO.updateCount(boardFreeId);
    }

    // 좋아요 눌럿는지 확인
    public Boolean likeCheck(FreeLikeVO freeLikeVO){
        return freeLikeDAO.findByIdBoardFreeId(freeLikeVO) == 0;
    }

}
