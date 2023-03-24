package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BoardBusinessDTO {
    private Long boardBusinessId;
    private Long businessId;
    private String boardBusinessTitle;
    private String boardBusinessContent;
    private String boardBusinessRegisterDate;
    private String boardBusinessUpdateDate;
    private Long boardBusinessImgId;
    private String boardBusinessImgOriginalName;
    private String boardBusinessImgUuid;
    private String boardBusinessImgPath;
}
