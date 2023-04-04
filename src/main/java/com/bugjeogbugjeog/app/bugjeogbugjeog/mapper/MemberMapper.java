package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.AdminCriteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MemberDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
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

//    유통업체 사업자 번호 중복검사
    public Long selectByBusinessNumber(String businessNumber);

//    유통업체 로그인
    public Long selectByBusinessIdAndBusinessPassword(String businessEmail, String businessPassword);

//    자영업자 계정 찾기
    public String selectByPhoneNumberForEmail(String memberEmail);

//    유통업체 계정 찾기
    public String businessSelectByPhoneNumberForEmail(String businessEmail);

//    자영업자 비밀번호 변경
    public void updatePassword(String memberEmail, String memberPassword);

//    사업자 비밀번호 변경
    public void businessUpdatePassword(String businessEmail, String businessPassword);

//    자영업자 계정 상태 조회
    public Integer selectForStatus(String memberEmail);

//    사업자 계정 상태 조회
    public Integer businessSelectForStatus(String businessEmail);

//    네이버 로그인
    public Long selectNaver(String memberEmail);

//    네이버 로그인
    public MemberVO selectMemberInfo(String memberEmail);

    /*------------------------------------------------------------------------------------------------*/

    //    회원정보 조회
    public MemberVO select(Long memberId);

    //    회원정보 수정
    public void update(MemberVO memberVO);

    //    회원정보 삭제
    public void deleteMember(Long memberId);

    //    휴대폰 모든 정보 조회
    public List<String> selectAllPhoneNumber();

    // 관심업체 갯수
    public Integer interestingBusinessCount(Long memberId);


    /* 관리자는 맨 밑으로 내려주세요------------------------------------------------------------------------------------------------*/

    //  관리자 회원 목록
    public List<MemberDTO> adminSelectAllMember(AdminCriteria adminCriteria);

    // 관리자 멤버 카운트
    public int count();

    /* 관리자 회원 상세 보기 */
    public MemberDTO adminSelectMember(Long memberId);


}
