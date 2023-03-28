package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BusinessReviewDTO {
    private Long reviewId;
    private Long memberId;
    private Long boardBusinessId;
    private String reviewContent;
    private String reviewRegisterDate;
    private String reviewUpdateDate;
    private Long reviewGrade;

    private String memberEmail;
    private String memberPassword;
    private String memberPhoneNumber;
    private String memberName;
    private String memberRegisterDate;
    private Long memberStatus;
    private String memberImgOriginalName;
    private String memberImgUuid;
    private String memberImgPath;

    private String memberImgFullPath;
}
