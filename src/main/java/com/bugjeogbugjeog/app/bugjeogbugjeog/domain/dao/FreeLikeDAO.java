package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardFreeVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.FreeLikeVO;
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


    // 좋아요 한 게시물 번호 리스트
    public Integer findByIdBoardFreeId(FreeLikeVO freeLikeVO){
        return freeLikeMapper.searchLike(freeLikeVO);
    }

    //좋아요 누르면 insert 좋아요 +1
    public void saveFreeLike(FreeLikeVO freeLikeVO){
        freeLikeMapper.likeInsert(freeLikeVO);
    }

    //좋아요 누르면 delete 좋아요 취소 -1
    public void removeFreeLike(FreeLikeVO freeLikeVO){
        freeLikeMapper.likeDown(freeLikeVO);
    }
    
    //해당 좋아요 게시판 글 번호(boardFreeId) 조회
    public void updateCount(Long boardFreeId){
        freeLikeMapper.likeCountUp(boardFreeId);
    }
    
    // 해당 좋아요 개수
    public Integer getLikeCount(Long boardFreeId) { return freeLikeMapper.selectLikeCount(boardFreeId);}

}
