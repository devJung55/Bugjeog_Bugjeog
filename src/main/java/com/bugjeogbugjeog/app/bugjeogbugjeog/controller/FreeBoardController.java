package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardFreeVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.FreeReplyVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.FreeBoardService;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String freeBoard(){return "/board/free/list";} //html 경로

    /*자유게시판 디테일 */
    @GetMapping("detail")
    public String freeDetail(){return "/board/free/detail";}

    /*자유게시판 작성*/
    @GetMapping("write")
    public String freeWrite(Model model){
        model.addAttribute(new BoardFreeVO());
        return "/board/free/write";
    }

    /*참고하기 mapper*/
    /*작성 후 디비 입력 -> 화면 이동*/
    @PostMapping("free/insert")
    public RedirectView freeInsert(BoardFreeVO boardFreeVO){
        freeBoardService.registerBoard(boardFreeVO);
        return new RedirectView("/FreeBoards/");
    }

    /*댓글 등록*/
    @GetMapping("resister-reply")
    public void replyResister(FreeReplyVO freeReplyVO){
        replyService.save(freeReplyVO);
    }

    /* 댓글 등록완료*/
    @GetMapping("reply-complate")
    public String replyAddEnd(Model model){
        model.addAttribute(new FreeReplyVO());
        return "/FreeBoard/resister-reply";
    }
}
