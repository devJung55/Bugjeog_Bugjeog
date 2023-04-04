package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.service.BusinessBoardService;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.BusinessInterestingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
//@RequestMapping("/board/business/favorite")
@RequiredArgsConstructor
@Slf4j
public class BusinessBoardInterestingController {
    private final BusinessInterestingService businessInterestingService;
    private final BusinessBoardService businessBoardService;

    @PostMapping("/favorite/insert")
    @ResponseBody
    public RedirectView register(HttpServletRequest req, Long businessId) {
        Long memberId = (Long) req.getSession().getAttribute("memberId");
        businessInterestingService.isThere(businessId, memberId);
        if (businessInterestingService.isThere(businessId, memberId)) {
            return new RedirectView("/board/business/list");
        } else {
            businessInterestingService.save(businessId, memberId);
            return new RedirectView("/board/business/list");
        }
    }
}
