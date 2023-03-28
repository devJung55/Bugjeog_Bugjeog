package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Base64;

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

//    자영업자 인증번호 발송
    @PostMapping("code")
    @ResponseBody
    public String smsCode(@RequestParam("memberPhoneNumber") String memberPhoneNumber){
        String code = memberService.sendSMS(memberPhoneNumber);
        return code;
    }

//    유통업체 이메일 중복 검사
    @PostMapping("businessEmailsCheck")
    public Long checkBusinessEmail(@RequestParam("businessEmail") String businessEmail) {
        return null;
    }

//    유통업체 휴대폰번호 중복 검사
    @PostMapping("businessPhoneNumbersCheck")
    public Long checkBusinessPhoneNumber(@RequestParam("businessPhoneNumber") String businessPhoneNumber) {
        return null;
    }
}