package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MemberInquireDTO {
    private Long memberId;
    private String memberEmail;
    private String memberPhoneNumber;
    private String memberName;
    private String memberImgOriginalName;
    private String memberImgUuid;
    private String memberImgPath;
    private Long boardInquiryId;
    private String boardInquiryTitle;
    private String boardInquiryContent;
    private String boardInquiryRegisterDate;
    private Boolean boardInquiryStatus;
}
