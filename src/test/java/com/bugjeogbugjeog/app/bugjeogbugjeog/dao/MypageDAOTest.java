package com.bugjeogbugjeog.app.bugjeogbugjeog.dao;


import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.MypageDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MypageDAOTest {

    @Autowired
    MypageDAO mypageDAO;

    @Test
    public void findByIdTest(){
        log.info(mypageDAO.findById(1L).toString());
    }
}
