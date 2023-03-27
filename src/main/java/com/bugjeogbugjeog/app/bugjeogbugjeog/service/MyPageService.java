package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.MypageDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MemberInquireDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardInquiryVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final MypageDAO mypageDAO;

    //    회원 정보 조회
    public MemberVO memberInfo(Long memberId){
        return mypageDAO.findById(memberId);
    };

    //    회원 정보 수정
    public void memberUpdate(MemberVO memberVO) {
        mypageDAO.updateById(memberVO);
    }

    //    회원 탈퇴
    public void memberWithdraw(Long memberId) { mypageDAO.deleteById(memberId);}

    //    파일 저장
    public void fileSave(MemberVO member){
        MemberVO memberVO = mypageDAO.findById(member.getMemberId());

        memberVO.setMemberImgOriginalName(member.getMemberImgOriginalName());
        memberVO.setMemberImgPath(member.getMemberImgPath());
        memberVO.setMemberImgUuid(member.getMemberImgUuid());

        mypageDAO.updateById(memberVO);
    }

    //    이름 변경
    public void updateName(Long memberId, String memberName){
        MemberVO memberVO = mypageDAO.findById(memberId);
        memberVO.setMemberName(memberName);
        mypageDAO.updateById(memberVO);
    }

    //    핸드폰 번호 변경
    public void updatePhoneNumber(Long memberId , String memberPhoneNumber){
        MemberVO memberVO = mypageDAO.findById(memberId);
        memberVO.setMemberPhoneNumber(memberPhoneNumber);
        mypageDAO.updateById(memberVO);
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
    public Boolean PhoneNumberCheck(String memberPhoneNumber){
        List<String> phoneNumbers = mypageDAO.findAllToMemberPhoneNumber();
        boolean check = false;

        for (int i = 0; i < phoneNumbers.size(); i++){
            if(phoneNumbers.get(i).equals(memberPhoneNumber)){
                check = true;
            }
        }
        return check;
    }

    // 비밀 번호 변경
    public void updatePassword(Long memberId, String memberPassword){
        MemberVO memberVO = mypageDAO.findById(memberId);
        memberVO.setMemberPassword(memberPassword);
        mypageDAO.updateById(memberVO);
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


    //    유통 분야 설정 수정
    public void updateLocation(BusinessVO businessVO) {
        mypageDAO.updateLocation(businessVO);
    }
}
