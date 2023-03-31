package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardBusinessDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardBusinessImgDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BusinessReviewDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessImgVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.BoardBusinessImgService;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.BoardBusinessService;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.BusinessReviewService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
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
    private final BoardBusinessService businessBoardService;
    private final BoardBusinessImgService businessBoardImgService;
    private final BusinessReviewService businessReviewService;

    @GetMapping("/board/business/test")
    public String test() {
        return "/board/business/boardList";
    }

    @GetMapping(value = {"/board/business", " "})
    public RedirectView defaultRoot() {
        return new RedirectView("/board/business/list");
    }

    //    리스트
    @GetMapping("/board/business/list")
    public void showList(Model model) {
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
        ;
//        model.addAttribute("boards", businessBoardService.getList(searchMap));
        searchMap.put("category", category);
        searchMap.put("sort", req.getParameter("sort"));
        return businessBoardService.getList(searchMap);
    }

//    @GetMapping("/board/business/detail")
//    public String detail(HttpServletRequest request) {
//        String boardBusinessId = request.getParameter("boardBusinessId");
//        return "post:/board/business/detail?boardBusinessId=" + boardBusinessId;
//    }

    @GetMapping("/board/business/detail")
    public void detail(Model model, HttpServletRequest req) throws JsonProcessingException {
        log.info(req.getParameter("boardBusinessId"));

        BoardBusinessDTO dto = businessBoardService.getBoard(Long.parseLong(req.getParameter("boardBusinessId")));
        String name = dto.getBoardBusinessImgOriginalName();

        List<BusinessReviewDTO> businessReviewDTOs = businessReviewService.getReviews(Long.parseLong(req.getParameter("boardBusinessId")));

        System.out.println(dto.toString());
        System.out.println(businessReviewDTOs.toString());
        List<BoardBusinessDTO> dtos = businessBoardService.getBoardByBusinessId(dto.getBusinessId());

//        MemberVO memberVO = businessReviewService.getMember(Long.parseLong(String.valueOf(req.getSession().getAttribute("memberId"))));
        MemberVO memberVO = businessReviewService.getMember(5L);
        String orginalName = memberVO.getMemberImgOriginalName();
        String memberFullPath = (orginalName == null || orginalName == "null" || orginalName == "") ? "/image/mypage/member_no_image.png" : (memberVO.getMemberImgPath() + "/" + memberVO.getMemberImgUuid() + "_" + memberVO.getMemberImgOriginalName());
        ObjectMapper objectMapper = new ObjectMapper();
        model.addAttribute("board", objectMapper.writeValueAsString(dto));
        model.addAttribute("reviews", objectMapper.writeValueAsString(businessReviewDTOs));
        model.addAttribute("boardList", objectMapper.writeValueAsString(dtos));
        model.addAttribute("member", JSONObject.toString("member", memberVO));
        model.addAttribute("memberFullPath", objectMapper.writeValueAsString(memberFullPath));
        businessBoardImgService.getList(dto.getBoardBusinessId()).stream().forEach(one -> log.info(one.getBoardBusinessImgOriginalName()));
        model.addAttribute("boardImgs", objectMapper.writeValueAsString(businessBoardImgService.getList(dto.getBoardBusinessId())));
    }

    @PostMapping("/board/business/detail")
    @ResponseBody
    public String detailAjax(@RequestBody Long boardBusinessId) throws Exception {
        log.info(String.valueOf(boardBusinessId));
        // 클라의 success 내 retData로 갈 값
//        ☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
        BoardBusinessDTO board = businessBoardService.getBoard(boardBusinessId);
        List<BusinessReviewDTO> reviews = businessReviewService.getReviews(boardBusinessId);
        List<BoardBusinessDTO> boards = businessBoardService.getBoardByBusinessId(boardBusinessId);
        MemberVO member = businessReviewService.getMember(5L);
        String memberImgFullPath = member.getMemberImgPath() + "/" + member.getMemberImgUuid() + "_" + member.getMemberImgOriginalName();
        try {
            memberImgFullPath = member.getMemberImgPath().isBlank() ? "" : memberImgFullPath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<BoardBusinessImgVO> boardImgs = businessBoardImgService.getList(boardBusinessId);
//        ☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆

        log.info(boardImgs.toString());
        log.info("memberImgFullPath : " + memberImgFullPath);

        // 서버에서 클라로 전송
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject returnObj = new JSONObject();

        returnObj.put("board", objectMapper.writeValueAsString(board));
        returnObj.put("reviews", objectMapper.writeValueAsString(reviews));
        returnObj.put("boards", objectMapper.writeValueAsString(boards));
        returnObj.put("member", objectMapper.writeValueAsString(member));
        returnObj.put("memberImgFullPath", objectMapper.writeValueAsString(memberImgFullPath));
        returnObj.put("boardImgs", objectMapper.writeValueAsString(boardImgs));



        return returnObj.toJSONString();
    }

//    //    파일 저장
//    @PostMapping("/board/business/save")
//    @ResponseBody
//    public void save(@RequestBody List<BoardBusinessImgVO> images) {
//        businessBoardImgService.write(images);
//    }
//
//    //    현재 날짜 경로 구하기
//    private String getPath() {
//        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
//    }

    //    파일 업로드
//    @PostMapping("/board/business/upload")
//    @ResponseBody
//    public RedirectView upload(@RequestParam("file") List<MultipartFile> multipartFiles, HttpServletRequest req) throws IOException {
//        List<String> uuids = new ArrayList<>();
//        String reqBoardBusinessId = req.getParameter("boardBusinessId");
//        Long boardBusinessId = reqBoardBusinessId == null || reqBoardBusinessId == "null" || reqBoardBusinessId == ""
//                ? 3L
//                : Long.parseLong(reqBoardBusinessId);
//        String path = "C:/upload/" + getPath();
//        File file = new File(path);
//        if (!file.exists()) { file.mkdirs(); }
//
//        for (int i = 0; i < multipartFiles.size(); i++) {
//            BoardBusinessImgVO vo = new BoardBusinessImgVO();
//            String uuid = UUID.randomUUID().toString();
//            String originalFileName = multipartFiles.get(i).getOriginalFilename();
//            String fileFullPath = uuid + "_" + originalFileName;
//            vo.setBoardBusinessId(boardBusinessId);
//            vo.setBoardBusinessImgOriginalName(originalFileName);
//            vo.setBoardBusinessImgUuid(uuid);
//            vo.setBoardBusinessImgPath(path);
////            uuids.add(UUID.randomUUID().toString());
//            multipartFiles.get(i).transferTo(new File(path, fileFullPath));
//
//            FileOutputStream out = new FileOutputStream(new File(path, "t_" + fileFullPath));
//            Thumbnailator.createThumbnail(multipartFiles.get(i).getInputStream(), out, 250, 175);
//            out.close();
//            businessBoardImgService.registerImg(vo);
//        }

//        return new RedirectView("/board/business/list");
//    }

    @GetMapping("/board/business/write")
    public void write(Model model) {
        model.addAttribute(new BoardBusinessVO());
    }

    @PostMapping("/board/business/write")
    @ResponseBody
    public Long register(@RequestBody BoardBusinessVO boardBusinessVO, HttpServletRequest req) {
        boardBusinessVO.setBusinessId(3L);
        businessBoardService.registerBoard(boardBusinessVO);
        return boardBusinessVO.getBoardBusinessId();
//        return null;
//        businessBoardImgService.write();
//        List<String> uuids = new ArrayList<>();
//        String reqBoardBusinessId = req.getParameter("boardBusinessId");
//        Long boardBusinessId = reqBoardBusinessId == null || reqBoardBusinessId == "null" || reqBoardBusinessId == ""
//                ? 3L
//                : Long.parseLong(reqBoardBusinessId);
//        String path = "C:/upload/" + getPath();
//        File file = new File(path);
//        if (!file.exists()) { file.mkdirs(); }
//        for (int i = 0; i < multipartFiles.size(); i++) {
//            BoardBusinessImgVO vo = new BoardBusinessImgVO();
//            String uuid = UUID.randomUUID().toString();
//            String originalFileName = multipartFiles.get(i).getOriginalFilename();
//            String fileFullPath = uuid + "_" + originalFileName;
//            vo.setBoardBusinessId(boardBusinessId);
//            vo.setBoardBusinessImgOriginalName(originalFileName);
//            vo.setBoardBusinessImgUuid(uuid);
//            vo.setBoardBusinessImgPath(path);
////            uuids.add(UUID.randomUUID().toString());
//            multipartFiles.get(i).transferTo(new File(path, fileFullPath));
//
//            FileOutputStream out = new FileOutputStream(new File(path, "t_" + fileFullPath));
//            Thumbnailator.createThumbnail(multipartFiles.get(i).getInputStream(), out, 250, 175);
//            out.close();
//            businessBoardImgService.registerImg(vo);
//        }

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
