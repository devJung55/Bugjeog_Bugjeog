package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice/*")
@Slf4j
public class NoticeController {

    @GetMapping("/list")
    public String List(){
        return "notice/notice_list";
    }
    @GetMapping("/detail")
    public String detail(){
        return "notice/notice_detail";
    }
}
