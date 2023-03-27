package com.bugjeogbugjeog.app.bugjeogbugjeog.dao;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.NoticeDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.NoticeVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class NoticeDAOTest {

    @Autowired
    NoticeDAO noticeDAO;

    @Test
    public void findByAllTest(){List<NoticeVO> noticeVO = noticeDAO.findAll();}

    @Test
    public void findByIdTest(){
        NoticeVO noticeVO = new NoticeVO();
        noticeDAO.findById(1L);
    }

    @Test
    public void addTest(){
        NoticeVO noticeVO = new NoticeVO();
        noticeVO.setNoticeTitle("공지사항3");
        noticeVO.setNoticeContent("공지사항3");
        noticeDAO.add(noticeVO);
    }

    @Test
    public void removeTest(){
        noticeDAO.remove(3L);
    }
}
