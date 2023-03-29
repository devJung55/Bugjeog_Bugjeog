package com.bugjeogbugjeog.app.bugjeogbugjeog.dao;


import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.AdminDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.MemberDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MemberDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessVO;
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
    MemberDAO memberDAO;

    /* 회원 목록 */

 /*   @Test
    public void findAllMemberTest(){List<MemberDTO> memberDTO = adminDAO.findAllMember();}
    */
/*

    */
/* 회원 조회 */

    @Test
    public void findByIdMemberTest(){
        MemberDTO memberDTO = new MemberDTO();
        memberDAO.adminFindById(1L).toString();
    }


/* 회원 수정 *//*

    @Test
    public void updateMember(){
        MemberDTO memberDTO = memberDAO.adminFindById(1L);
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberPhoneNumber("01089151820");
        memberDAO.updateById(memberVO);
    }
*/



    /* ------------------------------------------------------------------------------------------------------------- */

    /* 유통 회원 *//*


    @Test
    public void findAllBusinessTest(){
        List<BusinessVO> businessVO = adminDAO.findAllBusiness();
    }

    @Test
    public void findByIdBusinessTest(){
        BusinessVO businessVO = new BusinessVO();
        adminDAO.findByIdBusiness(2L);
    }

    @Test
    public void updateBusinessTest(){
        BusinessVO businessVO = adminDAO.findByIdBusiness(2L);
        businessVO.setBusinessCompanyName("최강유통");
        adminDAO.updateBusiness(businessVO);
    }

    @Test
    public void removeBusinessTest(){ adminDAO.removeBusiness(3L);}
*/
    /* ------------------------------------------------------------------------------------------------------------- */
}
