package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.MyPageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MypageDAO {
    private final MyPageMapper myPageMapper;

    //    회원정보 조회
    public MemberVO findById(Long memberId){
        return myPageMapper.select(memberId);
    };
    
    // 회원정보 수정
    public void updateById(MemberVO memberVO){
        myPageMapper.update(memberVO);
    };

}
