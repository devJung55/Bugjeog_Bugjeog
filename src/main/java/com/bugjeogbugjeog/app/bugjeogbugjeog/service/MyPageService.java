package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.MypageDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final MypageDAO mypageDAO;

    //    회원정보 조회
    public MemberVO memberInfo(Long memberId){
        return mypageDAO.findById(memberId);
    };

    //    회원정보 수정
    public void memberUpdate(MemberVO memberVO) {
        mypageDAO.updateById(memberVO);
    }
}
