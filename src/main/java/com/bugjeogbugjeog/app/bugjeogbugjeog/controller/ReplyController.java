package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardReplyDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.FreeReplyVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.ReplyMapper;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/replies/*")
public class ReplyController {
    private final ReplyService replyService;

    //    댓글 전체 조회
//    @GetMapping("list")
//    public List<BoardReplyDTO> showList(Criteria criteria, Long boardId){
//        return replyService.getList(criteria, boardId);
//    }

    /*댓글 등록 용준*/
//    @GetMapping("register-reply")
//    public void register(Model model){
//        model.addAttribute(new FreeReplyVO());
//    }

    /*댓글 등록완료 용준*/
//    @PostMapping("register-reply")
//    public RedirectView replyResister(FreeReplyVO freeReplyVO){
//        replyService.save(freeReplyVO);
//
//        return new RedirectView("/free-boards/detail");
//    }

    /*댓글 목록*/
//    @GetMapping("resister-reply")
//    public String replyResister(){
//        return "/board/free/detail";
//    }

    /*댓글 등록*/
    @PostMapping("resister-reply")
    public RedirectView replyResister(FreeReplyVO freeReplyVO){

        replyService.save(freeReplyVO);

        return new RedirectView("/free-boards/detail");
    }

    /* 댓글 등록완료*/
//    @GetMapping("reply-complate")
//    public String replyAddEnd(Model model){
//        model.addAttribute(new FreeReplyVO());
//        return "/free-boards/detail";
//    }

}
