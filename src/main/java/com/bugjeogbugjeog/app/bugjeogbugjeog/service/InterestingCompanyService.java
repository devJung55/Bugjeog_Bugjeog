package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.InterestingCompanyDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.InterestingCompanyDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.InterestingCriteria;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterestingCompanyService {

    private final InterestingCompanyDAO interestingCompanyDAO;

    //         관심업체 리스트
    public List<InterestingCompanyDTO> findAllToInterestingCompany(Long memberId, InterestingCriteria interestingCriteria){
        return interestingCompanyDAO.findAllToInterestingCompany(memberId, interestingCriteria);}

    //    갯수 가져오기
    public int count(Long memberId){
        return interestingCompanyDAO.count(memberId);
    }

    //   관심글 삭제
    public void deleteInterestingCompany(Long businessId, Long memberId) {
        interestingCompanyDAO.deleteByInterestingCompany(businessId, memberId);
    }
    //    관심글 재 클릭시 추가
    public void insertInterestingCompany(Long businessId, Long memberId) {
        interestingCompanyDAO.insertByInterestingCompany(businessId, memberId);
    }

}
