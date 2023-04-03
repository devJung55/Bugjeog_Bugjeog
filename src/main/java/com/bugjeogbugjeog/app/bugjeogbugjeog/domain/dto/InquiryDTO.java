package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class InquiryDTO {
    private Long boardInquiryId;
    private Long memberId;
    private Long businessId;
    private String boardInquiryTitle;
    private String boardInquiryContent;
    private String boardInquiryRegisterDate;
    private String boardInquiryStatus;

    private String memberName;
    private String businessCeoName;

    private Long boardInquiryAnswerId;
    private String boardInquiryAnswerContent;
    private String boardInquiryAnswerRegisterDate;
    private String boardInquiryImgOriginalName;
    private String boardInquiryImgUuid;
    private String boardInquiryImgPath;
}
