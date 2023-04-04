package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardReplyDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardFreeVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.FreeReplyVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyDAO {

    private final ReplyMapper replyMapper;

    //    댓글 전체 조회
    public List<BoardReplyDTO> findAll(Criteria criteria, Long boardFreeId){    //boardFreeId 원래는 boardId
        return replyMapper.selectAll(criteria, boardFreeId);
    }
    //    댓글 전체 개수
    public int findCountAll(Long boardFreeId){
        return replyMapper.getTotal(boardFreeId);
    }

    // 댓글 등록
    public void addReply(FreeReplyVO freeReplyVO){ replyMapper.replyAdd(freeReplyVO);}

    // 댓글 갯수
    public Integer getReplyTotal(Long memberId){
        return replyMapper.replyCount(memberId);
    }

    // 게시물의 댓글 개수
    public Integer getReplyCount(Long boardFreeId){ return replyMapper.boardReplyCount(boardFreeId);}


    //  유통업자 댓글 단 갯수
    public Integer getBusinessReplyTotal(Long businessId){
        return replyMapper.businessReplyCount(businessId);
    }

    // 이용자의 댓글 단 게시물 목록
    public List<BoardFreeVO> findAllBoardFreeToMember(@Param("memberId") Long memberId, @Param("criteria") Criteria criteria){
        return replyMapper.selectAll(memberId, criteria);
    }

    // 이용자의 게시물의 댓글 단 리스트
    public List<FreeReplyVO> findAllFreeReplyToMember(Long memberId, Long boardFreeId){
        return replyMapper.replyAllList(memberId, boardFreeId);
    }

    // 이용자의 댓글 단 게시물 목록
    public List<BoardFreeVO> findAllBoardFreeToBusiness(@Param("businessId") Long businessId, @Param("criteria") Criteria criteria){
        return replyMapper.businessSelectAll(businessId, criteria);
    }

    // 이용자의 게시물의 댓글 단 리스트
    public List<FreeReplyVO> findAllFreeReplyToBusiness(Long businessId, Long boardFreeId){
        return replyMapper.businessReplyAllList(businessId, boardFreeId);
    }


}
