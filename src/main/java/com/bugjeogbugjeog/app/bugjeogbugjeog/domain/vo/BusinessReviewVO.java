package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BusinessReviewVO {
    private Long review_id;
    private Long member_id;
    private Long board_business_id;
    private String review_content;
    private String review_register_date;
    private String review_update_date;
    private Long review_grade;
}
