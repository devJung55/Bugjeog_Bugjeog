package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MyPageReplyDTO {
    Long boardFreeId;
    String boardFreeTitle;
    String boardFreeContent;
    String boardFreeRegisterDate;
    String boardFreeUpdateDate;
    Long boardFreeLike;
    String replyContent;
    String replyRegisterDate;
    String replyUpdateDate;
}
