package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessVO;
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
    public Long findByMemberEmailAndMemberPassword(String memberEmail, String memberPassword) {
        return memberMapper.selectByMemberIdAndMemberPassword(memberEmail, memberPassword);
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

    /*-----------------------------------------------------------------------------*/
    //    회원정보 조회
    public MemberVO findById(Long memberId){
        return memberMapper.select(memberId);
    };

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
}
