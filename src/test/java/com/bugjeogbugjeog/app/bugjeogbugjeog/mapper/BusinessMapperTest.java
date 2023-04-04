package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class BusinessMapperTest {

    @Autowired
    BusinessMapper businessMapper;

    @Test
    void selectBusiness() {
    }

    @Test
    void updateLocation() {
    }

    @Test
    void selectListToBusinessNumber() {
    }

    @Test
    void withdraw() {
    }

    @Test
    void reviewGrade() {
    }

    @Test
    void reviewCount() {
    }

    @Test
    void adminSelectAllBusiness() {
    }

    @Test
    void count() {
    }

    @Test
    void adminSelectBusiness() {
        businessMapper.adminSelectBusiness(1L);
    }

    @Test
    void update() {
    }

    @Test
    void selectReivewRank() {
        log.info("===================" + businessMapper.selectReivewRank());
    }
}