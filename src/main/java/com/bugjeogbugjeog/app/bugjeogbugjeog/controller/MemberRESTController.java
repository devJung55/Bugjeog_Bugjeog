package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/*")
@Slf4j
public class MemberRESTController {
    private final MemberService memberService;

//    이메일 중복검사
    @PostMapping("emailsCheck")
    public Long checkEmail(@RequestParam("memberEmail") String memberEmail) {
        return memberService.checkEmail(memberEmail);
    }

//    휴대폰 번호 중복검사
    @PostMapping("phoneNumbersCheck")
    public Long checkPhoneNumber(@RequestParam("memberPhoneNumber") String memberPhoneNumber) {
        log.info(memberPhoneNumber);
        return memberService.checkPhoneNumber(memberPhoneNumber);
    }

//    인증번호 발송
    @PostMapping("code")
    @ResponseBody
    public String smsCode(@RequestParam("memberPhoneNumber") String memberPhoneNumber){
        String code = memberService.sendSMS(memberPhoneNumber);
        return code;
    }

//    유통업체 이메일 중복검사
    @PostMapping("businessEmailsCheck")
    public Long checkBusinessEmail(@RequestParam("businessEmail") String businessEmail) {
        log.info(businessEmail);
        return memberService.businessCheckEmail(businessEmail);
    }

//    유통업체 휴대폰번호 중복검사
    @PostMapping("businessPhoneNumbersCheck")
    public Long checkBusinessPhoneNumber(@RequestParam("businessPhoneNumber") String businessPhoneNumber) {
        return memberService.businessCheckPhoneNumber(businessPhoneNumber);
    }

//    유통업체 사업자번호 중복검사
    @PostMapping("businessNumbersCheck")
    public Long checkBusinessNumber(@RequestParam("businessNumber") String businessNumber) {
        return memberService.businessCheckBusinessNumber(businessNumber);
    }

//    네이버 로그인
    @PostMapping("login-naver")
    public Integer loginOauth(String memberEmail, HttpSession httpSession){
        Long memberId = memberService.loginNaverOauth(memberEmail);
        log.info(String.valueOf(memberId));
        if(memberId == null){
            MemberVO memberVO = new MemberVO();
            memberVO.setMemberEmail(memberEmail);
            httpSession.setAttribute("memberVO", memberVO);
            return  0;
        }else{
            MemberVO memberVO = memberService.findInfoByEmail(memberEmail);
            httpSession.setAttribute("memberVO", memberVO);
            return 1;
        }
    }
}