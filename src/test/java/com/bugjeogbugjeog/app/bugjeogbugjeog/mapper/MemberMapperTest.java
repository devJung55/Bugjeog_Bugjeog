package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MemberMapperTest {
    @Autowired
    MemberMapper memberMapper;

    //    자영업자 회원가입
    @Test
    public void insertMemberTest() {
        MemberVO memberVO = new MemberVO();

        memberVO.setMemberEmail("kjp1234@naver.com");
        memberVO.setMemberName("킹자지");
        memberVO.setMemberPhoneNumber("01041219495");
        memberVO.setMemberPassword("1234");

        memberMapper.insertMember(memberVO);
    }

//    이메일 중복검사
    @Test
    public void checkEmail() {
        log.info(memberMapper.selectByEmail("jjy1234@naver.com").toString());
    }

//    휴대폰번호 중복검사
    @Test
    public void checkPhoneNumber() {
        log.info(memberMapper.selectByPhoneNumber("01012341234").toString());
    }

//    자영업자 로그인
    @Test
    public void loginTest() {
        log.info("memberId : " + memberMapper.selectByMemberIdAndMemberPassword("jjy1234@naver.com", "d2pkd2xkdWQxIQ=="));
    }

//    유통업체 회원가입
    @Test
    public void insertBusinessTest() {
        BusinessVO businessVO = new BusinessVO();

        businessVO.setBusinessEmail("jjy1234@naver.com");
        businessVO.setBusinessCeoName("정지영");
        businessVO.setBusinessCompanyName("주식");
        businessVO.setBusinessEstablishmentDate("2019/05/25");
        businessVO.setBusinessNumber("110-11-11111");
        businessVO.setBusinessPhoneNumber("01012341234");
        businessVO.setBusinessPassword("wjdwldud1!");
        memberMapper.insertBusiness(businessVO);
    }
}
