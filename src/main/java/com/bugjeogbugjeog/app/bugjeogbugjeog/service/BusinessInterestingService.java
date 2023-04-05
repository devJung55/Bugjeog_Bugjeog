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
    public InterestingCompanyVO getOne(Long businessId, Long memberId) {
        return businessBoardInterestingDAO.isThere(businessId, memberId);
    }

    //    DB에 이미 등록된 내용이 있는지 검사
    public Long countByIds(Long businessId, Long memberId) {
        return businessBoardInterestingDAO.isThere(businessId, memberId) == null ? 0L : 1L;
    }

    public void save(Long businessId, Long memberId) {
        businessBoardInterestingDAO.insert(businessId, memberId);
    }

    public void remove(Long boardId, Long memberId) {
        businessBoardInterestingDAO.deleteByIds(boardId, memberId);
    }
}
