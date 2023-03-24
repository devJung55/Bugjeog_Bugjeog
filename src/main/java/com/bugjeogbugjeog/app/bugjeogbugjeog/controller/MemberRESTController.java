package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Base64;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/*")
public class MemberRESTController {
    private final MemberService memberService;

//    이메일 중복검사
    @PostMapping("emails/{memberEmail}")
    public Long checkEmail(@PathVariable("memberEmail") String memberEmail) {
        return memberService.checkEmail(memberEmail);
    }
}