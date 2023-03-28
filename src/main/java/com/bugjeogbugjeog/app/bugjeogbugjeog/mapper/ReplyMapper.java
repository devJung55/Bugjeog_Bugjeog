package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MyPageReplyDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {

    //  댓글 단 게시물 목록
    public List<MyPageReplyDTO> replyList(@Param("memberId") Long memberId, @Param("criteria") Criteria criteria);

    //    댓글 단 갯수
    public Integer replyCount(Long memberId);
}
