package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardBusinessDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BusinessReviewDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessImgVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessReviewVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.BoardBusinessImgService;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.BusinessBoardService;
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

    @GetMapping("/board/business/list")
    public void businessList(Long businessId, String category, String sort, Model model) throws Exception {
//        model.addAttribute(businessBoardService.getBoardsByBusinessId(businessId));
        System.out.println("/board/business/list 들어옴");
        System.out.println(category);
        Map<String, Object> searchMap = new HashMap<>();
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
//        model.addAttribute("boards", businessBoardService.getList(searchMap));
        if (category != null) {
            searchMap.put("category", category == null ? null : category);
        }
        if (sort != null) {
            searchMap.put("sort", sort == null ? "recent" : sort);
        }
        if (businessId != null) {
            searchMap.put("businessId", businessId == null ? null : businessId);
        }
        if (category == null && sort == null && businessId == null) {
            model.addAttribute("boards", businessBoardService.getList());
        } else {
            model.addAttribute("boards", businessBoardService.getList(searchMap));
        }
    }

//    @GetMapping("/board/business/detail")
//    public String detail(HttpServletRequest request) {
//        String boardBusinessId = request.getParameter("boardBusinessId");
//        return "post:/board/business/detail?boardBusinessId=" + boardBusinessId;
//    }

    @GetMapping("/board/business/detail")
    public void detail(Model model, @RequestParam("boardId") Long boardBusinessId, HttpServletRequest req) throws JsonProcessingException {

        BoardBusinessDTO dto = businessBoardService.getBoard(boardBusinessId);
        String name = dto.getBoardBusinessImgOriginalName();

        List<BusinessReviewDTO> businessReviewDTOs = businessReviewService.getReviews(boardBusinessId);

        System.out.println(dto.toString());
        System.out.println(businessReviewDTOs.toString());
        List<BoardBusinessDTO> otherBoardDTOs = businessBoardService.getBoardsByBusinessId(dto.getBusinessId());

//        MemberVO memberVO = businessReviewService.getMember(Long.parseLong(String.valueOf(req.getSession().getAttribute("memberId"))));

        MemberVO memberVO = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            memberVO = businessReviewService.getMember((Long) req.getSession().getAttribute("memberId"));
            String orginalName = memberVO.getMemberImgOriginalName();
            String memberFullPath = (orginalName == null || orginalName == "null" || orginalName == "") ? "/image/mypage/member_no_image.png" : (memberVO.getMemberImgPath() + "/" + memberVO.getMemberImgUuid() + "_" + memberVO.getMemberImgOriginalName());
            //  로그인한 사용자 이미지 정보
            model.addAttribute("memberFullPath", objectMapper.writeValueAsString(memberFullPath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute(new BusinessReviewVO());

        //  게시글 정보
        model.addAttribute("board", objectMapper.writeValueAsString(dto));

        //  게시글 배너 이미지들 정보
        model.addAttribute("boardImgs", objectMapper.writeValueAsString(businessBoardImgService.getList(dto.getBoardBusinessId())));

        //  게시글 리뷰 정보
        model.addAttribute("reviews", objectMapper.writeValueAsString(businessReviewDTOs));

        //  게시글 작성자(businessId)의 작성글 리스트
        model.addAttribute("boardList", objectMapper.writeValueAsString(otherBoardDTOs));

        //  로그인한 사용자 정보
        model.addAttribute("member", JSONObject.toString("member", memberVO));
    }

    @PostMapping("/board/business/detail")
    @ResponseBody
    public String detailAjax(@RequestBody Long boardBusinessId, @RequestBody Long memberId) throws Exception {
        // 클라의 success 내 resultData로 갈 값
//        ☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
        BoardBusinessDTO board = businessBoardService.getBoard(boardBusinessId);
        List<BusinessReviewDTO> reviews = businessReviewService.getReviews(boardBusinessId);
        List<BoardBusinessDTO> boards = businessBoardService.getBoardsByBusinessId(boardBusinessId);

        MemberVO member = businessReviewService.getMember(memberId);
        String memberImgFullPath = member.getMemberImgPath() + "/" + member.getMemberImgUuid() + "_" + member.getMemberImgOriginalName();
        memberImgFullPath = member.getMemberImgPath().isBlank() ? "" : memberImgFullPath;
        List<BoardBusinessImgVO> boardImgs = businessBoardImgService.getList(boardBusinessId);
//        ☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆

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
    }

    @PostMapping("/board/business/delete")
    public void delete(Long boardBusinessId) {
        businessBoardService.remove(boardBusinessId);
    }

}
