package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BoardBusinessImgDTO {
    private Long boardBusinessImgId;
    private Long boardBusinessId;
    private String boardBusinessImgOriginalName;
    private String boardBusinessImgUuid;
    private String boardBusinessImgPath;
}
