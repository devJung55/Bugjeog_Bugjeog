package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.AdminCriteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardReplyDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.PageDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.ReplyDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardFreeVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.FreeReplyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReplyMapper {

    //    댓글 전체 조회
    public List<FreeReplyVO> selectAll();

    //    댓글 전체 개수
    public int getTotal();

    // 댓글 등록
    public void replyAdd(FreeReplyVO freeReplyVO);

    // 댓글 수정
    public void replyUpdate(FreeReplyVO freeReplyVO);

    // 댓글 삭제
    public void replyDelete(Long replyId);


    //    댓글 단 갯수
    public Integer replyCount(Long memberId);

    //  유통업자 댓글 단 갯수
    public Integer businessReplyCount(Long businessId);

    // 이용자의 댓글 단 게시물 목록
    public List<BoardFreeVO> selectAll(@Param("memberId") Long memberId, @Param("criteria") Criteria criteria);

    // 이용자의 게시물의 댓글 단 리스트
    public List<FreeReplyVO> replyAllList(Long boardFreeId);

    // 유통업자의 댓글 단 게시물 목록
    public List<BoardFreeVO> businessSelectAll(@Param("businessId") Long businessId, @Param("criteria") Criteria criteria);

    // 유통업자의 게시물의 댓글 단 리스트
    public List<FreeReplyVO> businessReplyAllList(Long businessId, Long boardFreeId);

    // 게시물의 댓글 개수
    public Integer boardReplyCount(Long boardFreeId);

    // 댓글 조회
    public FreeReplyVO select(Long replyId);

    // 게시물의 댓글 전체 조회
    public List<ReplyDTO> selectAllReplyList(@Param("cri") AdminCriteria adminCriteria, @Param("boardFreeId") Long boardFreeId);

    // 게시물의 댓글 갯수
    public Integer replyCountFreeBoard(Long boardFreeId);
}
