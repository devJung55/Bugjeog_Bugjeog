package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MemberDTO {
    private Long memberId;
    private String memberEmail;
    private String memberPassword;
    private String memberPhoneNumber;
    private String memberName;
    private String memberRegisterDate;
    private Long memberStatus;
    private String memberImgOriginalName;
    private String memberImgUuid;
    private String memberImgPath;

    private Integer boardCount;

    private Long boardFreeId;
    private Long businessId;
    private String boardFreeTitle;
    private String boardFreeContent;
    private String boardFreeRegisterDate;
    private String boardFreeUpdateDate;
    private Long boardFreeLike;

    private Integer replyCount;

    private Long replyId;
    private String replyContent;
    private String replyRegisterDate;
    private String replyUpdateDate;
}
