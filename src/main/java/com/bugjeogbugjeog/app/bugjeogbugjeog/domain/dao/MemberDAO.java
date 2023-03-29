package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MemberDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberDAO {
    private final MemberMapper memberMapper;

//    자영업자 회원가입
    public void register(MemberVO memberVO) {
        memberMapper.insertMember(memberVO);
    }

//    이메일 중복검사
    public Long findByEmail(String memberEmail) {
        return memberMapper.selectByEmail(memberEmail);
    }

//    휴대폰번호 중복검사
    public Long findByPhoneNumber(String memberPhoneNumber) {
        return memberMapper.selectByPhoneNumber(memberPhoneNumber);
    }

//    자영업자 로그인
    public Long findByMemberId(MemberVO memberVO) {
        return memberMapper.selectByMemberIdAndMemberPassword(memberVO);
    }

//    유통업체 회원가입
    public void registerBusiness(BusinessVO businessVO) {
        memberMapper.insertBusiness(businessVO);
    }

//    유통업체 이메일 중복검사
    public Long businessFindByEmail(String businessEmail) {
        return memberMapper.businessSelectByEmail(businessEmail);
    }

//    유통업체 휴대폰 중복검사
    public Long businessFindByPhoneNumber(String businessPhoneNumber) {
        return memberMapper.businessSelectByPhoneNumber(businessPhoneNumber);
    }

//    유통업체 사업자 번호 중복검사
    public Long findByBusinessNumber(String businessNumber) {
        return memberMapper.selectByBusinessNumber(businessNumber);
    }

//    유통업체 로그인
    public Long findByBusinessId(String businessEmail, String businessPassword) {
        return memberMapper.selectByBusinessIdAndBusinessPassword(businessEmail, businessPassword);
    }

//    자영업자 계정 찾기
    public String findByPhoneNumberForEmail(String memberPhoneNumber) {
        return memberMapper.selectByPhoneNumberForEmail(memberPhoneNumber);
    }

//    유통업체 계정 찾기
    public String businessFindByPhoneNumberForEmail(String businessPhoneNumber) {
        return memberMapper.businessSelectByPhoneNumberForEmail(businessPhoneNumber);
    }

//    자영업자 비밀번호 변경
    public void setPassword(String memberEmail, String memberPassword) {
        memberMapper.updatePassword(memberEmail, memberPassword);
    }

//    사업자 비밀번호 변경
    public void businessSetPassword(String businessEmail, String businessPassword) {
        memberMapper.businessUpdatePassword(businessEmail, businessPassword);
    }

    /*-----------------------------------------------------------------------------*/
    //    회원정보 조회
    public MemberVO findById(Long memberId){return memberMapper.select(memberId);};

    // 회원정보 수정
    public void updateById(MemberVO memberVO){
        memberMapper.update(memberVO);
    };

    // 회원탈퇴
    public void deleteById(Long memberId){
        memberMapper.deleteMember(memberId);
    };

    // 핸드폰 전체 조회
    public List<String> findAllToMemberPhoneNumber(){
        return memberMapper.selectAllPhoneNumber();
    }


    /*-----------------------------------------------------------------------------*/

    //  관리자 회원 목록
    public List<MemberDTO> adminFindAll(Criteria criteria){return memberMapper.adminSelectAllMember(criteria);}

    // 관리자 멤버 카운트
    public int count(){return memberMapper.count();}

    /* 관리자 회원 상세 보기 */
    public MemberDTO adminFindById(Long memberId){return memberMapper.adminSelectMember(memberId);}

}
