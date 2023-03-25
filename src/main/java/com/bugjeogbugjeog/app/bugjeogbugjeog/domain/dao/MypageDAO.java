package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MemberInquireDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MemberLikeDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.MyPageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MypageDAO {
    private final MyPageMapper myPageMapper;

    //    회원정보 조회
    public MemberVO findById(Long memberId){
        return myPageMapper.select(memberId);
    };
    
    // 회원정보 수정
    public void updateById(MemberVO memberVO){
        myPageMapper.update(memberVO);
    };

    //    유통업체 회원정보 조회
    public BusinessVO findByIdToBusiness(Long businessId){
        return myPageMapper.selectBuisness(businessId);
    };

    //    좋아요 한 게시물 수
    public Integer getCountToLike(Long memberId){
        return myPageMapper.likeCount(memberId);
    };

    //    좋아요 한 게시물 번호들
    public List<Long> findAllToLikeNumber(Long memberId){
        return myPageMapper.likeListNumber(memberId);
    };

    //    좋아요 한 게시물 리스트
    public MemberLikeDTO findAllToLike(Long boardFreeId){
        return myPageMapper.likeList(boardFreeId);
    };

    //    문의 작성 목록
    public List<MemberInquireDTO> findAllByIdToInquire(Long memberId){
        return myPageMapper.inquireList(memberId);
    };

    //    문의 게시글 작성 갯수
    public Integer getCountToInquire(Long memberId){
        return myPageMapper.inquireCount(memberId);
    };

    //    문의 게시글 답변 유무 체크
    public Long findByIdToBoardInquiryAnswerCheck(Long boardInquiryId){
        return myPageMapper.answerCheck(boardInquiryId);
    };

}
