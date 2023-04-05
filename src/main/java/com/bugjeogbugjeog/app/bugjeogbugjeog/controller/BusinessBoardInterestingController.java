package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.service.BusinessBoardService;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.BusinessInterestingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
//@RequestMapping("/board/business/favorite")
@RequiredArgsConstructor
@Slf4j
public class BusinessBoardInterestingController {
    private final BusinessInterestingService businessInterestingService;
    private final BusinessBoardService businessBoardService;

    @PutMapping("/favorite/update")
    public void redirect(Long boardId, Long memberId, RedirectAttributes redirectAttributes){
        Long businessId = businessBoardService.getBoardById(boardId).getBusinessId();
        redirectAttributes.addAttribute("businessId", businessId);
        redirectAttributes.addAttribute("memberId", memberId);
        if(businessInterestingService.isThere(businessId, memberId)){
            businessInterestingService.remove(businessId, memberId);
        }else {
            businessInterestingService.save(businessId, memberId);
        }
    }

}
