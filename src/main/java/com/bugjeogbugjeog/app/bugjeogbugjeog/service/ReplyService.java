package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.ReplyDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.*;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.FreeReplyVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyDAO replyDAO;

    //    댓글 전체 조회
    public List<FreeReplyVO> getList(Long boardId){    //원래 boardId
        return replyDAO.findAllFreeReplyToMember(boardId);
    }

    //    댓글 전체 개수
    public int getTotal(){
        return replyDAO.findCountAll();
    }

//    /* 댓글 등록*/
    public void save(FreeReplyVO freeReplyVO){

        replyDAO.addReply(freeReplyVO);
    }

//    댓글 수정
     public void update(FreeReplyVO freeReplyVO){
        FreeReplyVO reply = replyDAO.findById(freeReplyVO.getReplyId());
        reply.setReplyContent(freeReplyVO.getReplyContent());
        replyDAO.modifyReply(reply);
     }

//    댓글 삭제
     public void delete(Long replyId){
        replyDAO.deleteReply(replyId);
     }

     // 댓글 전체 목록 조회
    public List<ReplyDTO> selectAllReply(AdminCriteria criteria, Long boardFreeId){
        return replyDAO.findAllByBoard(criteria, boardFreeId);
    }

    // 게시물의 댓글 갯수
    public Integer getReplyCountByFreeBoard(Long boardFreeId){
        return replyDAO.getReplyCountByFreeBoard(boardFreeId);
    }

}
