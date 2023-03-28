package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.NoticeVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
public class NoticeServiceTests {

    @Autowired
    NoticeService noticeService;

    /*@Test
    public void showListTest(){List<NoticeVO> noticeVO = noticeService.showList();}*/

    /*@Test
    public void showNoticeTest(){
        NoticeVO noticeVO = new NoticeVO();
        noticeService.showNotice(1L);
    }*/

  /*  @Test
    public void addTest(){
        NoticeVO noticeVO = new NoticeVO();
        noticeVO.setNoticeTitle("공지사항4");
        noticeVO.setNoticeContent("공지사항4");
        noticeService.add(noticeVO);
    }*/

   /* @Test
    public void removeTest(){
    }*/
}
