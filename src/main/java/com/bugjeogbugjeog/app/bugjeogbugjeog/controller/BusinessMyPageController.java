package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardFreeLikeDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardReplyDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.PageDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.BusinessMyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/profile/business/*")
@Slf4j
public class BusinessMyPageController {

    private final HttpServletRequest req;
    private final BusinessMyPageService businessMyPageService;

    // 유통업체 정보
    @GetMapping("myinfo")
    public void myInfoBusiness(Model model){
        HttpSession session = req.getSession();
        Long businessId = (Long) session.getAttribute("businessId");
        businessId = 4L;

        model.addAttribute("businessVO", businessMyPageService.businessInfo(businessId));
    }

    // 회원탈퇴 페이지
    @GetMapping("exit")
    public void exitBusiness(Model model){
        HttpSession session = req.getSession();
        Long businessId = (Long) session.getAttribute("businessId");

        model.addAttribute("businessVO", businessMyPageService.businessInfo(businessId));
    }

    // 회원 탈퇴
    @PostMapping("businessWithdraw")
    public RedirectView businessWithdraw(HttpServletRequest req){
        HttpSession session = req.getSession();
        Long businessId = (Long) session.getAttribute("businessId");

        businessMyPageService.businessWithdraw(businessId);
        return new RedirectView("/main/main");
    }

    // 자유게시판 작성 목록
    @GetMapping("postList")
    public void freeBoardList(Model model, Criteria criteria){
        HttpSession session = req.getSession();
        Long businessId = (Long) session.getAttribute("businessId");

        model.addAttribute("businessVO", businessMyPageService.businessInfo(businessId));
        model.addAttribute("boardFreeVO", businessMyPageService.businessFreeBoardList(businessId,criteria));
        model.addAttribute("pageDTO", new PageDTO(criteria, businessMyPageService.businessFreeCount(businessId)));
    }

    // 댓글 단 게시판 정보
    @GetMapping("commentList")
    public void replyList(Model model, Criteria criteria){
        HttpSession session = req.getSession();
        Long businessId = (Long) session.getAttribute("businessId");
        BoardReplyDTO boardReplyDTO = businessMyPageService.businessReplyBoardFreeList(businessId,criteria);

        model.addAttribute("businessVO", businessMyPageService.businessInfo(businessId));
        model.addAttribute("memberVOs", boardReplyDTO.getMemberVOS());
        model.addAttribute("businessVOs", boardReplyDTO.getBusinessVOS());
        model.addAttribute("boardFreeVOS",boardReplyDTO.getBoardFreeVOS());
        model.addAttribute("pageDTO", new PageDTO(criteria, businessMyPageService.businessReplyBoardFreeCount(businessId, criteria)));
    }

    // 좋아요 한 게시물목록
    @GetMapping("likedList")
    public void likeList(Model model, Criteria criteria){
        HttpSession session = req.getSession();
        Long businessId = (Long) session.getAttribute("businessId");

        BoardFreeLikeDTO boardFreeLikeDTO = businessMyPageService.businessLikeList(businessId, criteria);

        model.addAttribute("businessVO", businessMyPageService.businessInfo(businessId));
        model.addAttribute("memberVOs", boardFreeLikeDTO.getMemberVOs());
        model.addAttribute("replyCounts", boardFreeLikeDTO.getBoardReplyCounts());
        model.addAttribute("businessVOs", boardFreeLikeDTO.getBusinessVOS());
        model.addAttribute("freeVOs", boardFreeLikeDTO.getBoardFreeVOs());
        model.addAttribute("pageDTO", new PageDTO(criteria, businessMyPageService.businessLikeCount(businessId)));
    }

    // faq 리스트
    @GetMapping("faqList")
    public void faqList(Model model, Criteria criteria){
        HttpSession session = req.getSession();
        Long businessId = (Long) session.getAttribute("businessId");

        model.addAttribute("businessVO", businessMyPageService.businessInfo(businessId));
        model.addAttribute("inquireDTO", businessMyPageService.businessInquireList(businessId,criteria));
        model.addAttribute("pageDTO", new PageDTO(criteria, businessMyPageService.businessInquireCount(businessId)));
        model.addAttribute("inquireCount", businessMyPageService.businessInquireCount(businessId));
    }



}
