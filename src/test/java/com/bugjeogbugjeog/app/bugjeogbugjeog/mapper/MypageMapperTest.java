package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;


import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MemberLikeDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardInquiryVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
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

}
