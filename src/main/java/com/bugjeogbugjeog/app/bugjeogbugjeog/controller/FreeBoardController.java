package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.AdminCriteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardFreeDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.SearchDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.enums.SearchEnum;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardFreeVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.FreeReplyVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.BusinessService;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.FreeBoardService;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.MemberService;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/free-boards/*")
@RequiredArgsConstructor
@Slf4j
public class FreeBoardController {
    /*주입*/
    private final FreeBoardService freeBoardService;
    private final ReplyService replyService;
    private final BusinessService businessService;
    private final MemberService memberService;

    /*자유게시판 첫 화면(자유게시물 리스트)*/
    @GetMapping("/")
    public String freeBoard(AdminCriteria criteria, Model model, HttpServletRequest request){

        MemberVO memberVO = new MemberVO();
        Object memberId = request.getSession().getAttribute("memberId");
        if(memberId == null) model.addAttribute("member", null);
        else model.addAttribute("member", memberService.showMember((Long)memberId));

        model.addAttribute("businessReviewTop10", businessService.getListByReviewRank());
        model.addAttribute("criteria", criteria);

        return "/board/free/list";

    } //html 경로

    @GetMapping("boards")
    @ResponseBody
    public List<BoardFreeDTO> getBoards(AdminCriteria criteria){

        int currentPage = 0;
        int rowCount = 5;
        int total = freeBoardService.getTotal();
        int pageCount = 5;

        if(criteria.getPage() == 0) {
            currentPage = 1;
        } else {
            currentPage = criteria.getPage();
        }
        criteria.setSearchDTO(new SearchDTO().setOrderColumn(SearchEnum.BOARD_FREE_ID));
        criteria.create(currentPage, rowCount, total, pageCount);

        return freeBoardService.getListWithName(criteria);
    }


    /*자유게시판 디테일 */
    @GetMapping("detail/{boardFreeId}")
    public String freeDetail(@PathVariable("boardFreeId") Long boardFreeId, Model model){
        List<BoardFreeDTO> boardList = freeBoardService.getListBoard(boardFreeId);
        model.addAttribute("prevBoard", boardList.get(0));
        model.addAttribute("currentBoard", boardList.get(1));
        model.addAttribute("nextBoard", boardList.get(2));

        log.info("========" + boardList);

        return "/board/free/detail";
    }

    /*자유게시판 작성*/
    @PostMapping("write")
    public RedirectView freeWrite(BoardFreeVO boardFreeVO){
     /*   model.addAttribute(new BoardFreeVO());*/
        freeBoardService.registerBoard(boardFreeVO);
        return new RedirectView("/free-boards/");
    }

    /*참고하기 mapper*/
    /*자유게시판 작성페이지 */
    @GetMapping("write")
    public String freeWrite() {
        return "/board/free/write";

    }

    /*댓글 목록*/
    @GetMapping("resister-reply")
    public String replyResister(){
        return "/board/free/detail";
    }

    /*댓글 등록*/
    @PostMapping("resister-reply")
    public RedirectView replyResister(FreeReplyVO freeReplyVO){

        replyService.save(freeReplyVO);

        return new RedirectView("/free-boards/detail");
    }

    /* 댓글 등록완료*/
    @GetMapping("reply-complate")
    public String replyAddEnd(Model model){
        model.addAttribute(new FreeReplyVO());
        return "/free-boards/detail";
    }

}
