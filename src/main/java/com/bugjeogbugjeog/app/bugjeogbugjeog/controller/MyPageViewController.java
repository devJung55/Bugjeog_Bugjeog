package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/*")
public class MyPageViewController {

    @GetMapping("test1")
    public String test1(){
        return "/mypage/profile/faqList";
    }
    @GetMapping("test2")
    public String test2(){
        return "/mypage/profile/likedList";
    }
    @GetMapping("test3")
    public String test3(){
        return "/mypage/profile/postList";
    }
    @GetMapping("test4")
    public String test4(){
        return "/mypage/profile/recentViewList";
    }

    @GetMapping("test")
    public String test5(){
        return "/mypage/profile/myInfo";
    }

}
