package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.MemberDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.MypageDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MemberInquireDTO;
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
    private final MypageDAO mypageDAO;
    private final MemberDAO memberDAO;

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
    public Boolean PhoneNumberCheck(String PhoneNumber){
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
    public MemberInquireDTO inquireList(Long memberId, Criteria criteria){
        List<BoardInquiryVO> inquires = mypageDAO.findAllByIdToInquire(memberId,criteria);
        List<Long> status = new ArrayList<>();
        MemberInquireDTO memberInquireDTO = new MemberInquireDTO();

        for(int i =0; i < inquires.size(); i++){
            Long inquiryBoardId = inquires.get(i).getBoardInquiryId();
            status.add(mypageDAO.inquireAnswer(inquiryBoardId));
        }

        memberInquireDTO.setAnswerStatus(status);
        memberInquireDTO.setInquire(inquires);

        return memberInquireDTO;
    }

    // 문의 게시판 개수
    public Integer inquireCount(Long memberId){
        return mypageDAO.getCountToInquire(memberId);
    };

    // 좋아요 한 게시물 목록
    public List<BoardFreeVO> likeList(Long memberId, Criteria criteria){
        return mypageDAO.findAllToLike(memberId,criteria);
    }

    // 좋아요 게시물 갯수
    public Integer likeCount(Long memberId){
        return mypageDAO.getCountToLike(memberId);
    }

    //  댓글 단 게시물 목록
    public List<MyPageReplyDTO> replyList(Long memberId, Criteria criteria){
        return mypageDAO.findAllMyPageReplyDTO(memberId, criteria);
    };

    // 댓글 갯수
    public Integer replyCount(Long memberId){
        return mypageDAO.getReplyTotal(memberId);
    };

    // 자유게시판 목록 가져오기
    public List<BoardFreeVO> freeList(Long memberId, Criteria criteria){
        return mypageDAO.findByIdBoardFreeVO(memberId, criteria);
    }

    // 자유게시판 개수
    public Integer freeCount(Long memberId){
        return mypageDAO.getFreeBoardTotal(memberId);
    }

    // 게시판 각각의 개수
    public Map<String, Integer> allcount(Long memberId){
        Map<String, Integer> allCount = new HashMap<>();

        allCount.put("freeBoardCount",mypageDAO.getFreeBoardTotal(memberId));
        allCount.put("replyCount", mypageDAO.getReplyTotal(memberId));
        allCount.put("likeBoardCount", mypageDAO.getCountToLike(memberId));
        allCount.put("inquireCount", mypageDAO.getCountToInquire(memberId));

        return allCount;
    }


    //    유통업체 파일 저장
    public void businessFileSave(BusinessVO business){
        BusinessVO businessVO = mypageDAO.findByIdToBusiness(business.getBusinessId());

        businessVO.setBusinessImgOriginalName(business.getBusinessImgOriginalName());
        businessVO.setBusinessImgPath(business.getBusinessImgPath());
        businessVO.setBusinessImgUuid(business.getBusinessImgUuid());

        mypageDAO.updateLocation(businessVO);
    }

    //    유통업체 이름 변경
    public void updateBusinessCeoName(Long businessId, String businessCeoName){
        BusinessVO businessVO = mypageDAO.findByIdToBusiness(businessId);
        businessVO.setBusinessCeoName(businessCeoName);
        mypageDAO.updateLocation(businessVO);
    }

    //    유통업자 핸드폰 번호 변경
    public void updateBusinessPhoneNumber(Long businessId , String businessPhoneNumber){
        BusinessVO businessVO =mypageDAO.findByIdToBusiness(businessId);
        businessVO.setBusinessPhoneNumber(businessPhoneNumber);
        mypageDAO.updateLocation(businessVO);
    }

    // 유통 조회
    public BusinessVO businessInfo(Long businessId){
        return mypageDAO.findByIdToBusiness(businessId);
    }

    //    유통 분야 설정 수정
    public void updateLocation(BusinessVO businessVO) {
        mypageDAO.updateLocation(businessVO);
    }
}
