package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface MemberMapper {
//    자영업자 회원가입
    public void insertMember(MemberVO memberVO);

//    이메일 중복검사
    public Long selectByEmail(String memberEmail);

//    휴대폰번호 중복검사
    public Long selectByPhoneNumber(String memberPhoneNumber);

//    자영업자 로그인
    public Long selectByMemberIdAndMemberPassword(String memberEmail, String memberPassword);

//    유통업체 회원가입
    public void insertBusiness(BusinessVO businessVO);

//    유통업체 이메일 중복검사
    public Long businessSelectByEmail(String businessEmail);

//    유통업체 휴대폰 중복검사
    public Long businessSelectByPhoneNumber(String businessPhoneNumber);
}
