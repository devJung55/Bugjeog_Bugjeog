package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;


import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
@Slf4j
public class AdminMapperTest {

    @Autowired
    AdminMapper adminMapper;

    @Test
    public void selectAllMemberTest(){
        List<MemberVO> memberVO = adminMapper.selectAllMember();
    }

    @Test
    public void selectMemberTest(){
        MemberVO memberVO = new MemberVO();
        adminMapper.selectMember(1L).toString();
    }

    @Test
    public void updateMemberTest(){
        MemberVO memberVO = adminMapper.selectMember(1L);
        memberVO.setMemberEmail("monday12@gmail.com");
        adminMapper.updateMember(memberVO);
    }

    @Test
    public void deleteMemberTest(){
        adminMapper.deleteMember(3L);
    }



}
