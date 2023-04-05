package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BoardInquiryDTO {
    private Long boardInquiryId;
    private Long memberId;
    private Long businessId;
    private String boardInquiryTitle;
    private String boardInquiryContent;
    private String boardInquiryRegisterDate;
    private String boardInquiryStatus;

    private String writerType;

    private String businessEmail;
    private String businessPassword;
    private String businessCeoName;
    private String businessNumber;
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

    private String memberEmail;
    private String memberPassword;
    private String memberPhoneNumber;
    private String memberName;
    private String memberRegisterDate;
    private Long memberStatus;
    private String memberImgOriginalName;
    private String memberImgUuid;
    private String memberImgPath;

    private Long boardInquiryAnswerId;
}
