package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.NoticeVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NoticeDAO {
    private final NoticeMapper noticeMapper;

    /* 공지사항 목록 조회 */
    public List<NoticeVO> findAll(){ return noticeMapper.selectAll();}

    /* 공지사항  상세 보기 */
    public NoticeVO findById(Long noticeId){ return noticeMapper.select(noticeId);}

    /* 공지사항  추가 */
    public void add(NoticeVO noticeVO){ noticeMapper.insert(noticeVO);}

    /* 공지사항  삭제 */
    public void remove(Long noticeId){ noticeMapper.delete(noticeId);}
}
