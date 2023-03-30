package com.bugjeogbugjeog.app.bugjeogbugjeog.dao;


import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.ReplyDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.FreeReplyVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Slf4j
public class ReplyDAOTest {

    @Autowired
    ReplyDAO replyDAO;

    @Autowired
    FreeReplyVO freeReplyVO;

    /*테스트 완료*/
    @Test
    public void replyTest(){
        freeReplyVO.setBoardFreeId(1L);
        freeReplyVO.setBusinessId(1L);
        freeReplyVO.setMemberId(1L);
        freeReplyVO.setReplyContent("집가고싶다");
        freeReplyVO.setReplyId(1L);
        freeReplyVO.setReplyRegisterDate("2023-03-30");
        freeReplyVO.setReplyUpdateDate("2023-03-30");
        replyDAO.addReply(freeReplyVO);
    }

}

