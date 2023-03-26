package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main/")
public class MainController {
    /*화면 이동*/
    @GetMapping("/")    //url 부분
    public String mainPage(){ return "main/main";}
    /* http://localhost:10000/main/ */

    /*검색 결과*//*서치로 다시 만들기 noresult search로 이름 바꾸기*/
    @GetMapping("/search")
    public String search(){ return "board/noresult";}
}
