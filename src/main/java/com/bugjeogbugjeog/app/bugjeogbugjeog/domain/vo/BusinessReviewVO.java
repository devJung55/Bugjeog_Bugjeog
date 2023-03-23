package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BusinessReviewVO {
    private Long reviewId;
    private Long memberId;
    private Long boardBusinessId;
    private String reviewContent;
    private String reviewRegisterDate;
    private String reviewUpdateDate;
    private Long reviewGrade;
}
