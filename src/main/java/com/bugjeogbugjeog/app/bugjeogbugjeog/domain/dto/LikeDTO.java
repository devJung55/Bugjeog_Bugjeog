package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class LikeDTO {
    Long memberId;
    String memberEmail;
    String memberPhoneNumber;
    String memberName;
    String memberImgOriginalName;
    String memberImgUuid;
    String memberImgPath;
    Long boardFreeId;
    String boardFreeTitle;
    String boardFreeContent;
    String boardFreeRegisterDate;
    String boardFreeUpdateDate;
    String boardFreeLike;
}