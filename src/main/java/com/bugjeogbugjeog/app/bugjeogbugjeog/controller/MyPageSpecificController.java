package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;


import com.bugjeogbugjeog.app.bugjeogbugjeog.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage/*")
@RequiredArgsConstructor
public class MyPageSpecificController {

    @GetMapping("/test")
    public String main(){
        return "mypage/specific/businessEdit";
    }
    @GetMapping("/test2")
    public String main2(){
        return "mypage/specific/personalFavoriteList";
    }

//    지역선택과 카테고리 선택 후 적용하기 눌렀을때 여기로 날아옴
//    @PostMapping("/specific/personalFavoriteList")
//    public String locaCate(){
//        return
//    }

//    @GetMapping("/specific/businessEdit")
//    public String main2(){
//        return "mypage/specific/businessEdit";
//    }

}
