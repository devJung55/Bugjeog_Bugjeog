package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.AdminDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminDAO adminDAO;
/*

    */
/* 회원 목록 조회 *//*

    public List<MemberVO> showMemberList(){ return adminDAO.findAllMember();}

    */
/* 회원 상세 보기 *//*

    public MemberVO showMember(Long memberId) { return adminDAO.findByIdMember(memberId);}

    */
/* 회원 수정 *//*

    public void updateMember(MemberVO memberVO){ adminDAO.updateMember(memberVO);}

    */
/* 회원 삭제 *//*

    public void removeMember(Long memberId){ adminDAO.removeMember(memberId);}


    */
/* ------------------------------------------------------------------------------------------------------------- *//*


    */
/* 유통 회원 목록 조회 *//*

    public List<BusinessVO> showBusinessList(){ return adminDAO.findAllBusiness();}

    */
/* 유통 회원 조회 *//*

    public BusinessVO showBusiness(Long businessId){ return adminDAO.findByIdBusiness(businessId);}

    */
/* 유통 회원 수정 *//*

    public void updateBusiness(BusinessVO businessVO){adminDAO.updateBusiness(businessVO);}

    */
/* 유통 회원 삭제 *//*

    public void deleteBusiness(Long businessId){adminDAO.removeBusiness(businessId);}

*/

    /* ------------------------------------------------------------------------------------------------------------- */



}
