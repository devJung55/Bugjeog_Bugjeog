package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardFreeLikeDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardReplyDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.PageDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/profile/business/*")
@Slf4j
public class BusinessMyPageController {

    private final HttpServletRequest req;
    private final MyPageService myPageService;

    // 유통업체 정보
    @GetMapping("myinfo")
    public void myInfoBusiness(Model model){
        HttpSession session = req.getSession();
        Long businessId = (Long) session.getAttribute("businessId");
        model.addAttribute("businessVO",myPageService.businessInfo(businessId));
    }

    @GetMapping("exit")
    public void exitBusiness(Model model){
        HttpSession session = req.getSession();
        Long businessId = (Long) session.getAttribute("businessId");

        model.addAttribute("businessVO",myPageService.businessInfo(4L));
    }

    @PostMapping("businessWithdraw")
    public RedirectView businessWithdraw(HttpServletRequest req){
        HttpSession session = req.getSession();
        Long businessId = (Long) session.getAttribute("businessId");

        myPageService.businessWithdraw(businessId);
        return new RedirectView("/main/main");
    }

    // 자유게시판 작성 목록
    @GetMapping("postList")
    public void freeBoardList(Model model, Criteria criteria){
        HttpSession session = req.getSession();
        Long businessId = (Long) session.getAttribute("businessId");

        model.addAttribute("businessVO",myPageService.businessInfo(businessId));
        model.addAttribute("boardFreeVO", myPageService.businessFreeBoardList(businessId,criteria));
        model.addAttribute("pageDTO", new PageDTO(criteria, myPageService.businessFreeCount(businessId)));
    }

    // 댓글 단 게시판 정보
    @GetMapping("commentList")
    public void replyList(Model model, Criteria criteria){
        HttpSession session = req.getSession();
        Long businessId = (Long) session.getAttribute("businessId");
        BoardReplyDTO boardReplyDTO = myPageService.businessReplyBoardFreeList(businessId,criteria);

        model.addAttribute("businessVO",myPageService.businessInfo(businessId));
        model.addAttribute("memberVOs", boardReplyDTO.getMemberVOS());
        model.addAttribute("businessVOs", boardReplyDTO.getBusinessVOS());
        model.addAttribute("boardFreeVOS",boardReplyDTO.getBoardFreeVOS());
        model.addAttribute("pageDTO", new PageDTO(criteria, myPageService.businessReplyBoardFreeCount(businessId, criteria)));
    }

    // 좋아요 한 게시물목록
    @GetMapping("likedList")
    public void likeList(Model model, Criteria criteria){
        HttpSession session = req.getSession();
        Long businessId = (Long) session.getAttribute("businessId");

        BoardFreeLikeDTO boardFreeLikeDTO = myPageService.businessLikeList(businessId, criteria);

        model.addAttribute("businessVO",myPageService.businessInfo(businessId));
        model.addAttribute("memberVOs", boardFreeLikeDTO.getMemberVOs());
        model.addAttribute("replyCounts", boardFreeLikeDTO.getBoardReplyCounts());
        model.addAttribute("businessVOs", boardFreeLikeDTO.getBusinessVOS());
        model.addAttribute("freeVOs", boardFreeLikeDTO.getBoardFreeVOs());
        model.addAttribute("pageDTO", new PageDTO(criteria, myPageService.businessLikeCount(businessId)));
    }

    // faq 리스트
    @GetMapping("faqList")
    public void faqList(Model model, Criteria criteria){
        HttpSession session = req.getSession();
        Long businessId = (Long) session.getAttribute("businessId");

        model.addAttribute("businessVO",myPageService.businessInfo(businessId));
        model.addAttribute("inquireDTO",myPageService.businessInquireList(businessId,criteria));
        model.addAttribute("pageDTO", new PageDTO(criteria, myPageService.businessInquireCount(businessId)));
        model.addAttribute("inquireCount", myPageService.inquireCount(businessId));
    }

}
