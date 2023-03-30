package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.ReplyDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.FreeReplyVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyDAO replyDAO;

//    댓글 등록
    public void save(FreeReplyVO freeReplyVO){ replyDAO.addReply(freeReplyVO);}
}
