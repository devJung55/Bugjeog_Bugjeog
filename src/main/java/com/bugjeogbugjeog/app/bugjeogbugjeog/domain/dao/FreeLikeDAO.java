package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardFreeVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.FreeLikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FreeLikeDAO {

    private final FreeLikeMapper freeLikeMapper;

    //    좋아요 한 게시물 수
    public Integer getCountToLike(Long memberId){
        return freeLikeMapper.likeCount(memberId);
    };

    //    좋아요 한 게시물 리스트
    public List<BoardFreeVO> findAllToLike(Long memberId, Criteria criteria){
        return freeLikeMapper.likeList(memberId, criteria);
    };

    //    좋아요 한 게시물 수
    public Integer getBusinessCountToLike(Long businessId){
        return freeLikeMapper.businessLikeCount(businessId);
    };

    //    좋아요 한 게시물 리스트
    public List<BoardFreeVO> findAllToBusinessLike(Long businessId, Criteria criteria){
        return freeLikeMapper.businessLikeList(businessId, criteria);
    };

}
