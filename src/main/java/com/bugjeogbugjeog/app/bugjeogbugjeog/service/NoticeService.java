package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.NoticeDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.AdminCriteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.NoticeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeDAO noticeDAO;

    /* 공지사항 목록 조회 */
    public List<NoticeVO> adminShowList(AdminCriteria adminCriteria){ return noticeDAO.adminFindAll(adminCriteria);}

    /* 공지사항 목록 조회 */
    public List<NoticeVO> showList(Criteria criteria){ return noticeDAO.findAll(criteria);}

    /* 공지사항  상세 보기 */
    public NoticeVO showNotice(Long noticeId){ return noticeDAO.findById(noticeId);}

    /* 공지사항  추가 */
    public void add(NoticeVO noticeVO){ noticeDAO.add(noticeVO);}

    /* 공지사항  삭제 */
    public void remove(Long noticeId) {
//        noticeIds.stream().map(noticeId -> Long.parseLong(noticeId)).forEach(noticeDAO::remove);
//        noticeIds.forEach(noticeDAO::remove);
        noticeDAO.remove(noticeId);
    }

    /* 카운트 */
    public Long count(){return noticeDAO.count();}
}
