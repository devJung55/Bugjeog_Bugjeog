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
    public Long checkEmail(/*@RequestParam("memberEmail")*/ String memberEmail) {
        log.info(memberEmail);
        return memberService.checkEmail(memberEmail);
    }
}