package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MemberInquireDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MemberLikeDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MyPageReplyDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.*;
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.MyPageMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MypageDAO {
    private final MyPageMapper myPageMapper;


    //    유통업체 회원정보 조회
    public BusinessVO findByIdToBusiness(Long businessId){
        return myPageMapper.selectBuisness(businessId);
    };

    //    좋아요 한 게시물 수
    public Integer getCountToLike(Long memberId){
        return myPageMapper.likeCount(memberId);
    };

    //    좋아요 한 게시물 리스트
    public List<BoardFreeVO> findAllToLike(Long boardFreeId, Criteria criteria){
        return myPageMapper.likeList(boardFreeId, criteria);
    };

    //    문의 작성 목록
    public List<BoardInquiryVO> findAllByIdToInquire(Long memberId, Criteria criteria){
        return myPageMapper.inquireList(memberId,criteria);
    };

    //    문의 게시글 작성 갯수
    public Integer getCountToInquire(Long memberId){
        return myPageMapper.inquireCount(memberId);
    };

    // 문의 답변 여부
    public Long inquireAnswer(Long boardInquireId){
        return myPageMapper.inquireAnswer(boardInquireId);
    }

    //  댓글 단 게시물 목록
    public List<MyPageReplyDTO> findAllMyPageReplyDTO(Long memberId,Criteria criteria){
        return myPageMapper.replyList(memberId, criteria);
    };

    // 댓글 갯수
    public Integer getReplyTotal(Long memberId){
        return myPageMapper.replyCount(memberId);
    };

    // 자유게시판 글 목록
    public List<BoardFreeVO> findByIdBoardFreeVO(Long memberId, Criteria criteria) {
        return myPageMapper.freeList(memberId, criteria);
    }

    // 자유게시판 갯수
    public Integer getFreeBoardTotal(Long memberId){
        return myPageMapper.freeCount(memberId);
    }

    //유통 분야 설정 수정
    public void updateLocation(BusinessVO businessVO) {myPageMapper.updateLocation(businessVO);};
}

