package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;


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
public class MypageMapperTest {

    @Autowired
    MyPageMapper myPageMapper;

    //    회원정보 조회
    @Test
    public void selectTest(){
        log.info(myPageMapper.select(1L).toString());
    }

    //    회원정보 수정
    @Test
    public void updateTest(){
        MemberVO memberVO = myPageMapper.select(1L);

        memberVO.setMemberEmail("ljw@naver.com");
        memberVO.setMemberImgOriginalName("1234");
        memberVO.setMemberImgPath("1234");
        memberVO.setMemberImgUuid("1234");

        myPageMapper.update(memberVO);
    }
    
//    휴대폰 정보 조회
    @Test
    public void selectAllPhoneNumberTest(){
        myPageMapper.selectAllPhoneNumber();
    }

//     회원탈퇴
    @Test
    public void deleteMemberTest(){
        myPageMapper.deleteMember(2L);
    }


    //    유통업체 회원정보 조회
    @Test
    public void selectBuisnessTest(){
        myPageMapper.selectBuisness(1L);
    }


    //    좋아요 한 게시물 수
    @Test
    public void likeCountTest(){
        log.info(String.valueOf(myPageMapper.likeCount(1L)));
    }

    //    좋아요 한 게시물 번호들
    @Test
    public void likeListNumberTest(){
        myPageMapper.likeListNumber(1L);
    }

    //    좋아요 한 게시물 리스트
    @Test
    public void likeListTest(){
        List<Long> memberIds = myPageMapper.likeListNumber(1L);
        List<MemberLikeDTO> datas = new ArrayList<>();

        memberIds.stream().forEach(data -> datas.add(myPageMapper.likeList(data)));
       datas.stream().forEach(data -> log.info(data.toString()));
    }

    //    문의 작성 목록
    @Test
    public void inquireListTest(){
        myPageMapper.inquireList(1L);
    }

    //    문의 게시글 작성 갯수
    @Test
    public void inquireCountTest(){
        myPageMapper.inquireCount(1L);
    }

    //    문의 게시글 답변 유무 체크
    @Test
    public void answerCheckTest(){
        log.info(String.valueOf(myPageMapper.answerCheck(1L) == null));
    }

}
