package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/FreeBoards/")
public class FreeBoardController {
    /*자유게시판 첫 화면(자유게시물 리스트)*/
    @GetMapping("/")
    public String freeBoard(){return "/board/free/list";} //html 경로

    /*자유게시판 디테일 */
    @GetMapping("/detail")
    public String freeDetail(){return "/board/free/detail";}

    /*자유게시판 작성*/
    @GetMapping("/write")
    public String freeWrite(){return "board/free/write";}
}
