package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.service.BusinessBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
//@RequestMapping("/board/business")
@RequiredArgsConstructor
@Slf4j
public class BusinessBoardController {
    private final BusinessBoardService businessBoardService;

    @GetMapping(value = {"/board/business", "/board/business/"})
    public RedirectView defaultRoot() {
        return new RedirectView("/board/business/list");
    }

    @GetMapping("/board/business/list")
    public void showList(Model model) { model.addAttribute(); }

    @GetMapping("/board/business/detail")
    public void detail() {
        ;
    }

    @GetMapping("/board/business/write")
    public void write() {
        ;
    }


//    @GetMapping("upload")
//    public String goUploadForm(){
//        return "/upload";
//    }
//
//    //    파일 저장
//    @PostMapping("save")
//    @ResponseBody
//    public void save(@RequestBody List<FileVO> files){
//        fileService.write(files);
//    }
//
//    //    파일 업로드
//    @PostMapping("upload")
//    @ResponseBody
//    public List<String> upload(@RequestParam("file") List<MultipartFile> multipartFiles) throws IOException {
//        List<String> uuids = new ArrayList<>();
//        String path = "C:/upload/" + getPath();
//        File file = new File(path);
//        if(!file.exists()) {file.mkdirs();}
//
//        for(int i=0; i<multipartFiles.size(); i++){
//            uuids.add(UUID.randomUUID().toString());
//            multipartFiles.get(i).transferTo(new File(path, uuids.get(i) + "_" + multipartFiles.get(i).getOriginalFilename()));
//
//            if(multipartFiles.get(i).getContentType().startsWith("image")){
//                FileOutputStream out = new FileOutputStream(new File(path, "t_" + uuids.get(i) + "_" + multipartFiles.get(i).getOriginalFilename()));
//                Thumbnailator.createThumbnail(multipartFiles.get(i).getInputStream(), out, 100, 100);
//                out.close();
//            }
//        }
//        return uuids;
//    }
//
//    //    파일 불러오기
//    @GetMapping("display")
//    @ResponseBody
//    public byte[] display(String fileName) throws IOException {
//        return FileCopyUtils.copyToByteArray(new File("C:/upload", fileName));
//    }
//
//    @GetMapping("download")
//    public ResponseEntity<Resource> download(String fileName) throws UnsupportedEncodingException {
//        Resource resource = new FileSystemResource("C:/upload/" + fileName);
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "attachment;filename=" + new String(fileName.substring(fileName.indexOf("_") + 1).getBytes("UTF-8"), "ISO-8859-1"));
//        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
//    }
//
//    //    현재 날짜 경로 구하기
//    private String getPath(){
//        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
//    }
}
