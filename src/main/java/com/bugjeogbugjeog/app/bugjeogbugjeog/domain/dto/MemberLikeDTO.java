package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MemberLikeDTO {
    private Long memberId;
    private String memberEmail;
    private String memberPhoneNumber;
    private String memberName;
    private String memberImgOriginalName;
    private String memberImgUuid;
    private String memberImgPath;
    private Long boardFreeId;
    private String boardFreeTitle;
    private String boardFreeContent;
    private String boardFreeRegisterDate;
    private String boardFreeUpdateDate;
    private String boardFreeLike;
}