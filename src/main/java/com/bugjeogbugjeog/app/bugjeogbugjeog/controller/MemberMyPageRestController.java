package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.FreeReplyVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.MyPageService;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.util.FileCopyUtils;
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
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/myPages/*")
public class MemberMyPageRestController {

    private final HttpServletRequest req;
    private final MyPageService myPageService;

    @PostMapping("upload-file")
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

    //    파일 저장
    @PatchMapping("file-memeber-save")
    public void fileSave(@RequestBody MemberVO member){
        myPageService.fileSave(member);
    }

    //    이름 변경
    @PatchMapping("updateName")
    public String updateName(@RequestParam("memberName") String memberName) {
        HttpSession session = req.getSession();
        Long memberId = (Long) session.getAttribute("memberId");

        myPageService.updateName(memberId, memberName);
        return memberName;
    }

    //    핸드폰 중복검사
    @GetMapping("memberPhoneCheck")
    public Boolean memberPhoneCheck(@RequestParam("memberPhoneNumber") String memberPhoneNumber){
        return myPageService.phoneNumberCheck(memberPhoneNumber);
    }

    // 핸드폰 인증 번호
    @PostMapping("code")
    public String smsCode(@RequestBody String memberPhoneNumber){
        String code = myPageService.memberSMS(memberPhoneNumber);
        return code;
    };

    //    핸드폰 번호 변경
    @PatchMapping("phoneNumberUpdate")
    public String phoneNumberUpdate(@RequestParam("memberPhoneNumber") String memberPhoneNumber){
        HttpSession session = req.getSession();
        Long memberId = (Long) session.getAttribute("memberId");

        myPageService.updatePhoneNumber(memberId, memberPhoneNumber);
        return memberPhoneNumber;
    }

    //    비밀번호 변경
    @PatchMapping("updatePassword")
    public void updatePassword(@RequestParam("memberPassword") String memberPassword){
        HttpSession session = req.getSession();
        Long memberId = (Long) session.getAttribute("memberId");

        myPageService.updatePassword(memberId, memberPassword);
    }

    //    회원정보 가져오기
    @GetMapping("memberVO")
    public MemberVO memberVO(){
        HttpSession session = req.getSession();
        Long memberId = (Long) session.getAttribute("memberId");

        return myPageService.memberInfo(memberId);
    }

    @GetMapping("replyList")
    public List<FreeReplyVO> reply(@RequestParam("boardFreeId") Long boardFreeId){
        return myPageService.replyList(boardFreeId);
    }

    // 각 게시물 작성 갯수
    @GetMapping("count")
    public Map<String, Integer> allCount(){
        HttpSession session = req.getSession();
        Long memberId = (Long) session.getAttribute("memberId");

        return myPageService.allcount(memberId);
    }

    //    현재 날짜 경로 구하기
    private String getPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
}
