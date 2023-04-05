package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.InterestingCompanyVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.BusinessBoardMapper;
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.InterestingCompanyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BusinessBoardInterestingDAO {
    private final InterestingCompanyMapper interestingCompanyMapper;

    public InterestingCompanyVO isThere(Long businessId, Long memberId) {
        return interestingCompanyMapper.selectOneByIds(businessId, memberId);
    }

    public void insert(Long businessId, Long memberId) {
        interestingCompanyMapper.insertByInterestingCompany(businessId, memberId);
    }

    public void deleteByIds(Long boardId, Long memberId) {
        interestingCompanyMapper.deleteByInterestingCompany(boardId, memberId);
    }
}
