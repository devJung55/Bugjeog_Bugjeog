package com.bugjeogbugjeog.app.bugjeogbugjeog.dao;


import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.InterestingCompanyDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class InterestingCompanyDAOTest {
    @Autowired
    InterestingCompanyDAO interestingCompanyDAO;

    @Test
    public void InterestingCompanyDAOTest(){
        log.info(interestingCompanyDAO.findAllToInterestingCompany().toString());
    }
}
