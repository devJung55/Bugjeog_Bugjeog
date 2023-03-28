package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.NoticeVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class NoticeMapperTest {

    @Autowired
    NoticeMapper noticeMapper;

    /*@Test
    public void selectAllTest(){ List<NoticeVO> noticeVO = noticeMapper.selectAll();}

    @Test
    public void selectTest(){
        NoticeVO noticeVO = new NoticeVO();
        noticeMapper.select(1L).toString();
    }

    @Test
    public void insertTest(){
        NoticeVO noticeVO = new NoticeVO();
        noticeVO.setNoticeTitle("공지사항2");
        noticeVO.setNoticeContent("공지사항2");
        noticeMapper.insert(noticeVO);
    }

    @Test
    public void deleteTest(){
        noticeMapper.delete(2L);
    }*/
}
