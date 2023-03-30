package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MyPageReplyDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardFreeVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.FreeReplyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {

    // 댓글 등록
    public void replyAdd(FreeReplyVO freeReplyVO);

    //  댓글 단 게시물 목록
    public List<MyPageReplyDTO> replyList(@Param("memberId") Long memberId, @Param("criteria") Criteria criteria);

    //    댓글 단 갯수
    public Integer replyCount(Long memberId);

    // 게시물의 댓글 개수
    public Integer boardReplyCount(Long boardFreeId);

    //  유통업자 댓글 단 게시물 목록
    public List<MyPageReplyDTO> businessReplyList(@Param("businessId") Long businessId, @Param("criteria") Criteria criteria);

    //  유통업자 댓글 단 갯수
    public Integer businessReplyCount(Long businessId);

    // 이용자의 댓글 단 게시물 목록
    public List<BoardFreeVO> selectAll(@Param("memberId") Long memberId, @Param("criteria") Criteria criteria);

    // 이용자의 게시물의 댓글 단 리스트
    public List<FreeReplyVO> replyAllList(Long memberId, Long boardFreeId);
}
