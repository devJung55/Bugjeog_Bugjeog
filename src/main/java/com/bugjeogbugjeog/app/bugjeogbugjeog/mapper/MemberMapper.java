package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface MemberMapper {
//    자영업자 회원가입
    public void insertMember(MemberVO memberVO);

//    이메일 중복검사
    public Long selectByEmail(String memberEmail);
}
