package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BoardFreeVO {
    private Long board_free_id;
    private Long member_id;
    private Long business_id;
    private String board_free_title;
    private String board_free_content;
    private String board_free_register_date;
    private String board_free_update_date;
    private Long board_free_like;
}
