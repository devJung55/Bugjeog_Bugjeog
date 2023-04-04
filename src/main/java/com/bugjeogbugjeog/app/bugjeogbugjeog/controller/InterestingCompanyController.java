package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;


import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.PageDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.PageInterestingDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.InterestingCriteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.InterestingCompanyService;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/mypage/*")
@RequiredArgsConstructor
@Slf4j
public class InterestingCompanyController {
    private final HttpServletRequest req;
    private final InterestingCompanyService interestingCompanyService;
    private final MyPageService myPageService;

    @GetMapping("specific/personalFavoriteList")
    public String company(Model model, InterestingCriteria interestingCriteria, Long interestingCompanyId){
        HttpSession session = req.getSession();
        Long memberId = (Long) session.getAttribute("memberId");
        model.addAttribute("memberVO", myPageService.memberInfo(memberId));
        model.addAttribute("interestingCompanyDTOs", interestingCompanyService.findAllToInterestingCompany(memberId, interestingCriteria));
        model.addAttribute("pageInterestingDTO", new PageInterestingDTO(interestingCriteria, interestingCompanyService.count(memberId)));
        model.addAttribute("interestingCompanyCount", interestingCompanyService.count(memberId));
        return "mypage/specific/personalFavoriteList";
    }
}
