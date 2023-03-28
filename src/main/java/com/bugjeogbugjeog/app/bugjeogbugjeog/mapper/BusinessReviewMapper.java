package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BusinessReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BusinessReviewMapper {
    //    추가
//    public void insert(BusinessReviewVO boardBusinessVO);

    //    삭제
//    public void delete(Long orderId);

    //    조회(이미지까지)
    public List<BusinessReviewDTO> select(Long boardBusinessId);

    //    로그인 정보 조회
    public BusinessReviewDTO selectByMemberId(Long memberId);

    //    목록
//    public List<BoardBusinessDTO> selectAll();

    //    썸네일 포함 목록
//    public List<BoardBusinessDTO> selectAllList();

    //    썸네일 포함 목록
//    public List<BoardBusinessDTO> selectAllListBySearch(Map<String, Object> searchMap);

}
