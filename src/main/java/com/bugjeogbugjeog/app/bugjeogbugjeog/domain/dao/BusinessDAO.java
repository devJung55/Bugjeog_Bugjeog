package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.BusinessMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BusinessDAO {

    private final BusinessMapper businessMapper;

    //    유통업체 회원정보 조회
    public BusinessVO findByIdToBusiness(Long businessId){
        return businessMapper.selectBusiness(businessId);
    };

    //유통 분야 설정 수정
    public void updateLocation(BusinessVO businessVO) {businessMapper.updateLocation(businessVO);};

    // 사업자 번호 전부 가져옴
    public List<String> findAllToBusinessNumber(){
        return businessMapper.selectListToBusinessNumber();
    };
}
