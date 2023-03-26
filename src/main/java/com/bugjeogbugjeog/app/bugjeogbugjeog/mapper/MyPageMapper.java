package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MemberInquireDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MemberLikeDTO;
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

//    회원정보 삭제
    public void deleteMember(Long memberId);

//    휴대폰 모든 정보 조회
    public List<String> selectAllPhoneNumber();

//    유통업체 회원정보 조회
    public BusinessVO selectBuisness(Long businessId);

//    좋아요 한 게시물 수
    public Integer likeCount(Long memberId);

//    좋아요 한 게시물 번호들
    public List<Long> likeListNumber(Long memberId);

//    좋아요 한 게시물 리스트
    public MemberLikeDTO likeList(Long boardFreeId);

//    문의 작성 목록
    public List<MemberInquireDTO> inquireList(Long memberId);

//    문의 게시글 작성 갯수
    public Integer inquireCount(Long memberId);

//    문의 게시글 답변 유무 체크
    public Long answerCheck(Long boardInquiryId);

}
