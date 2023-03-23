package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class FreeReplyVO {
    private Long reply_id;
    private Long board_free_id;
    private Long member_id;
    private Long business_id;
    private String reply_content;
    private String reply_register_date;
    private String reply_update_date;
}
