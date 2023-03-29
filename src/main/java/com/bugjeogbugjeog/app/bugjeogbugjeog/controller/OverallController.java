package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/overall/*")
@Slf4j
public class OverallController {

    //    헤더쪽 유통업체 business의 list
    @GetMapping("distribution")
    public String moveDistribution(){ return "/board/business/list";}

    //    헤더쪽 문의하기 inquiry의 boardList
    @GetMapping("inquiry")
    public String moveInquiry(){ return "/board/inquiry/boardList";}

    //     헤더쪽 공지사항 notice의 list
    //      일단 보류
    @GetMapping("notice")
    public String moveNotice(){ return "/notice/notice_list";}

    //      헤더쪽 FAQ
    @GetMapping("faq")
    public String moveFaq(){ return "/notice/faq";}

}
