package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.*;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/replies/*")
public class ReplyController {
    private final ReplyService replyService;
    private final FreeBoardService freeBoardService;


    //    댓글 전체 조회
    @GetMapping("list/{page}")
    public Map<String, Object> showList(Long boardFreeId, @PathVariable("page") int page,  AdminCriteria criteria){
        Map<String, Object> info = new HashMap<>();
        int total = replyService.getReplyCountByFreeBoard(boardFreeId);

        if (criteria.getPage() == 0){
            criteria.create(1,5,total,5);
        } else {
            criteria.create(page,5, total,5);
        }

        info.put("replyDTO", replyService.selectAllReply(criteria, boardFreeId));
        info.put("criteria", criteria);
        return info;
    }

    // 댓글 갯수
    @GetMapping("count")
    public Integer count(@RequestParam("boardFreeId") Long boardFreeId){
        return replyService.getReplyCountByFreeBoard(boardFreeId);
    }

    /*댓글 등록 */
    @PostMapping("register-reply")
    public void replyResister(@RequestBody FreeReplyVO freeReplyVO, HttpSession session){
        Long memberId = (Long)session.getAttribute("memberId");
        Long businessId = (Long)session.getAttribute("businessId");
        log.info("=============================" + freeReplyVO.toString());
        if(memberId != null) {
            freeReplyVO.setMemberId(memberId);
        }else{
            freeReplyVO.setBusinessId(businessId);
        }
        replyService.save(freeReplyVO);
    }

    /*댓글 수정*/
    @PatchMapping("update-reply")
    public void replyUpdate(@RequestBody FreeReplyVO freeReplyVO){
        replyService.update(freeReplyVO);
    }
    
    
/*댓글 삭제*/
    @DeleteMapping("delete-reply")
    public void replyDelete(@RequestParam("replyId") Long replyId) {
        replyService.delete(replyId);
    }

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

        /*  등록 DB에 올라간 댓글 화면에 뿌려주기*/
//    @GetMapping("reply-complate")
//    public String replyAddEnd(Model model){
//        model.addAttribute(new FreeReplyVO());
//        return "/free-boards/detail";
//    }

    }