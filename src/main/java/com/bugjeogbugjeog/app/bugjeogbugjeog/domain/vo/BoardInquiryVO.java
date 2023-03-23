package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BoardInquiryVO {
    private Long board_inquiry_id;
    private Long member_id;
    private Long business_id;
    private String board_inquiry_title;
    private String board_inquiry_content;
    private String board_inquiry_register_date;
    private String board_inquiry_status;
}
