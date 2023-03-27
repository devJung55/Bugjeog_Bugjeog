package com.bugjeogbugjeog.app.bugjeogbugjeog.service;


import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MemberServiceTests {
    @Autowired
    MemberService memberService;

//    자영업자 회원가입
    @Test
    public void insertTest() {
        MemberVO memberVO = new MemberVO();

        memberVO.setMemberEmail("jjy1234@naver.com");
        memberVO.setMemberName("정지영");
        memberVO.setMemberPhoneNumber("01071228966");
        memberVO.setMemberPassword("1234");

        memberService.join(memberVO);
    }

//    이메일 중복검사
    @Test
    public void selectEmail() {
        log.info(memberService.checkEmail("jjy1234@naver.com").toString());
    }

//    휴대폰번호 중복검사
    @Test
    public void selectPhoneNumber() {
        log.info(memberService.checkPhoneNumber("01012341234").toString());
    }

//    자영업자 로그인
    @Test
    public void loginTest() {
        log.info("memberId : " + memberService.login("jjy1234@naver.com", "d2pkd2xkdWQxIQ=="));
    }

//    유통업체 회원가입
    @Test
    public void insertBusinessTest() {
        BusinessVO businessVO = new BusinessVO();

        businessVO.setBusinessEmail("jjy2222@naver.com");
        businessVO.setBusinessCeoName("정지영");
        businessVO.setBusinessCompanyName("주식");
        businessVO.setBusinessEstablishmentDate("2019/05/25");
        businessVO.setBusinessNumber("112-11-11111");
        businessVO.setBusinessPhoneNumber("01012341111");
        businessVO.setBusinessPassword("wjdwldud1!");
        memberService.joinBusiness(businessVO);
    }
}
