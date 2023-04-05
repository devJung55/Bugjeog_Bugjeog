package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.AdminCriteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.NoticeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeMapper {

    /* 공지사항 목록 조회 */
    public List<NoticeVO> selectAll(Criteria criteria);

    /* 공지사항  상세 보기 */
    public NoticeVO select(Long noticeId);

    /* 공지사항  추가 */
    public void insert(NoticeVO noticeVO);

    /* 공지사항  삭제 */
    public void delete(Long noticeId);

    /* 카운트 */
    public Long count();

    /* 공지사항 목록 조회 */
    public List<NoticeVO> adminSelectAll(@Param("adminCriteria") AdminCriteria adminCriteria);

}
