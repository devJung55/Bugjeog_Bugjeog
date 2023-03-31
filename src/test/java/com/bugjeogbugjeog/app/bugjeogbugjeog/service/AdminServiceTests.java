package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MemberDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
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
    MemberService memberService;

     /* 회원 목록 */
/*
    @Test
    public void showMemberList(){ List<MemberDTO> memberDTO = adminService.showMemberList();}
    */
/*

    */
/* 회원 조회 */

    @Test
    public void showMember(){
        MemberDTO memberDTO = new MemberDTO();
        memberService.adminMemberShow(1L);
    }

/* 회원 수정 */

    @Test
    public void updateMember(){
        MemberVO memberVO = memberService.showMember(1L);
        memberVO.setMemberPhoneNumber("01089151829");
        memberService.updateMember(memberVO);
    }



    /* ------------------------------------------------------------------------------------------------------------- */

    /* 유통 회원 */

    /*


    @Test
    public void showListBusiness(){List<BusinessVO> businessVO = adminService.showBusinessList();}

    @Test
    public void showBusiness(){
        BusinessVO businessVO = new BusinessVO();
        adminService.showMember(2L);
    }

    @Test
    public void updateBusiness(){
        BusinessVO businessVO = adminService.showBusiness(2L);
        businessVO.setBusinessCompanyName("용준유통");
        adminService.updateBusiness(businessVO);
    }

    @Test
    public void deleteBusiness(){
        adminService.deleteBusiness(2L);
    }
*/


    /* ------------------------------------------------------------------------------------------------------------- */
}
