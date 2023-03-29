package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessVO;
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

    // 회원 탈퇴
    public void withdraw(Long businessId);

    // 해당 업체의 평점
    public Double reviewGrade(Long businessId);

    // 해당 업체 리뷰 개수
    public Integer reviewCount(Long businessId);
}
