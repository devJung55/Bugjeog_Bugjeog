package com.bugjeogbugjeog.app.bugjeogbugjeog.dao;


import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.MypageDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MemberLikeDTO;
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
    MypageDAO mypageDAO;

    @Test
    public void findByIdTest(){
        log.info(mypageDAO.findById(1L).toString());
    }

    @Test
    public void updateByIdTest(){
        MemberVO memberVO = mypageDAO.findById(1L);

        memberVO.setMemberEmail("hs@naver.com");
        memberVO.setMemberImgOriginalName("12345");
        memberVO.setMemberImgPath("12345");
        memberVO.setMemberImgUuid("12345");

        mypageDAO.updateById(memberVO);
    }

    // 핸드폰 전체 조회
    @Test
    public void findAllToMemberPhoneNumber(){
        mypageDAO.findAllToMemberPhoneNumber();
    }

    //    유통업체 회원정보 조회
    @Test
    public void selectBusinessTest(){
        mypageDAO.findByIdToBusiness(1L);
    }


    //    좋아요 한 게시물 수
    @Test
    public void likeCountTest(){
        log.info(String.valueOf(mypageDAO.getCountToLike(1L)));
    }

    //    좋아요 한 게시물 번호들
    @Test
    public void likeListNumberTest(){
        mypageDAO.findAllToLikeNumber(1L);
    }

    //    좋아요 한 게시물 리스트
    @Test
    public void likeListTest(){
        List<Long> memberIds = mypageDAO.findAllToLikeNumber(1L);
        List<MemberLikeDTO> datas = new ArrayList<>();

        memberIds.stream().forEach(data -> datas.add(mypageDAO.findAllToLike(data)));
        datas.stream().forEach(data -> log.info(data.toString()));
    }

    //    문의 작성 목록
    @Test
    public void inquireListTest(){
        mypageDAO.findAllByIdToInquire(1L);
    }

    //    문의 게시글 작성 갯수
    @Test
    public void inquireCountTest(){
        mypageDAO.getCountToInquire(1L);
    }

    //    문의 게시글 답변 유무 체크
    @Test
    public void answerCheckTest(){
        log.info(String.valueOf(mypageDAO.findByIdToBoardInquiryAnswerCheck(1L) == null));
    }
}
