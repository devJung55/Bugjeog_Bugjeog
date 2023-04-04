package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.BusinessBoardDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.BusinessBoardInterestingDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.InterestingCompanyVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Qualifier("businessBoardFavorite")
@Primary
@RequiredArgsConstructor
public class BusinessInterestingService {
    private final BusinessBoardInterestingDAO businessBoardInterestingDAO;

    //    DB에 이미 등록된 내용이 있는지 검사
    public InterestingCompanyVO getOne(Long memberId, Long businessId) {
        return businessBoardInterestingDAO.isThere(memberId, businessId);
    }

    //    DB에 이미 등록된 내용이 있는지 검사
    public boolean isThere(Long businessId, Long memberId) {
        return businessBoardInterestingDAO.isThere(businessId, memberId)==null;
    }

    public void save(Long businessId, Long memberId) {
        businessBoardInterestingDAO.insert(memberId, businessId);
    }
}
