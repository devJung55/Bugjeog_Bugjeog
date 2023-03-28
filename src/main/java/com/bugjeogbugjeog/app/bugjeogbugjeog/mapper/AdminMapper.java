package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MemberDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    //  관리자 회원 정보 조회
    public List<MemberDTO> selectAllMember();

    /* 회원 상세 보기 */
    public MemberVO selectMember(Long MemberId);

    /* 회원 수정 */
    public void updateMember(MemberVO memberVO);

    /* 회원 삭제 */
    public void deleteMember(Long memberId);


    /* ------------------------------------------------------------------------------------------------------------- */

    /* 유통 회원 목록 조회 */
    public List<BusinessVO> selectAllBusiness();

    /* 유통 회원 상세 보기 */
    public BusinessVO selectBusiness(Long businessId);

    /* 유통 회원 수정 */
    public void updateBusiness(BusinessVO businessVO);

    /* 유통 회원 삭제  */
    public void deleteBusiness(Long businessId);

    /* ------------------------------------------------------------------------------------------------------------- */

}
