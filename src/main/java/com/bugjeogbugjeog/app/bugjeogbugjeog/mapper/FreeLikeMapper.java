package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardFreeVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
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
}
