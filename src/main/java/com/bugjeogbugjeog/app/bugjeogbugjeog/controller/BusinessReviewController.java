package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessReviewVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.BusinessReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
//@RequestMapping("/board/business/review")
@RequiredArgsConstructor
@Slf4j
public class BusinessReviewController {
    private final BusinessReviewService businessReviewService;

    @PostMapping("/board/business/review/write")
    public RedirectView writeOk(BusinessReviewVO businessReviewVO){
        businessReviewService.save(businessReviewVO);
        return new RedirectView("/board/business/list");
    }
}
