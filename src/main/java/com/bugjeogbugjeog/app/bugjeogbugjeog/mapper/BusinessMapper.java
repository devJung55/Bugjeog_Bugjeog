package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BusinessMapper {

    //    유통업체 회원정보 조회
    public BusinessVO selectBusiness(Long businessId);

    //유통 분야 설정 추가
    public void updateLocation(BusinessVO businessVO);
}
