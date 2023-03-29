package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MyPageReplyDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyDAO {

    private final ReplyMapper replyMapper;

    //  댓글 단 게시물 목록
    public List<MyPageReplyDTO> findAllMyPageReplyDTO(Long memberId, Criteria criteria){
        return replyMapper.replyList(memberId, criteria);
    }

    // 댓글 갯수
    public Integer getReplyTotal(Long memberId){
        return replyMapper.replyCount(memberId);
    }

    // 게시물의 댓글 개수
    public Integer getReplyCount(Long boardFreeId){ return replyMapper.boardReplyCount(boardFreeId);}


}
