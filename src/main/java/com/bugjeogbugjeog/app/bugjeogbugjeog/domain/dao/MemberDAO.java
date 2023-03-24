package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
}
