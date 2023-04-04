package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BusinessDTO {
    private Long businessId;
    private String businessEmail;
    private String businessPassword;
    private String businessCeoName;
    private String  businessNumber;
    private String businessCompanyName;
    private String businessPhoneNumber;
    private String businessEstablishmentDate;
    private String businessRegisterDate;
    private Long businessStatus;
    private String businessImgOriginalName;
    private String businessImgUuid;
    private String businessImgPath;
    private String businessLocation;
    private String businessCategory;

    private Integer boardCount;

    private Long boardFreeId;
    private Long memberId;
    private String boardFreeTitle;
    private String boardFreeContent;
    private String boardFreeRegisterDate;
    private String boardFreeUpdateDate;
    private Long boardFreeLike;

    private Integer replyCount;
    private Integer reviewCount;
    private Double reviewGrade;

    private Long replyId;
    private String replyContent;
    private String replyRegisterDate;
    private String replyUpdateDate;
}
