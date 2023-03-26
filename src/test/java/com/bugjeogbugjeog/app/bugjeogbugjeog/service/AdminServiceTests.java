package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class AdminServiceTests {

    @Autowired
    AdminService adminService;

    @Test
    public void showMemberList(){ List<MemberVO> memberVO = adminService.showMemberList();}

    @Test
    public void showMember(){
        MemberVO memberVO = new MemberVO();
        adminService.showMember(1L).toString();
    }

    @Test
    public void updateMember(){
        MemberVO memberVO = adminService.showMember(1L);
        memberVO.setMemberEmail("lsc00@gmail.com");
        adminService.updateMember(memberVO);
    }

    @Test
    public void deleteMember(){
        adminService.removeMember(0L);
    }
}
