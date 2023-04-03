package com.bugjeogbugjeog.app.bugjeogbugjeog.service;


import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.InterestingCriteria;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Slf4j
public class InterestingCompanyServiceTests {
    @Autowired
    InterestingCompanyService interestingCompanyService;
    @Autowired
    InterestingCriteria interestingCriteria;


    @Test
    public void Test(){
//        log.info(interestingCompanyService.findAllToInterestingCompany(criteria).toString());
    }
}
