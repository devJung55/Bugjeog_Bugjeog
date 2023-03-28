package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;


import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MemberDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
@Slf4j
public class AdminMapperTest {
/*
    @Autowired
    AdminMapper adminMapper;
*/

    /* 회원 */
  /*
  @Test
    public void selectAllMemberTest(){List<MemberDTO> memberDTO = adminMapper.selectAllMember();}
*/

   /* @Test
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
*/


    /* ------------------------------------------------------------------------------------------------------------- */

    /* 유통 회원 */

   /*
   @Test
    public void selectAllBusiness(){List<BusinessVO> businessVO = adminMapper.selectAllBusiness();}

    @Test
    public void selectBusiness(){
        BusinessVO businessVO = new BusinessVO();
        adminMapper.selectBusiness(2L).toString();
    }

    @Test
    public void updateBusiness(){
        BusinessVO businessVO = adminMapper.selectBusiness(2L);
        businessVO.setBusinessCompanyName("최강 유통");
        adminMapper.updateBusiness(businessVO);
    }

    @Test
    public void deleteBusiness(){
        adminMapper.deleteBusiness(2L);
    }
*/

    /* ------------------------------------------------------------------------------------------------------------- */


    /* ------------------------------------------------------------------------------------------------------------- */
}
