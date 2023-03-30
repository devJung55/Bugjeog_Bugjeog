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

    @PostMapping("upload-file")
    @ResponseBody
    public String memberUpload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String path = "C:/upload/" + getPath();
        File file = new File(path);
        if(!file.exists()) {file.mkdirs();}

        String uuid = UUID.randomUUID().toString();
        multipartFile.transferTo(new File(path, uuid + "_" + multipartFile.getOriginalFilename()));

        if(multipartFile.getContentType().startsWith("image")){
            FileOutputStream out = new FileOutputStream(new File(path, "t_" + uuid + "_" + multipartFile.getOriginalFilename()));
            Thumbnailator.createThumbnail(multipartFile.getInputStream(), out, 400, 400);
            out.close();
        }
        return uuid;
    }

    //    파일 불러오기
    @GetMapping("display")
    @ResponseBody
    public byte[] display(String fileName) throws IOException {
        return FileCopyUtils.copyToByteArray(new File("C:/upload", fileName));
    }

    //    파일 저장
    @PatchMapping("file-memeber-save")
    @ResponseBody
    public void fileSave(@RequestBody MemberVO member){
        myPageService.fileSave(member);
    }

    //    이름 변경
    @PatchMapping("updateName")
    @ResponseBody
    public String updateName(@RequestParam("memberName") String memberName) {
        HttpSession session = req.getSession();
        Long memberId = (Long) session.getAttribute("memberId");

        myPageService.updateName(memberId, memberName);
        return memberName;
    }

    //    핸드폰 중복검사
    @GetMapping("memberPhoneCheck")
    @ResponseBody
    public Boolean memberPhoneCheck(@RequestParam("memberPhoneNumber") String memberPhoneNumber){
        return myPageService.phoneNumberCheck(memberPhoneNumber);
    }

    // 핸드폰 인증 번호
    @PostMapping("code")
    @ResponseBody
    public String smsCode(@RequestBody String memberPhoneNumber){
        String code = myPageService.memberSMS(memberPhoneNumber);
        return code;
    };

    //    핸드폰 번호 변경
    @PatchMapping("phoneNumberUpdate")
    @ResponseBody
    public String phoneNumberUpdate(@RequestParam("memberPhoneNumber") String memberPhoneNumber){
        HttpSession session = req.getSession();
        Long memberId = (Long) session.getAttribute("memberId");

        myPageService.updatePhoneNumber(memberId, memberPhoneNumber);
        return memberPhoneNumber;
    }

//    비밀번호 변경
    @PatchMapping("updatePassword")
    @ResponseBody
    public void updatePassword(@RequestParam("memberPassword") String memberPassword){
        HttpSession session = req.getSession();
        Long memberId = (Long) session.getAttribute("memberId");

        myPageService.updatePassword(memberId, memberPassword);
    }
    
//    회원정보 가져오기
    @GetMapping("memberVO")
    @ResponseBody
    public MemberVO memberVO(){
        HttpSession session = req.getSession();
        Long memberId = (Long) session.getAttribute("memberId");

        return myPageService.memberInfo(memberId);
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

    @GetMapping("replyList")
    @ResponseBody
    public List<FreeReplyVO> reply(@RequestParam("boardFreeId") Long boardFreeId){
        HttpSession session = req.getSession();
        Long memberId = (Long) session.getAttribute("memberId");
        return myPageService.replyList(memberId, boardFreeId);
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

    // 각 게시물 작성 갯수
    @GetMapping("count")
    @ResponseBody
    public Map<String, Integer> allCount(){
        HttpSession session = req.getSession();
        Long memberId = (Long) session.getAttribute("memberId");

        return myPageService.allcount(memberId);
    }

    @PostMapping("like/likeUp")
    @ResponseBody
    public void likeInsert(@RequestBody FreeLikeVO freeLikeVO) {
        myPageService.likeInsert(freeLikeVO);
    }

    @DeleteMapping("like/likeDown")
    @ResponseBody
    public void likeDown(@RequestBody FreeLikeVO freeLikeVO){
        myPageService.likeDown(freeLikeVO);
    }

    @PatchMapping("like/likeCountUpdate")
    @ResponseBody
    public void businessLikeCountUpdate(@RequestParam("boardFreeId") Long boardFreeId){
        myPageService.countUp(boardFreeId);
    }

    @PostMapping("like/like-check")
    @ResponseBody
    public Boolean likeCheck(@RequestBody FreeLikeVO freeLikeVO){
        return myPageService.likeCheck(freeLikeVO);
    }

    @PostMapping("like/likeCount")
    @ResponseBody
    public Integer getLikeCount(@RequestBody Long boardFreeId){
        return myPageService.getlikeCount(boardFreeId);
    }

    //    현재 날짜 경로 구하기
    private String getPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

}
