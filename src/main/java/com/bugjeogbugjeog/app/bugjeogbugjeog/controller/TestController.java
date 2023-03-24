package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage/*")
/*@RequestMapping("/main/*")*/
public class TestController {

    @GetMapping("/profile/favorite")
    public String main(){
        return "mypage/specific/personalFavoriteList";
    }
    @GetMapping("/profile/edit")
    public String main2(){
        return "mypage/specific/businessEdit";
    }

/*    @GetMapping("/main")
    public void main(){
    }*/
}
