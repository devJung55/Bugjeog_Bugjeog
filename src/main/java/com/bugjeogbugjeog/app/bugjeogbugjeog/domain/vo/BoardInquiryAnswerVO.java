package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BoardInquiryAnswerVO {
    private Long boardInquiryAnswerId;
    private Long boardInquiryId;
    private String boardInquiryAnswerContent;
    private String boardInquiryAnswerRegisterDate;
    private String boardInquiryImgOriginalName;
    private String boardInquiryImgUuid;
    private String boardInquiryImgPath;
}
