package com.bugjeogbugjeog.app.bugjeogbugjeog.dao;


import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.AdminDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Member;
import java.util.List;

@SpringBootTest
@Slf4j
public class AdminDAOTest {

    @Autowired
    AdminDAO adminDAO;

    @Test
    public void findAllMemberTest(){
        List<MemberVO> memberVO = adminDAO.findAllMember();
    }

    @Test
    public void findByIdMemberTest(){
        MemberVO memberVO = new MemberVO();
        adminDAO.findByIdMember(1L).toString();
    }

    @Test
    public void updateMember(){
        MemberVO memberVO = adminDAO.findByIdMember(1L);
        memberVO.setMemberEmail("monday123@gamil.com");
        adminDAO.updateMember(memberVO);
    }

    @Test
    public void deleteMember(){
        adminDAO.removeMember(6L);
    }


    /* ------------------------------------------------------------------------------------------------------------- */

    /* 유통 회원 */

/*
    @Test
    public void findAllBusiness(){}

    @Test
    public void findByIdBusiness(){}

    @Test
    public void updateBusiness(){}

    @Test
    public void deleteBusiness(){}
*/
    /* ------------------------------------------------------------------------------------------------------------- */
}
