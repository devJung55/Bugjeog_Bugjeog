package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
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

    @PostMapping("update-file")
    @ResponseBody
    public String memberUpdate(HttpServletRequest req,@RequestParam("file") MultipartFile multipartFile) throws IOException {
        HttpSession session = req.getSession();
//        Long memberId = (Long) session.getAttribute("memberId");
        Long memberId = 1L;
        MemberVO memberVO = myPageService.memberInfo(memberId);
        String path = "C:/upload/" + getPath();

        File file = new File(path);

        if(!file.exists()) {file.mkdirs();}

        String uuid = UUID.randomUUID().toString();
        multipartFile.transferTo(new File(path, uuid + "_" + multipartFile.getOriginalFilename()));

        return uuid;
    }

    //    현재 날짜 경로 구하기
    private String getPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

}
