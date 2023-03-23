package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BoardBusinessVO {
    private Long board_business_id;
    private Long business_id;
    private String board_business_title;
    private String board_business_content;
    private String board_business_register_date;
    private String board_business_update_date;
}
