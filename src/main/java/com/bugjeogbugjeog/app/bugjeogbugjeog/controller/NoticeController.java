package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;


import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.AdminCriteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.PageDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.NoticeVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.MyPageService;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice/*")
@Slf4j
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/list")
    public String List(Model model, Criteria criteria){
        model.addAttribute("noticeVO", noticeService.showList(criteria));
        model.addAttribute("pageDTO", new PageDTO(criteria, noticeService.count()));
        return "notice/notice_list";
    }
    @GetMapping("/detail/{noticeId}")
    public String detail(Model model, @PathVariable("noticeId") Long noticeId){

        model.addAttribute("noticeVO", noticeService.showNotice(noticeId));
        model.addAttribute("noticeVOS", noticeService.showList(new Criteria(1, 5)));

        return "notice/notice_detail";
    }
}
