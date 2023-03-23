package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MypageMapperTest {

    @Autowired
    MyPageMapper myPageMapper;

    @Test
    public void selectTest(){
        myPageMapper.select(1L);
    }


}
