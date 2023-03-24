package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyPageMapper {

//    회원정보 조회
    public MemberVO select(Long memberId);

//    회원정보 수정
    public void update(MemberVO memberVO);

}
