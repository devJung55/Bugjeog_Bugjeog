package com.bugjeogbugjeog.app.bugjeogbugjeog.service;


import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

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

//    회원정보 업데이트
    @Test
    public void memberUpdateTest(){
        MemberVO memberVO = myPageService.memberInfo(1L);

        memberVO.setMemberPhoneNumber("01044445555");
        myPageService.memberUpdate(memberVO);
    }
    
//    핸드폰 중복검사
    @Test
    public void phoneNumberCheck(){
        log.info(String.valueOf(myPageService.phoneNumberCheck("01012341234")));
    }

    //    회원 탈퇴
    @Test
    public void memberWithdrawTest(Long memberId) {
        myPageService.memberWithdraw(memberId);
    }

    //    이름 변경
    @Test
    public void updateNameTest(){
        myPageService.updateName(1L,"로이");
    }

    //    핸드폰 번호 변경
    @Test
    public void updatePhoneNumberTest(){
        myPageService.updatePhoneNumber(1L,"01066666666");
    }

    // sms 발송 서비스
    @Test
    public void memberSMSTest(String memberPhoneNumber){
        log.info(myPageService.memberSMS("01036758324"));
    }

    // 비밀번호 변경
    @Test
    public void updatePasswordTest(){
        myPageService.updatePassword(1L,"1234");
    }

    // 문의 리스트



    //    유통 분야 설정 수정
    @Test
    public void updateLocationTest(){
        BusinessVO businessVO = myPageService.businessInfo(1L);


        businessVO.setBusinessLocation("경남");
        businessVO.setBusinessCategory("채소");
        myPageService.updateLocation(businessVO);
    }
}
