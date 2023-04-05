package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.MemberDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.AdminCriteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.FindEmailDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MemberDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
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

        findEmailDTO.setMemberEmail(memberDAO.findByPhoneNumberForEmail(memberPhoneNumber));
        findEmailDTO.setBusinessEmail(memberDAO.businessFindByPhoneNumberForEmail(memberPhoneNumber));

        return findEmailDTO;
    }

//    자영업자 비밀번호 변경
    public void changePassword(String memberEamil, String memberPassword) {
        memberDAO.setPassword(memberEamil, memberPassword);
    }

//    사업자 비밀번호 변경
    public void businessChangePassword(String businessEmail, String businessPassword) {
        memberDAO.businessSetPassword(businessEmail, businessPassword);
    }

//    자영업자 계정 상태 조회
    public Integer findForStatus(String memberEmail) {
        return memberDAO.findForStatus(memberEmail);
    }

//    자영업자 계정 상태 조회
    public Integer businessFindForStatus(String businessEmail) {
        return memberDAO.businessFindForStatus(businessEmail);
    }

//    네이버 로그인
    public Long loginNaverOauth(String memberEmail) {
        return memberDAO.findIdByEmail(memberEmail);
    }

//    네이버 로그인
    public MemberVO findInfoByEmail(String memberEmail) {
        return memberDAO.findMemberInfo(memberEmail);
    }

    /*-----------------------------------------------------------------------------*/

    //  관리자 회원 목록
    public List<MemberDTO> adminMemberShowList(AdminCriteria adminCriteria){return memberDAO.adminFindAll(adminCriteria);}

    // 관리자 멤버 카운트
    public Long count(){return memberDAO.count();}

    /* 관리자 회원 상세 보기 */
    public MemberDTO adminMemberShow(Long memberId){return memberDAO.adminFindById(memberId);}

    /*-----------------------------------------------------------------------------*/

    /* 회원 정보 수정 */
    public void updateMember(MemberVO memberVO){
        MemberVO member = memberDAO.findById(memberVO.getMemberId());
        member.setMemberEmail(memberVO.getMemberEmail());
        member.setMemberPhoneNumber(memberVO.getMemberPhoneNumber());
        member.setMemberStatus(memberVO.getMemberStatus());
        memberDAO.updateById(member);}

    /* 회원 정보 조회*/
    public MemberVO showMember(Long memberId){return memberDAO.findById(memberId);}

    /* 회원 정보 삭제*/
    public void removeMember(Long memberId){
//        memberIds.stream().map(memberId -> Long.parseLong(memberId)).forEach(memberDAO::deleteById);
        memberDAO.deleteById(memberId);
    }


}