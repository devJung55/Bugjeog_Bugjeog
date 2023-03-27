package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.PageDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
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
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/profile/*")
@Slf4j
public class MyPageController {

    private final MyPageService myPageService;

    //  --------------------- 일반 회원 조회
    @GetMapping("myinfo")
    public void memberInfo(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession();
//        Long memberId = (Long) session.getAttribute("memberId");
        Long memberId = 1L;

        model.addAttribute("memberVO", myPageService.memberInfo(memberId));
    }

    @GetMapping("exit")
    public void exit(HttpServletRequest req,Model model){
        HttpSession session = req.getSession();
//        Long memberId = (Long) session.getAttribute("memberId");
        Long memberId = 1L;
        model.addAttribute("memberVO",myPageService.memberInfo(memberId));
    }

    @PostMapping("withdraw")
    public RedirectView withdraw(HttpServletRequest req){
        HttpSession session = req.getSession();
//        Long memberId = (Long) session.getAttribute("memberId");
        Long memberId = 3L;
        myPageService.memberWithdraw(memberId);
        return new RedirectView("/main/main.html");// 수정해야하는 부분
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
    public String updateName(HttpServletRequest req, @RequestParam("memberName") String memberName) {
        HttpSession session = req.getSession();
//        Long memberId = (Long) session.getAttribute("memberId");
        Long memberId = 1L;
        myPageService.updateName(memberId, memberName);
        return memberName;
    }

    //    핸드폰 중복검사
    @GetMapping("memberPhoneCheck")
    @ResponseBody
    public Boolean memberPhoneCheck(@RequestParam("memberPhoneNumber") String memberPhoneNumber){
        return myPageService.PhoneNumberCheck(memberPhoneNumber);
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
    public String phoneNumberUpdate(HttpServletRequest req, @RequestParam("memberPhoneNumber") String memberPhoneNumber){
        HttpSession session = req.getSession();
//        Long memberId = (Long) session.getAttribute("memberId");
        Long memberId = 1L;
        myPageService.updatePhoneNumber(memberId, memberPhoneNumber);
        return memberPhoneNumber;
    }

//    비밀번호 변경
    @PatchMapping("updatePassword")
    @ResponseBody
    public void updatePassword(HttpServletRequest req, @RequestParam("memberPassword") String memberPassword){
        HttpSession session = req.getSession();
//        Long memberId = (Long) session.getAttribute("memberId");
        Long memberId = 1L;
        myPageService.updatePassword(memberId, memberPassword);
    }
    
//    회원정보 가져오기
    @GetMapping("memberVO")
    @ResponseBody
    public MemberVO memberVO(HttpServletRequest req){
        HttpSession session = req.getSession();
//        Long memberId = (Long) session.getAttribute("memberId");
        Long memberId = 1L;
        return myPageService.memberInfo(memberId);
    }

    @GetMapping("faqList")
    public void faqList(HttpServletRequest req, Model model, Criteria criteria){
        HttpSession session = req.getSession();
//        Long memberId = (Long) session.getAttribute("memberId");
        Long memberId = 1L;

        model.addAttribute("memberVO",myPageService.memberInfo(memberId));
        model.addAttribute("inquireDTO",myPageService.inquireList(memberId,criteria));
        model.addAttribute("pageDTO", new PageDTO(criteria, myPageService.inquireCount(memberId)));
        model.addAttribute("inquireCount", myPageService.inquireCount(memberId));
    }

    @GetMapping("list_business")
    public void test(){

    }

    @GetMapping("postList")
    public void test1(){

    }

    @GetMapping("likedList")
    public void likeList(HttpServletRequest req, Model model, Criteria criteria){
        HttpSession session = req.getSession();
//        Long memberId = (Long) session.getAttribute("memberId");
        Long memberId = 1L;

        model.addAttribute("memberVO",myPageService.memberInfo(memberId));
        model.addAttribute("freeVO",myPageService.likeList(memberId,criteria));
        model.addAttribute("pageDTO", new PageDTO(criteria, myPageService.likeCount(memberId)));
    }


    //    현재 날짜 경로 구하기
    private String getPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

}
