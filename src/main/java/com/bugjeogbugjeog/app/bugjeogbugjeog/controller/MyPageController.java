package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardFreeLikeDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardReplyDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.PageDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.*;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/profile/*")
@Slf4j
public class MyPageController {
    private final HttpServletRequest req;
    private final MyPageService myPageService;

    //  --------------------- 일반 회원 조회
    @GetMapping("myinfo")
    public void memberInfo(Model model) {
        HttpSession session = req.getSession();
        Long memberId = (Long) session.getAttribute("memberId");

        model.addAttribute("memberVO", myPageService.memberInfo(memberId));
    }

    @GetMapping("exit")
    public void exit(Model model){
        HttpSession session = req.getSession();
        Long memberId = (Long) session.getAttribute("memberId");

        model.addAttribute("memberVO",myPageService.memberInfo(memberId));
    }

    @PostMapping("withdraw")
    public RedirectView withdraw(){
        HttpSession session = req.getSession();
        Long memberId = (Long) session.getAttribute("memberId");

        myPageService.memberWithdraw(memberId);
        session.removeAttribute("memberId");

        return new RedirectView("/main");
    }

    // faq 리스트
    @GetMapping("faqList")
    public void faqList(Model model, Criteria criteria){
        HttpSession session = req.getSession();
        Long memberId = (Long) session.getAttribute("memberId");

        model.addAttribute("memberVO",myPageService.memberInfo(memberId));
        model.addAttribute("inquireDTO",myPageService.inquireList(memberId,criteria));
        model.addAttribute("pageDTO", new PageDTO(criteria, myPageService.inquireCount(memberId)));
        model.addAttribute("inquireCount", myPageService.inquireCount(memberId));
    }
    
    // 댓글 단 게시판 정보
    @GetMapping("commentList")
    public void replyList(Model model, Criteria criteria){
        HttpSession session = req.getSession();
        Long memberId = (Long) session.getAttribute("memberId");
        BoardReplyDTO boardReplyDTO = myPageService.replyBoardFreeList(memberId,criteria);

        model.addAttribute("memberVO",myPageService.memberInfo(memberId));
        model.addAttribute("memberVOs", boardReplyDTO.getMemberVOS());
        model.addAttribute("businessVOs", boardReplyDTO.getBusinessVOS());
        model.addAttribute("boardFreeVOS",boardReplyDTO.getBoardFreeVOS());
        model.addAttribute("pageDTO", new PageDTO(criteria, myPageService.replyBoardFreeCount(memberId, criteria)));
    }

    // 자유게시판 작성 목록
    @GetMapping("postList")
    public void freeBoardList(Model model, Criteria criteria){
        HttpSession session = req.getSession();
        Long memberId = (Long) session.getAttribute("memberId");

        model.addAttribute("memberVO",myPageService.memberInfo(memberId));
        model.addAttribute("boardFreeVO", myPageService.freeList(memberId,criteria));
        model.addAttribute("pageDTO", new PageDTO(criteria, myPageService.freeCount(memberId)));
    }

    // 좋아요한 게시물
    @GetMapping("likedList")
    public void likeList(Model model, Criteria criteria){
        HttpSession session = req.getSession();
        Long memberId = (Long) session.getAttribute("memberId");

        BoardFreeLikeDTO boardFreeLikeDTO = myPageService.likeList(memberId, criteria);

        model.addAttribute("memberVO",myPageService.memberInfo(memberId));
        model.addAttribute("memberVOs", boardFreeLikeDTO.getMemberVOs());
        model.addAttribute("replyCounts", boardFreeLikeDTO.getBoardReplyCounts());
        model.addAttribute("businessVOs", boardFreeLikeDTO.getBusinessVOS());
        model.addAttribute("freeVOs", boardFreeLikeDTO.getBoardFreeVOs());
        model.addAttribute("pageDTO", new PageDTO(criteria, myPageService.likeCount(memberId)));
    }

    //    파일 불러오기
    @GetMapping("display")
    @ResponseBody
    public byte[] display(String fileName) throws IOException {
        return FileCopyUtils.copyToByteArray(new File("C:/upload", fileName));
    }

}
