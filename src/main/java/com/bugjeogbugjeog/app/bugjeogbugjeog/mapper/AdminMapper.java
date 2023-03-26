package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    /* 회원 목록 조회 */
    public List<MemberVO> selectAllMember();

    /* 회원 상세 보기 */
    public MemberVO selectMember(Long MemberId);

    /* 회원 삭제 */
    public void deleteMember(Long memberId);

    /* 회원 수정 */
    public void updateMember(MemberVO memberVO);
}
