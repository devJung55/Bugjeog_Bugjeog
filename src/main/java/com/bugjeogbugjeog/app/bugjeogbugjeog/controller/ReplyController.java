package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardFreeDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardReplyDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.PageDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.FreeReplyVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.ReplyMapper;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.FreeBoardService;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/replies/*")
public class ReplyController {
    private final ReplyService replyService;
    private final FreeBoardService freeBoardService;


    //    댓글 전체 조회
    @GetMapping("detail/{boardFreeId}")
    public String showList(@PathVariable("boardFreeId") Long boardFreeId, Model model){
        List<BoardFreeDTO> boardList = freeBoardService.getListBoard(boardFreeId);
        model.addAttribute("replyLists", replyService.getList());

        return "free-boards";
    }


    /*댓글 등록완료 용준*/
    /*@PostMapping("register-reply")
    public RedirectView replyResister(FreeReplyVO freeReplyVO){
        replyService.save(freeReplyVO);

        return new RedirectView("/free-boards/detail");
    }*/

    /*댓글 목록*/
    /*@GetMapping("resister-reply")               //model = 객체 DB에서 화면에 전달해줄 때 사용하는 객체
    public String replyResister(PageDTO pageDTO, Model model){
        int total = 0;
        total = replyService.getTotal();
        List<BoardFreeDTO> boardList = freeBoardService.getListBoard(boardFreeId);
        model.addAttribute("replyList", replyService.getList(pageDTO));


        return "/free-boards/detail";
    }*/


    /*댓글 디테일*/
    /*@GetMapping("detail/{boardFreeId}")
    public String freeDetail(@PathVariable("boardFreeId") Long boardFreeId, Model model){
        List<BoardFreeDTO> boardList = freeBoardService.getListBoard(boardFreeId);
        model.addAttribute("prevBoard", boardList.get(0));
        model.addAttribute("currentBoard", boardList.get(1));
        model.addAttribute("nextBoard", boardList.get(2));

        log.info("========" + boardList);

        return "/board/free/detail";
   }*/

    /*댓글 등록 성공*/
    @PostMapping("resister-reply")
    public RedirectView replyResister(FreeReplyVO freeReplyVO, HttpSession httpSession){
        /*아래 두줄 주석 풀면 */
        freeReplyVO.setMemberId(((MemberVO)httpSession.getAttribute("member")).getMemberId());
//        /*freeReplyVO.setBusinessId(((BusinessVO)httpSession.getAttribute("business")).getBusinessId());*/
        replyService.save(freeReplyVO);

        return new RedirectView("/free-boards/");
    }

    /* 댓글 등록완료*/
//    @GetMapping("reply-complate")
//    public String replyAddEnd(Model model){
//        model.addAttribute(new FreeReplyVO());
//        return "/free-boards/detail";
//    }

}
