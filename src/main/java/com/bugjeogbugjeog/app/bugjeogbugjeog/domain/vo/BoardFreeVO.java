package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BoardFreeVO {
    private Long boardFreeId;
    private Long memberId;
    private Long businessId;
    private String boardFreeTitle;
    private String boardFreeContent;
    private String boardFreeRegisterDate;
    private String boardFreeUpdateDate;
    private Long boardFreeLike;
}
