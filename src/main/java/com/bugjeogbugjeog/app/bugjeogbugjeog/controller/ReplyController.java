package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardReplyDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.FreeReplyVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/replies/*")
public class ReplyController {
    private final ReplyService replyService;

    //    댓글 전체 조회
    @GetMapping("list")
    public List<BoardReplyDTO> showList(Criteria criteria, Long boardId){
        return replyService.getList(criteria, boardId);
    }

}
