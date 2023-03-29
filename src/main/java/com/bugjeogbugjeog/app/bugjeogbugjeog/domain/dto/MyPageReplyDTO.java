package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MyPageReplyDTO {
    private Long boardFreeId;
    private String boardFreeTitle;
    private String boardFreeContent;
    private String boardFreeRegisterDate;
    private String boardFreeUpdateDate;
    private Long boardFreeLike;
    private String replyContent;
    private String replyRegisterDate;
    private String replyUpdateDate;
    private Long memberId;
    private Long businessId;
}
