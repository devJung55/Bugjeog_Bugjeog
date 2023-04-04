package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BusinessDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MemberDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
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
    }

    //유통 분야 설정 수정
    public void updateLocation(BusinessVO businessVO) {businessMapper.updateLocation(businessVO);}

    // 사업자 번호 전부 가져옴
    public List<String> findAllToBusinessNumber(){
        return businessMapper.selectListToBusinessNumber();
    }

    // 회원 삭제
    public void removeById(Long businessId){
        businessMapper.withdraw(businessId);
    }

    // 해당 업체의 평점
    public Double getReviewGrade(Long businessId){
        return businessMapper.reviewGrade(businessId);
    }

    // 해당 업체의 리뷰 개수
    public Integer getReviewCount(Long businessId){
        return businessMapper.reviewCount(businessId);
    }

    /* ------------------------------------------------------------ */
    /* 관리자 유통 목록 */
    public List<BusinessDTO> adminFindAll(Criteria criteria){ return businessMapper.adminSelectAllBusiness(criteria);}

    /* 관리자 카운트 */
    public int count(){return businessMapper.count();}

    /* 관리자 유통 상세 보기 */
    public BusinessDTO adminFindById(Long businessId){return businessMapper.adminSelectBusiness(businessId);}

    /* 관리자 유통 회원 수정 */
    public void updateById(BusinessVO businessVO){businessMapper.update(businessVO);}

    /* 추천 유통업체 TOP 10 */
    public List<BusinessDTO> findListByReviewRank(){
        return businessMapper.selectReivewRank();
    }
}
