package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search/")
public class SearchController {

    /*검색 결과 noresult에서 search로 이름 변경 */
    @GetMapping("/")
    public String search(){ return "board/search";}
}
