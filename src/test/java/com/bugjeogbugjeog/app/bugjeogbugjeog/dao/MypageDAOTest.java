package com.bugjeogbugjeog.app.bugjeogbugjeog.dao;


import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.BusinessDAO;
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
public class MypageDAOTest {

    @Autowired
    BusinessDAO businessDAO;

//    @Test
//    public void findByIdTest(){
//        log.info(mypageDAO.findById(1L).toString());
//    }
//
//    @Test
//    public void updateByIdTest(){
//        MemberVO memberVO = mypageDAO.findById(1L);
//
//        memberVO.setMemberEmail("hs@naver.com");
//        memberVO.setMemberImgOriginalName("12345");
//        memberVO.setMemberImgPath("12345");
//        memberVO.setMemberImgUuid("12345");
//
//        mypageDAO.updateById(memberVO);
//    }
//
//    @Test
//    public void deleteByIdTest(){
//        mypageDAO.deleteById(3L);
//    }
//
//    // 핸드폰 전체 조회
//    @Test
//    public void findAllToMemberPhoneNumber(){
//        mypageDAO.findAllToMemberPhoneNumber();
//    }
//
//    //    유통업체 회원정보 조회
//    @Test
//    public void selectBusinessTest(){
//        mypageDAO.findByIdToBusiness(1L);
//    }
//
//
//    //    좋아요 한 게시물 수
//    @Test
//    public void likeCountTest(){
//        log.info(String.valueOf(mypageDAO.getCountToLike(1L)));
//    }
//
//    //    좋아요 한 게시물 리스트
//
//    //    문의 작성 목록
//
//    //    문의 게시글 작성 갯수
//    @Test
//    public void inquireCountTest(){
//        mypageDAO.getCountToInquire(1L);
//    }
//
//    //유통 분야 설정 수정
//    @Test
//    public void updateByLocationTest(){
//        BusinessVO businessVO = mypageDAO.findByIdToBusiness(1L);
//
//        businessVO.setBusinessLocation("전북");
//        businessVO.setBusinessCategory("육류");
//
//        mypageDAO.updateLocation(businessVO);
//    }


    // 사업자 번호 중복검사
    @Test
    public void businessNumberCheckTest(){
//        businessDAO.findAllToBusinessNumber().stream().filter(data -> data.equals("12-1111-123")).forEach(log::info);
        businessDAO.findAllToBusinessNumber().stream().forEach(log::info);
    }
}
