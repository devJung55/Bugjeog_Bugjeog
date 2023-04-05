package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardBusinessDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BusinessReviewDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.PageDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessImgVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.BoardBusinessImgService;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.BusinessBoardService;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.BusinessInterestingService;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.BusinessReviewService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping("/board/business")
@RequiredArgsConstructor
@Slf4j
public class BusinessBoardController {
    private final BusinessBoardService businessBoardService;
    private final BoardBusinessImgService businessBoardImgService;
    private final BusinessReviewService businessReviewService;
    private final BusinessInterestingService businessInterestingService;
    private final Integer amount = 12;


//    @GetMapping("/board/business/test")
//    public String test() {
//        return "/board/business/boardList";
//    }

    @GetMapping(value = {"/board/business", "/board/business/"})
    public RedirectView defaultRoot() {
        return new RedirectView("/board/business/list");
    }

    //    리스트
//    @GetMapping("/board/business/list")
//    public void showList(Model model) {
//        model.addAttribute("boards", businessBoardService.getList());
//    }
    public Map<String, Object> boardFunction(Long businessId, String category, String sort) {
        Map<String, Object> searchMap = new HashMap<>();
        if (category != null) {
            switch (category) {
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
        }
//        model.addAttribute("boards", businessBoardService.getList(searchMap));
        searchMap.put("category", category == null ? null : category);
        searchMap.put("sort", sort == null ? "recent" : sort);
        searchMap.put("businessId", businessId == null ? null : businessId);

        return searchMap;
    }

    @GetMapping("/board/business/list")
    public void businessList(String category, String sort, Model model, HttpServletRequest req) {
        /* =================== getList pageDTO 받도록 변경됨 */
        Long businessId = req.getParameter("businessId") == null ? null : Long.parseLong(req.getParameter("businessId"));
        model.addAttribute("boards", businessBoardService.showList(new PageDTO(new Criteria(1, amount), Integer.parseInt(String.valueOf(businessBoardService.getCount()))), boardFunction(businessId, category, sort)));
    }

    @GetMapping("/board/business/list/ajax")
    @ResponseBody
    public JSONObject businessAjaxList(Integer pageNum, Long businessId, String category, String sort) {
//        new PageDTO(criteria), Integer.parseInt(String.valueOf(businessBoardService.getCount()))
        /* =================== getList pageDTO 받도록 변경됨 */
        /* 새로운 줄 가져오는 무한 스크롤용 ajax */
        JSONObject returnObj = new JSONObject();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            returnObj.put("boardBusinessDTOs", objectMapper.writeValueAsString(businessBoardService.showList(new PageDTO(new Criteria(pageNum, amount), Integer.parseInt(String.valueOf(businessBoardService.getCount()))), boardFunction(null, category, sort))));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return returnObj;
    }

    @GetMapping("/board/business/list/new")
    @ResponseBody
    public List<BoardBusinessDTO> businessListAjax(String category, String sort) {
        /* =================== 카테고리, 비지니스아이디, 정렬 눌렀을 때 처음부터 새로 받아오는. */
        return businessBoardService.showList(new PageDTO(new Criteria(1, amount), Integer.parseInt(String.valueOf(businessBoardService.getCount()))), boardFunction(null, category, sort));
//        return returnObj;
    }

//    @GetMapping("/board/business/detail")
//    public String detail(HttpServletRequest request) {
//        String boardBusinessId = request.getParameter("boardBusinessId");
//        return "post:/board/business/detail?boardBusinessId=" + boardBusinessId;
//    }

//    @GetMapping("/board/business/detail")
//    @ResponseBody
//    public void detail(Model model, @RequestParam("boardId") Long boardBusinessId, HttpServletRequest req) throws JsonProcessingException {
//
//        System.out.println(boardBusinessId);
//        BoardBusinessDTO dto = businessBoardService.getBoard(boardBusinessId);
////        String name = dto.getBoardBusinessImgOriginalName();
//
//        List<BusinessReviewDTO> businessReviewDTOs = businessReviewService.getReviews(boardBusinessId);
//
//        System.out.println(dto.toString());
//        System.out.println(businessReviewDTOs.toString());
//        List<BoardBusinessDTO> otherBoardDTOs = businessBoardService.getBoardsByBusinessId(dto.getBusinessId());
//
////        MemberVO memberVO = businessReviewService.getMember(Long.parseLong(String.valueOf(req.getSession().getAttribute("memberId"))));
//
//        MemberVO memberVO = null;
//        ObjectMapper objectMapper = new ObjectMapper();
//        Long memberId = (Long) req.getSession().getAttribute("memberId");
//        try {
//            memberVO = businessReviewService.getMember(memberId);
//            String orginalName = memberVO.getMemberImgOriginalName();
//            String memberFullPath = (orginalName == null || orginalName == "null" || orginalName == "") ? "/image/mypage/member_no_image.png" : (memberVO.getMemberImgPath() + "/" + memberVO.getMemberImgUuid() + "_" + memberVO.getMemberImgOriginalName());
//            //  로그인한 사용자 이미지 정보
//            model.addAttribute("memberFullPath", objectMapper.writeValueAsString(memberFullPath));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        model.addAttribute(new BusinessReviewVO());
//
//        //  게시글 정보
//        model.addAttribute("board", objectMapper.writeValueAsString(dto));
//
//        //  게시글 배너 이미지들 정보
//        model.addAttribute("boardImgs", objectMapper.writeValueAsString(businessBoardImgService.getList(dto.getBoardBusinessId())));
//
//        //  게시글 리뷰 정보
//        model.addAttribute("reviews", objectMapper.writeValueAsString(businessReviewDTOs));
//
//        //  게시글 작성자(businessId)의 작성글 리스트
//        model.addAttribute("boardList", objectMapper.writeValueAsString(otherBoardDTOs));
//
//        //  로그인한 사용자 정보
//        model.addAttribute("member", JSONObject.toString("member", memberVO));
//    }

//    @GetMapping("/board/business/detail")
//    @ResponseBody
//    public void detail(@RequestParam("boardId") Long boardBusinessId, Model model, HttpServletRequest req) throws JsonProcessingException {
//
//        BoardBusinessDTO dto = businessBoardService.getBoard(boardBusinessId);
////        String name = dto.getBoardBusinessImgOriginalName();
//
//        List<BusinessReviewDTO> businessReviewDTOs = businessReviewService.getReviews(boardBusinessId);
//
//        System.out.println(dto.toString());
//        System.out.println(businessReviewDTOs.toString());
//        List<BoardBusinessDTO> otherBoardDTOs = businessBoardService.getBoardsByBusinessId(dto.getBusinessId());
//
////        MemberVO memberVO = businessReviewService.getMember(Long.parseLong(String.valueOf(req.getSession().getAttribute("memberId"))));
//        Long memberId = (Long) req.getSession().getAttribute("memberId");
//        ObjectMapper objectMapper = new ObjectMapper();
//        if (memberId != null) {
//            MemberVO memberVO = businessReviewService.getMember(memberId);
//            String orginalName = memberVO.getMemberImgOriginalName();
//            String memberFullPath = (orginalName == null || orginalName == "null" || orginalName == "") ? "/image/mypage/member_no_image.png" : (memberVO.getMemberImgPath() + "/" + memberVO.getMemberImgUuid() + "_" + memberVO.getMemberImgOriginalName());
//            //  로그인한 사용자 정보
//            model.addAttribute("member", JSONObject.toString("member", memberVO));
//
//            //  로그인한 사용자 이미지 정보
//            model.addAttribute("memberFullPath", objectMapper.writeValueAsString(memberFullPath));
//        }
//
//        //  게시글 정보
//        model.addAttribute("board", objectMapper.writeValueAsString(dto));
//
//        //  게시글 배너 이미지들 정보
//        model.addAttribute("boardImgs", objectMapper.writeValueAsString(businessBoardImgService.getList(dto.getBoardBusinessId())));
//
//        //  게시글 리뷰 정보
//        model.addAttribute("reviews", objectMapper.writeValueAsString(businessReviewDTOs));
//
//        //  게시글 작성자(businessId)의 작성글 리스트
//        model.addAttribute("boardList", objectMapper.writeValueAsString(otherBoardDTOs));
//
//
//    }

    @GetMapping("/board/business/detail")
    @ResponseBody
    public String detailAjax(@RequestParam("boardBusinessId") Long boardBusinessId, HttpServletRequest req) {
        // 클라의 success 내 resultData로 갈 값
//        ☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
        System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆");
//        Long boardId = Long.parseLong(boardBusinessId);
        Long boardId = boardBusinessId;
        System.out.println(boardBusinessId);
        System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆");
        BoardBusinessDTO board = businessBoardService.getBoard(boardId);
        List<BusinessReviewDTO> reviews = businessReviewService.getReviews(boardId);
        List<BoardBusinessDTO> boards = businessBoardService.getBoardsByBusinessId(board.getBusinessId());

        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("boardBusinessId", board.getBoardBusinessId());
//        searchMap.put("boardBusinessId", board.getBusinessId().toString());
        List<BoardBusinessDTO> avgDto = businessBoardService.getList(boardFunction(boardBusinessId, null, "recent"));
        Long reviewGrade = null;
        if (avgDto != null) {
            reviewGrade = avgDto.get(0).getBoardBusinessGradeAverage();
        }

        // 서버에서 클라로 전송
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject returnObj = new JSONObject();
        Long memberId = (Long) req.getSession().getAttribute("memberId");

        try {
            if (memberId != null) {
                MemberVO member = businessReviewService.getMember(memberId);
                returnObj.put("member", objectMapper.writeValueAsString(member));
                boolean isThere = businessInterestingService.countByIds(memberId, boardBusinessId) != null;
                returnObj.put("isFavorite", isThere);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

//        String memberImgFullPath = member.getMemberImgPath() + "/" + member.getMemberImgUuid() + "_" + member.getMemberImgOriginalName();
//        memberImgFullPath = member.getMemberImgPath().isBlank() ? "" : memberImgFullPath;
        List<BoardBusinessImgVO> boardImgs = businessBoardImgService.getList(boardId);
        System.out.println(boardImgs.toString());
//        returnObj.put("memberImgFullPath", objectMapper.writeValueAsString(memberImgFullPath));
        try {
            System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆");
            returnObj.put("boardImgs", objectMapper.writeValueAsString(boardImgs));
            System.out.println("☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆");
//        ☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
            returnObj.put("board", objectMapper.writeValueAsString(board));
            returnObj.put("reviews", objectMapper.writeValueAsString(reviews));
            returnObj.put("boards", objectMapper.writeValueAsString(boards));
            returnObj.put("reviewCount", objectMapper.writeValueAsString(reviews.size()));
            if (reviewGrade != null) {
                returnObj.put("reviewGrade", reviewGrade);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
        System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
        System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
        System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
        System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
        System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
        System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
        return returnObj.toJSONString();
    }

    @GetMapping("/board/business/write")
    public void write(Model model) {
        model.addAttribute(new BoardBusinessVO());
    }

    @PostMapping("/board/business/write")
    @ResponseBody
    public Long register(@RequestBody BoardBusinessVO boardBusinessVO, HttpServletRequest req) {
        Long businessId = (Long) req.getSession().getAttribute("businessId");
        boardBusinessVO.setBusinessId(businessId);
        businessBoardService.registerBoard(boardBusinessVO);
        return boardBusinessVO.getBoardBusinessId();
    }

    @PostMapping("/board/business/delete")
    public void delete(Long boardBusinessId) {
        businessBoardService.remove(boardBusinessId);
    }

}
