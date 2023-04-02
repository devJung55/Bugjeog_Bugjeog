package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/main/*")
@RequiredArgsConstructor
@Slf4j
public class MainController {
    private final MemberService memberService;

    /*화면 이동*/
    @GetMapping("")    //url 부분
    public String mainPage(HttpSession httpSession){
        if(httpSession.getAttribute("memberId") != null) {  // 자영업자 로그인 시 분기처리
            httpSession.getAttribute("memberId");

        } else if(httpSession.getAttribute("businessId") != null) { // 비지니스 로그인 시 분기처리
            httpSession.getAttribute("businessId").toString();

        } else if(httpSession.getAttribute("memberVO") != null){    // 카카오 및 네이버 로그인 시 분기처리
            httpSession.setAttribute("memberId", memberService.loginNaverOauth(((MemberVO)httpSession.getAttribute("memberVO")).getMemberEmail()));
        }
        return "/main/main";
    }
    /* http://localhost:10000/main/ */

}
