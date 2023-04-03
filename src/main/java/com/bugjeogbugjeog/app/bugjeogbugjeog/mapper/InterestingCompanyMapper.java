package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.InterestingCompanyDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.InterestingCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InterestingCompanyMapper {
    //         관심업체 리스트
    public List<InterestingCompanyDTO> selectInterestingCompany(@Param("memberId") Long memberId, @Param("interestingCriteria") InterestingCriteria interestingCriteria);
    
//    갯수 가져오기
    public int count(Long memberId);

//   관심글 삭제
    public void deleteByInterestingCompany(@Param("businessId") Long businessId, @Param("memberId") Long memberId);

//    관심글 재 클릭시 추가
    public void insertByInterestingCompany(@Param("businessId") Long businessId, @Param("memberId") Long memberId);

    public Long interestingCompanyCount(Long interestingCompanyId);
}
