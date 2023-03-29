package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardFreeVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.FreeBoardService;
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

    private final FreeBoardService freeBoardService;

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

    /*중요!! 참고하기 mapper*/
    /*작성 후 디비 입력 -> 화면 이동*/
    @PostMapping("free/insert")
    public RedirectView freeInsert(BoardFreeVO boardFreeVO){
        freeBoardService.registerBoard(boardFreeVO);
        return new RedirectView("/FreeBoards/");
    }
}
