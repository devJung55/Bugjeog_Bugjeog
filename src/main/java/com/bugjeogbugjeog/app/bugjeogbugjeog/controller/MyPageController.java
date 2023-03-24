package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/*")
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping("myinfo")
    public void memberInfo(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession();
        Long memberId = (Long) session.getAttribute("memberId");

        model.addAttribute("memberVO", myPageService.memberInfo(memberId));
    }

}
