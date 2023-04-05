package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.service.BusinessBoardService;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.BusinessInterestingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/board/business/favorite")
@RequiredArgsConstructor
@Slf4j
public class BusinessBoardInterestingController {
    private final BusinessInterestingService businessInterestingService;
    private final BusinessBoardService businessBoardService;

    @PutMapping("/favorite/update")
    @ResponseBody
    public void redirect(@RequestParam("boardBusinessId") Long boardBusinessId,@RequestParam("memberId") Long memberId){
        Long businessId = businessBoardService.getBoardById(boardBusinessId).getBusinessId();
        System.out.println(businessId);
        System.out.println(businessInterestingService.getOne(businessId, memberId) == null);
        if(businessInterestingService.getOne(businessId, memberId) == null){
            businessInterestingService.save(businessId, memberId);
        }else {
            businessInterestingService.remove(businessId, memberId);
        }
    }

}
