package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessImgVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.BusinessBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //    리스트
    @GetMapping("/board/business/list")
    public void showList(Model model) {
        Map<String, Map> categoryMap = new HashMap<>();

        Map<String, String> mapAll = new HashMap<>();
        mapAll.put("categoryKor", "전체");
        mapAll.put("categoryEng", "all");
        categoryMap.put("all", mapAll);

        Map<String, String> mapMeat = new HashMap<>();
        mapMeat.put("categoryKor", "육류");
        mapMeat.put("categoryEng", "meat");
        categoryMap.put("meat", mapMeat);

        Map<String, String> mapSeafood = new HashMap<>();
        mapSeafood.put("categoryKor", "해산물");
        mapSeafood.put("categoryEng", "seafood");
        categoryMap.put("seafood", mapSeafood);

        Map<String, String> mapVegetable = new HashMap<>();
        mapVegetable.put("categoryKor", "채소");
        mapVegetable.put("categoryEng", "vegetable");
        categoryMap.put("vegetable", mapVegetable);

        Map<String, String> mapSpice = new HashMap<>();
        mapSpice.put("categoryKor", "향신료");
        mapSpice.put("categoryEng", "spice");
        categoryMap.put("spice", mapSpice);

        model.addAttribute("categorys", categoryMap);
        model.addAttribute("boards", businessBoardService.getList());

    }

    @PostMapping("/board/business/list")
    public void searchList(Model model, HttpServletRequest req) {
        Map<String, Object> searchMap = new HashMap<>();

        searchMap.put("category", req.getParameter("category"));

        model.addAttribute("boards", businessBoardService.getList(searchMap));
    }

    @PostMapping("/board/business/detail")
    public void detail(Long boardBusinessId, Model model) {
        model.addAttribute(businessBoardService.getBoard(boardBusinessId));
    }

    @GetMapping("/board/business/write")
    public void write(Model model) {
        model.addAttribute(new BoardBusinessVO());
        model.addAttribute(new BoardBusinessImgVO());
    }

    @PostMapping("/board/business/write")
    public RedirectView register(BoardBusinessVO boardBusinessVO, HttpServletRequest req) {
        boardBusinessVO.setBusinessId(Long.parseLong(req.getParameter("businessId")));
        businessBoardService.registerBoard(boardBusinessVO);
//        log.info(boardBusinessVO.toString());
        return new RedirectView("/board/business/list");
    }

    @PostMapping("/board/business/delete")
    public void delete(Long boardBusinessId) {
        businessBoardService.remove(boardBusinessId);
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
