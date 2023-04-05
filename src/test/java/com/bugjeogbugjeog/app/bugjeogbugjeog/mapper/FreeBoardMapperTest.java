package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;


import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.AdminCriteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.SearchDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.enums.SearchEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class FreeBoardMapperTest {

    @Autowired
    private FreeBoardMapper freeBoardMapper;

    @Test
    void selectTest() {
        log.info("====" + freeBoardMapper.select(2L).toString());
    }

    @Test
    void selectWithNames() {
        AdminCriteria adminCriteria = new AdminCriteria();
        int total = freeBoardMapper.getTotal();
        adminCriteria.create(1, 10, total, 5);
        log.info("=========" + new SearchDTO().setOrderColumn(SearchEnum.BOARD_FREE_REGISTER_DATE).getOrderColumn());
        adminCriteria.setSearchDTO(new SearchDTO().setOrderColumn(SearchEnum.BOARD_FREE_REGISTER_DATE));
        log.info(freeBoardMapper.selectWithName(adminCriteria).toString());
    }
}
