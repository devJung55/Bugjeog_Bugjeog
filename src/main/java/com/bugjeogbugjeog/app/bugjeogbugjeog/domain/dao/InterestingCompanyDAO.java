package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.InterestingCompanyDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.InterestingCriteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.InterestingCompanyMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class InterestingCompanyDAO {

    private final InterestingCompanyMapper interestingCompanyMapper;
    private final SqlSession sqlSession;

    //         관심업체 리스트
    public List<InterestingCompanyDTO> findAllToInterestingCompany(Long memberId, InterestingCriteria interestingCriteria){
        return interestingCompanyMapper.selectInterestingCompany(memberId, interestingCriteria);
    }

    //    갯수 가져오기
    public int count(Long memberId){
        return interestingCompanyMapper.count(memberId);
    }

    //   관심글 삭제
    public void deleteByInterestingCompany(Long businessId, Long memberId) {
        Map<String, Object> params = new HashMap<>();
        params.put("businessId", businessId);
        params.put("memberId", memberId);
        sqlSession.delete("deleteByInterestingCompany", params);
    }

    //    관심글 재 클릭시 추가
    public void insertByInterestingCompany(Long businessId, Long memberId) {
        Map<String, Object> params = new HashMap<>();
        params.put("businessId", businessId);
        params.put("memberId", memberId);
        sqlSession.insert("insertByInterestingCompany", params);
    }

    public Long interestingCompanyCount(Long interestingCompanyId) {
        return interestingCompanyMapper.interestingCompanyCount(interestingCompanyId);


    }
}
