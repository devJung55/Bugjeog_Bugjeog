package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Base64;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member/*")
@Slf4j
public class MemberController {
    private final MemberService memberService;

//    자영업자 회원가입
    @GetMapping("join")
    public void join(Model model) {
        model.addAttribute(new MemberVO());
    }

//    자영업자 회원가입 완료
    @PostMapping("join")
    public RedirectView join(MemberVO memberVO) {
        memberVO.setMemberPassword(new String(Base64.getEncoder().encode(memberVO.getMemberPassword().getBytes())));
        memberService.join(memberVO);
        return new RedirectView("/member/login");
    }

//    자영업자 로그인
    @GetMapping("login")
    public void login(Model model) {
        model.addAttribute(new MemberVO());
    }

//    자영업자 로그인 완료
    @PostMapping("login")
    public void login(MemberVO memberVO) {

    }
}