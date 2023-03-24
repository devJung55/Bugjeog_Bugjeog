package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

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
}
