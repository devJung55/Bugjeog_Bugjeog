package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.BusinessDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.AdminCriteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BusinessDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MemberDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessService {
    private final BusinessDAO businessDAO;

    /* ------------------------------------------------------------ */
    /* 관리자 유통 목록 */
    public List<BusinessDTO> adminShowListBusiness(AdminCriteria adminCriteria){ return businessDAO.adminFindAll(adminCriteria);}

    /* 관리자 카운트 */
    public Long count(){return businessDAO.count();}

    /* 관리자 유통 상세 보기 */
    public BusinessDTO adminShowBusiness(Long businessId){return businessDAO.adminFindById(businessId);}

    /* 관리자 유통 회원 수정 */
    public void setBusiness(BusinessVO businessVO){
        BusinessVO business = businessDAO.findByIdToBusiness(businessVO.getBusinessId());
        business.setBusinessCompanyName(businessVO.getBusinessCompanyName());
        business.setBusinessNumber(businessVO.getBusinessNumber());
        business.setBusinessPhoneNumber(businessVO.getBusinessPhoneNumber());
        business.setBusinessStatus(businessVO.getBusinessStatus());
        businessDAO.updateById(business);}

    /* 관리자 유통 회원 삭제 */
    public void removeBusiness(Long businessId) {
//        noticeIds.stream().map(noticeId -> Long.parseLong(noticeId)).forEach(noticeDAO::remove);
//        businessIds.stream().map(businessId -> Long.parseLong(businessId)).forEach(businessDAO::removeById);
        businessDAO.removeById(businessId);
    }

    /* 유통 회원 조회 */
    public BusinessVO showBusiness(Long businessId) {
      return businessDAO.findByIdToBusiness(businessId);
    }

    /* 추천 유통업체 TOP 10 */
    public List<BusinessDTO> getListByReviewRank(){
        return businessDAO.findListByReviewRank();
    }
}

