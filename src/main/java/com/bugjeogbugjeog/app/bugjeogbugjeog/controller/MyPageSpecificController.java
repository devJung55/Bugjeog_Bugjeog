package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;


import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.MyPageMapper;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/mypage/*")
@RequiredArgsConstructor
@Slf4j
public class MyPageSpecificController {

    private final MyPageService myPageService;

    @GetMapping("/edit")
    public String main(){
        return "mypage/specific/businessEdit";
    }
    @GetMapping("/favorite")
    public String main2(){
        return "mypage/specific/personalFavoriteList";
    }



    @PostMapping("/edit")
    public RedirectView updateLocation(HttpServletRequest req, RedirectAttributes attributes, BusinessVO businessVO){
        HttpSession session = req.getSession();
//        Long businessId = (Long) session.getAttribute("businessId");
        Long businessId = 1L;
        attributes.addAttribute("businessLocation", businessVO.getBusinessLocation());
        log.info(businessVO.getBusinessLocation());
        attributes.addAttribute("businessCategory", businessVO.getBusinessCategory());
        log.info(businessVO.getBusinessCategory());
        return new RedirectView("/mypage/edit");
    }


//    @GetMapping("/specific/businessEdit")
//    public String main2(){
//        return "mypage/specific/businessEdit";
//    }

}
