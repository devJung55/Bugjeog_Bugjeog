package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BoardInquiryAnswerVO {
    private Long board_inquiry_answer_id;
    private Long board_inquiry_id;
    private String board_inquiry_answer_content;
    private String board_inquiry_answer_register_date;
    private String board_inquiry_img_original_name;
    private String board_inquiry_img_uuid;
    private String board_inquiry_img_path;
}
