package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface MemberMapper {
//    자영업자 회원가입
    public void insertMember(MemberVO memberVO);

//    이메일 중복검사
    public Long selectByEmail(String memberEmail);

//    휴대폰번호 중복검사
    public Long selectByPhoneNumber(String memberPhoneNumber);

//    자영업자 로그인
    public Long selectByMemberIdAndMemberPassword(MemberVO memberVO);

//    유통업체 회원가입
    public void insertBusiness(BusinessVO businessVO);

//    유통업체 이메일 중복검사
    public Long businessSelectByEmail(String businessEmail);

//    유통업체 휴대폰 중복검사
    public Long businessSelectByPhoneNumber(String businessPhoneNumber);

/*------------------------------------------------------------------------------------------------*/

    //    회원정보 조회
    public MemberVO select(Long memberId);

    //    회원정보 수정
    public void update(MemberVO memberVO);

    //    회원정보 삭제
    public void deleteMember(Long memberId);

    //    휴대폰 모든 정보 조회
    public List<String> selectAllPhoneNumber();
}
