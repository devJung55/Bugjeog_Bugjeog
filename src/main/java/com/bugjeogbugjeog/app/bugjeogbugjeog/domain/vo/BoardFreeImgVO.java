package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BoardFreeImgVO {
    private Long boardFreeImgId;
    private Long boardFreeId;
    private String boardFreeImgOriginalName;
    private String boardFreeImgUuid;
    private String boardFreeImgPath;
}
