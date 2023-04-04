package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BusinessDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MemberDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BusinessMapper {

    //    유통업체 회원정보 조회
    public BusinessVO selectBusiness(Long businessId);

    //유통 분야 설정 추가
    public void updateLocation(BusinessVO businessVO);

    // 사업자번호 전부 가져옴
    public List<String> selectListToBusinessNumber();

    // 회원 삭제
    public void withdraw(Long businessId);

    // 해당 업체의 평점
    public Double reviewGrade(Long businessId);

    // 해당 업체 리뷰 개수
    public Integer reviewCount(Long businessId);

    /* 관리자 유통 목록 ------------------------------------------------------------ */
    public List<BusinessDTO> adminSelectAllBusiness(Criteria criteria);

    /* 관리자 카운트 */
    public Long count();

    /* 관리자 유통 상세 보기 */
    public BusinessDTO adminSelectBusiness(Long businessId);

    /* 관리자 유통 회원 수정 */
    public void update(BusinessVO businessVO);

    /* 추천 유통업체 TOP 10 */
    public List<BusinessDTO> selectReivewRank();
}
