package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class FreeReplyVO {
    private Long replyId;
    private Long boardFreeId;
    private Long memberId;
    private Long businessId;
    private String replyContent;
    private String replyRegisterDate;
    private String replyUpdateDate;
}
