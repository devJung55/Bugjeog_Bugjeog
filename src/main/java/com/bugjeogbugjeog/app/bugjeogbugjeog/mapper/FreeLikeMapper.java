package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardFreeVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.FreeLikeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FreeLikeMapper {
    //    좋아요 한 게시물 수
    public Integer likeCount(Long memberId);

    //    좋아요 한 게시물 리스트
    public List<BoardFreeVO> likeList(@Param("memberId") Long memberId, @Param("criteria") Criteria criteria);

    //    좋아요 한 게시물 수
    public Integer businessLikeCount(Long businessId);

    //    좋아요 한 게시물 리스트
    public List<BoardFreeVO> businessLikeList(@Param("businessId") Long businessId, @Param("criteria") Criteria criteria);

    // 좋아요한 게시물 번호 리스트
    public Integer searchLike(FreeLikeVO freeLikeVO);

    //  좋아요 갯수 추가
    public void likeInsert(FreeLikeVO freeLikeVO);

    //  좋아요 갯수 감소
    public void likeDown(FreeLikeVO freeLikeVO);

    public void likeCountUp(Long boardFreeId);

}
