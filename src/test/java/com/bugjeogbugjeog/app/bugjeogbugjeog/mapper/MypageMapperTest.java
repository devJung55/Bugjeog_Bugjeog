package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;


import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MemberLikeDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
public class MypageMapperTest {

    @Autowired
    ReplyMapper replyMapper;

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    BusinessMapper businessMapper;

    @Autowired
    FreeLikeMapper freeLikeMapper;

    @Autowired
    InquiryBoardMapper inquiryBoardMapper;



//    //    회원정보 조회
//    @Test
//    public void selectTest(){
//        log.info(memberMapper.select(1L).toString());
//    }
//
//    //    회원정보 수정
//    @Test
//    public void updateTest(){
//        MemberVO memberVO = memberMapper.select(1L);
//
//        memberVO.setMemberEmail("ljw@naver.com");
//        memberVO.setMemberImgOriginalName("1234");
//        memberVO.setMemberImgPath("1234");
//        memberVO.setMemberImgUuid("1234");
//
//        memberMapper.update(memberVO);
//    }
//
////    휴대폰 정보 조회
//    @Test
//    public void selectAllPhoneNumberTest(){
//        memberMapper.selectAllPhoneNumber();
//    }
//
////     회원탈퇴
//    @Test
//    public void deleteMemberTest(){
//        memberMapper.deleteMember(2L);
//    }
//
//
//    //    유통업체 회원정보 조회
//    @Test
//    public void selectBuisnessTest(){
//        businessMapper.selectBuisness(1L);
//    }
//
//
//    //    좋아요 한 게시물 수
//    @Test
//    public void likeCountTest(){
//        log.info(String.valueOf(freeLikeMapper.likeCount(1L)));
//    }
//
//    //    좋아요 한 게시물 리스트
//    @Test
//    public void likeListTest(){
//        freeLikeMapper.likeList(1L,new Criteria(1,4));
//    }
//
//    //    문의 작성 목록
//
//    //    문의 게시글 작성 갯수
//    @Test
//    public void inquireCountTest(){
//        inquiryBoardMapper.inquireCount(1L);
//    }
//
//    //유통 분야 설정 추가 select로 먼저 가져와서 추가후 insert
//    @Test
//    public void updateLocationTest(){
//        BusinessVO businessVO = businessMapper.selectBusisness(1L);
//        businessVO.setBusinessLocation("서울");
//        businessVO.setBusinessCategory("향신료");
//        businessMapper.updateLocation(businessVO);
//    }
//
//    @Test
//    public void replyListTest(){
//        replyMapper.replyList(1L,new Criteria(1,4));
//    }

    
    // 좋아요 갯수 테스트
    @Test
    public void likeCount() {
        freeLikeMapper.businessLikeCount(4L);
    }

    // 좋아요 한 게시물 목록
    @Test
    public void likeListTest(){
        freeLikeMapper.businessLikeList(4L,new Criteria(1,4)).stream().forEach(data -> log.info(data.toString()));
    }

    // 댓글 단 갯수
    @Test
    public void replyCountTest(){
        replyMapper.businessReplyCount(4L);
    }

    // 관심 등록한 갯수
    @Test
    public void interestingCountTest() {
        memberMapper.interestingBusinessCount(1L);
    }

    // 리뷰 평점
    @Test
    public void reviewGradeTest(){
        businessMapper.reviewGrade(4L);
    }

    // 좋아요 게시물 여부 확인
    @Test
    public void searchLikeTest(){
        FreeLikeVO freeLikeVO = new FreeLikeVO();
        Long a = null;
        freeLikeVO.setBusinessId(a);
        freeLikeVO.setMemberId(1L);

        freeLikeMapper.searchLike(freeLikeVO);
    }

    // 좋아요 업
    public void insertLike(){
        FreeLikeVO freeLikeVO = new FreeLikeVO();
        Long a = null;
        freeLikeVO.setBusinessId(a);
        freeLikeVO.setMemberId(1L);

        freeLikeMapper.likeInsert(freeLikeVO);
    }

    // 좋아요 다운
    @Test
    public void removeLike(){
        FreeLikeVO freeLikeVO = new FreeLikeVO();
        Long a = null;
        freeLikeVO.setBusinessId(a);
        freeLikeVO.setMemberId(1L);

        freeLikeMapper.likeDown(freeLikeVO);
    }

    // 좋아요 갯수 업데이트
    @Test
    public void updateFreeCount(){
        FreeLikeVO freeLikeVO = new FreeLikeVO();
        Long a = null;
        freeLikeVO.setBusinessId(a);
        freeLikeVO.setMemberId(1L);

//        freeLikeMapper.likeCountUp(freeLikeVO);
    }
}
