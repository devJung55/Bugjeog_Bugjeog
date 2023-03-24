package com.bugjeogbugjeog.app.bugjeogbugjeog.service;


import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.MemberDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MemberService {
    @Autowired
    MemberDAO memberDAO;

//    자영업자 회원가입
    @Test
    public void insertTest() {
        MemberVO memberVO = new MemberVO();

        memberVO.setMemberEmail("jjy1234@naver.com");
        memberVO.setMemberName("정지영");
        memberVO.setMemberPhoneNumber("01071228966");
        memberVO.setMemberPassword("1234");

        memberDAO.register(memberVO);
    }
}
