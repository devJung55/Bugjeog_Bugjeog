package com.bugjeogbugjeog.app.bugjeogbugjeog.service;


import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MyPageServiceTests {

    @Autowired
    MyPageService myPageService;

//   회원정보 조회
    @Test
    public void memberInfoTest(){
        log.info(myPageService.memberInfo(1L).toString());
    }

    @Test
    public void memberUpdateTest(){
        MemberVO memberVO = myPageService.memberInfo(1L);

        memberVO.setMemberPhoneNumber("010-4444-5555");
        myPageService.memberUpdate(memberVO);
    }

    
}
