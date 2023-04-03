package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardFreeVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.FreeReplyVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.FreeBoardService;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/FreeBoards/*")
@RequiredArgsConstructor
public class FreeBoardController {
    /*주입*/
    private final FreeBoardService freeBoardService;
    private final ReplyService replyService;

    /*자유게시판 첫 화면(자유게시물 리스트)*/
    @GetMapping("/")
    public String freeBoard(Long boardFreeId, Model model){
        model.addAttribute("boardLists", freeBoardService.getList());

        return "/board/free/list";} //html 경로


    /*자유게시판 디테일 */
    @GetMapping("detail/{boardFreeId}")
    public String freeDetail(@PathVariable("boardFreeId") Long boardFreeId, Model model){
        model.addAttribute("boardFreeId",freeBoardService.getListBoard(boardFreeId));

        return "/board/free/detail";}

    /*자유게시판 작성*/
    @PostMapping("write")
    public RedirectView freeWrite(BoardFreeVO boardFreeVO){
     /*   model.addAttribute(new BoardFreeVO());*/
        freeBoardService.registerBoard(boardFreeVO);
        return new RedirectView("/FreeBoards/");
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

        return new RedirectView("/FreeBoards/detail");
    }

    /* 댓글 등록완료*/
    @GetMapping("reply-complate")
    public String replyAddEnd(Model model){
        model.addAttribute(new FreeReplyVO());
        return "/FreeBoards/detail";
    }

}
