package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminDAO {

    private final AdminMapper adminMapper;

    /* 회원 목록 조회 */
    public List<MemberVO> findAll(){ return adminMapper.selectAllMember(); }

    /* 회원 조회 */
    public MemberVO findById(Long memberId){ return adminMapper.selectMember(memberId); }

    /* 회원 삭제 */
    public void deleteMember(Long memberId){ adminMapper.deleteMember(memberId);}

    /* 회원 수정 */
}
