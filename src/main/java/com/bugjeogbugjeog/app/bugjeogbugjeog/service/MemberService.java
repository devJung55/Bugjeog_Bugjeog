package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.MemberDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.FindEmailDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MemberDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberDAO memberDAO;

//    자영업자 회원가입
    public void join(MemberVO memberVO) {
        memberDAO.register(memberVO);
    }

//    이메일 중복검사
    public Long checkEmail(String memberEmail) {
        return memberDAO.findByEmail(memberEmail);
    }

//    휴대폰번호 중복검사
    public Long checkPhoneNumber(String memberPhoneNumber) {
        return memberDAO.findByPhoneNumber(memberPhoneNumber);
    }

//    자영업자 회원가입 인증번호 발송
    public String sendSMS(String memberPhoneNumber) {
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

//    자영업자 로그인
    public Long login(MemberVO memberVO) {
        return memberDAO.findByMemberId(memberVO);
    }

//    유통업체 회원가입
    public void joinBusiness(BusinessVO businessVO) {
        memberDAO.registerBusiness(businessVO);
    }

//    유통업체 이메일 중복검사
    public Long businessCheckEmail(String businessEmail) {
        return memberDAO.businessFindByEmail(businessEmail);
    }

//    유통업체 휴대폰 중복검사
    public Long businessCheckPhoneNumber(String businessPhoneNumber) {
        return memberDAO.businessFindByPhoneNumber(businessPhoneNumber);
    }

//    유통업체 사업자번호 중복검사
    public Long businessCheckBusinessNumber(String businessNumber) {
        return memberDAO.findByBusinessNumber(businessNumber);
    }

//    유통업체 로그인
    public Long businessLongin(String businessEmail, String businessPassword) {
        return memberDAO.findByBusinessId(businessEmail, businessPassword);
    }

//    계정 찾기
    public FindEmailDTO findAccount(String memberPhoneNumber) {
        FindEmailDTO findEmailDTO = new FindEmailDTO();

        findEmailDTO.setMemberEmail(memberDAO.businessFindByPhoneNumberForEmail(memberPhoneNumber));
        findEmailDTO.setBusinessEmail(memberDAO.businessFindByPhoneNumberForEmail(memberPhoneNumber));

        return findEmailDTO;
    }

    /*-----------------------------------------------------------------------------*/

    //  관리자 회원 목록
    public List<MemberDTO> adminMemberShowList(Criteria criteria){return memberDAO.adminFindAll(criteria);}

    // 관리자 멤버 카운트
    public int count(){return memberDAO.count();}

    /* 관리자 회원 상세 보기 */
    public MemberDTO adminMemberShow(Long memberId){return memberDAO.adminFindById(memberId);}

    /*-----------------------------------------------------------------------------*/

    // 회원정보 수정
    public void updateMember(MemberVO memberVO){memberDAO.updateById(memberVO);}
}