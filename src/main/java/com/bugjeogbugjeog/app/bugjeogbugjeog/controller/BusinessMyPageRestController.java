package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.ui.Model;
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
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypages/*")
public class BusinessMyPageRestController {

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

    //    파일 불러오기
    @GetMapping("display")
    public byte[] display(String fileName) throws IOException {
        return FileCopyUtils.copyToByteArray(new File("C:/upload", fileName));
    }

    //    파일 저장
    @PatchMapping("file-business-save")
    public void fileSave(@RequestBody BusinessVO business){
        myPageService.businessFileSave(business);
    }

    //    이름 변경
    @PatchMapping("updateBusinessCeoName")
    public String updateName(@RequestParam("businessCeoName") String businessCeoName) {
        HttpSession session = req.getSession();
        Long businessId = (Long) session.getAttribute("businessId");

        myPageService.updateBusinessCeoName(businessId, businessCeoName);
        return businessCeoName;
    }

    //    회원정보 가져오기
    @GetMapping("businessVO")
    public BusinessVO memberVO(){
        HttpSession session = req.getSession();
        Long businessId = (Long) session.getAttribute("businessId");

        return myPageService.businessInfo(businessId);
    }

    //    핸드폰 중복검사
    @GetMapping("businessPhoneCheck")
    public Boolean businessPhoneCheck(@RequestParam("businessPhoneNumber") String businessPhoneNumber){
        return myPageService.phoneNumberCheck(businessPhoneNumber);
    }

    //    핸드폰 번호 변경
    @PatchMapping("phoneNumberUpdate")
    public String phoneNumberUpdate(@RequestParam("businessPhoneNumber") String businessPhoneNumber){
        HttpSession session = req.getSession();
        Long businessId = (Long) session.getAttribute("businessId");

        myPageService.updateBusinessPhoneNumber(businessId, businessPhoneNumber);
        return businessPhoneNumber;
    }

    @PatchMapping("companyName-update")
    public String updateBusinessCompanyName(@RequestParam("businessCompanyName") String businessCompanyName){
        HttpSession session = req.getSession();
        Long businessId = (Long) session.getAttribute("businessId");

        myPageService.updateBusinessCompanyName(businessId, businessCompanyName);
        return businessCompanyName;
    }

    @GetMapping("businessNumber-check")
    public Boolean businessNumberCheck(@RequestParam("businessNumber") String businessNumber){
        return myPageService.businessNumberCheck(businessNumber);
    }

    @PatchMapping("businessNumber-update")
    public String businessNumberUpdate(@RequestParam("businessNumber") String businessNumber){
        HttpSession session = req.getSession();
        Long businessId = (Long) session.getAttribute("businessId");

        myPageService.businessNumberUpdate(businessId, businessNumber);
        return businessNumber;
    }

    @PatchMapping("businessPassword-update")
    public void businessPasswordUpdate(@RequestParam("businessPassword") String businessPassword){
        HttpSession session = req.getSession();
        Long businessId = (Long) session.getAttribute("businessId");

        myPageService.businessPasswordUpdate(businessId, businessPassword);
    }

    //    현재 날짜 경로 구하기
    private String getPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

}
