package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;


import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
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
        
        log.info(myPageMapper.select(1L).toString());
    }

    @Test
    public void updateTest(){
        MemberVO memberVO = myPageMapper.select(1L);

        memberVO.setMemberEmail("ljw@naver.com");
        memberVO.setMemberImgOriginalName("1234");
        memberVO.setMemberImgPath("1234");
        memberVO.setMemberImgUuid("1234");

        myPageMapper.update(memberVO);
    }

    @Test
    public void selectBuisnessTest(){
        myPageMapper.selectBuisness(1L);
    }

}
