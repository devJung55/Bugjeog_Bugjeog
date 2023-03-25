package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AdminServiceTests {

    @Autowired
    AdminService adminService;

    @Test
    public void showListTest(){
        adminService.showList();
    }
}
