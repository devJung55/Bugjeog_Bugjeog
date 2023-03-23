package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BoardInquiryVO {
    private Long boardInquiryId;
    private Long memberId;
    private Long businessId;
    private String boardInquiryTitle;
    private String boardInquiryContent;
    private String boardInquiryRegisterDate;
    private String boardInquiryStatus;
}
