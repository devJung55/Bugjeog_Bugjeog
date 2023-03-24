package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyPageMapper {

//    회원정보 조회
    public MemberVO select(Long memberId);

//    회원정보 수정
    public void update(MemberVO memberVO);

//    유통업체 회원정보 조회
    public BusinessVO selectBuisness(Long business_id);

//    좋아요 한 게시물 수
    public Integer likeCount(Long memberId);

//    좋아요 한 게시물 번호들
    public List<Long> likeListNumber(Long memberId);

//    좋아요 한 게시물리스트

}
