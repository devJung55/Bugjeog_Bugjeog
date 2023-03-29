package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardBusinessDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BusinessReviewDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessImgVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.BusinessBoardImgService;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.BusinessBoardService;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.BusinessReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping("/board/business")
@RequiredArgsConstructor
@Slf4j
public class BusinessBoardController {
    private final BusinessBoardService businessBoardService;
    private final BusinessBoardImgService businessBoardImgService;
    private final BusinessReviewService businessReviewService;

    @GetMapping(value = {"/board/business", " "})
    public RedirectView defaultRoot() {
        return new RedirectView("/board/business/list");
    }

    //    리스트
    @GetMapping("/board/business/list")
    public void showList(Model model, HttpServletRequest req) {

        businessBoardService.getList().stream().map(dto -> {
            String name = dto.getBoardBusinessImgOriginalName();
            String fullPath = (name == null || name == "null" || name == "") ? "/image/boardList/no-image-64.png" : (dto.getBoardBusinessImgPath() + "/" + dto.getBoardBusinessImgUuid() + "_" + dto.getBoardBusinessImgOriginalName());
            dto.setBoardBusinessImgFullPath(fullPath);
            return dto;
        });
        model.addAttribute("boards", businessBoardService.getList());
    }

    @ResponseBody
    @PostMapping("/board/business/list")
    public List<BoardBusinessDTO> searchList(Model model, HttpServletRequest req) {
        Map<String, Object> searchMap = new HashMap<>();
        String category = null;
        switch (req.getParameter("category")) {
            case "meat":
                category = "육류";
                break;
            case "seafood":
                category = "해산물";
                break;
            case "spice":
                category = "향신료";
                break;
            case "vegetable":
                category = "채소";
                break;
            default:
                category = null;
                break;
        }
        businessBoardService.getList().stream().map(dto -> {
            String name = dto.getBoardBusinessImgOriginalName();
            String fullPath = (name == null || name == "null" || name == "") ? "/image/boardList/no-image-64.png" : (dto.getBoardBusinessImgPath() + "/" + dto.getBoardBusinessImgUuid() + "_" + dto.getBoardBusinessImgOriginalName());
            dto.setBoardBusinessImgFullPath(fullPath);
            return dto;
        });
//        model.addAttribute("boards", businessBoardService.getList(searchMap));
        searchMap.put("category", category);
        searchMap.put("sort", req.getParameter("sort"));
//            return businessBoardService.getList(searchMap);
        return businessBoardService.getList(searchMap);
    }

//    @GetMapping("/board/business/detail")
//    public String detail(HttpServletRequest request) {
//        String boardBusinessId = request.getParameter("boardBusinessId");
//        return "post:/board/business/detail?boardBusinessId=" + boardBusinessId;
//    }

    @GetMapping("/board/business/detail")
    public void detail(Model model, HttpServletRequest req) {
        System.out.println("컨 들어옴");
        log.info(req.getParameter("boardBusinessId"));

        BoardBusinessDTO dto = businessBoardService.getBoard(Long.parseLong(req.getParameter("boardBusinessId")));
        String name = dto.getBoardBusinessImgOriginalName();
        String fullPath = (name == null || name == "null" || name == "") ? "/image/boardList/no-image-64.png" : (dto.getBoardBusinessImgPath() + "/" + dto.getBoardBusinessImgUuid() + "_" + dto.getBoardBusinessImgOriginalName());
        dto.setBoardBusinessImgFullPath(fullPath);

        List<BusinessReviewDTO> businessReviewDTOs = businessReviewService.getReply(Long.parseLong(req.getParameter("boardBusinessId")));
        businessReviewDTOs.stream().map(reviewDTO -> {
            String orginName = reviewDTO.getMemberImgOriginalName();
            String memberFullPath = (orginName == null || orginName == "null" || orginName == "") ? "/image/mypage/member_no_image.png" : (reviewDTO.getMemberImgPath() + "/" + reviewDTO.getMemberImgUuid() + "_" + reviewDTO.getMemberImgOriginalName());
            reviewDTO.setMemberImgFullPath(memberFullPath);
            return reviewDTO;
        });

        System.out.println(dto.toString());
        System.out.println(businessReviewDTOs.toString());
        List<BoardBusinessDTO> dtos = businessBoardService.getBoardByBusinessId(dto.getBusinessId());
        dtos.stream().map(eventDTO -> {
            String eventName = eventDTO.getBoardBusinessImgOriginalName();
            String eventFullPath = (eventName == null || eventName == "null" || eventName == "") ? "/image/boardList/no-image-64.png" : (eventDTO.getBoardBusinessImgPath() + "/" + eventDTO.getBoardBusinessImgUuid() + "_" + eventDTO.getBoardBusinessImgOriginalName());
            eventDTO.setBoardBusinessImgFullPath(eventFullPath);
            return eventDTO;
        });

//        MemberVO memberVO = businessReviewService.getMember(Long.parseLong(String.valueOf(req.getSession().getAttribute("memberId"))));
        MemberVO memberVO = businessReviewService.getMember(5L);
        log.info("==============");
        log.info(memberVO.getMemberName());
        log.info("==============");
        String orginalName = memberVO.getMemberImgOriginalName();
        String memberFullPath = (orginalName == null || orginalName == "null" || orginalName == "") ? "/image/mypage/member_no_image.png" : (memberVO.getMemberImgPath() + "/" + memberVO.getMemberImgUuid() + "_" + memberVO.getMemberImgOriginalName());
        model.addAttribute("board", dto);
        model.addAttribute("reviews", businessReviewDTOs);
        model.addAttribute("boardList", dtos);
        model.addAttribute("member", memberVO);
        model.addAttribute("memberFullPath", memberFullPath);
    }

    @GetMapping("/board/business/write")
    public void write(Model model) {
        model.addAttribute(new BoardBusinessVO());
        model.addAttribute(new BoardBusinessImgVO[3]);
    }

    @PostMapping("/board/business/write")
    public RedirectView register(BoardBusinessVO boardBusinessVO, BoardBusinessImgVO[] boardBusinessImgVOs, HttpServletRequest req) {
        log.info(req.getParameter("category"));
        businessBoardService.registerBoard(boardBusinessVO);
        Arrays.stream(boardBusinessImgVOs).filter(vo -> vo.getBoardBusinessImgOriginalName()!=null).forEach(vo -> businessBoardImgService.registerImg(vo));
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
