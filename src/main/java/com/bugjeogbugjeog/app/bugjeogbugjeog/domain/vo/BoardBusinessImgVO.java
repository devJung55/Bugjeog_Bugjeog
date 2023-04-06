package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BoardBusinessImgVO {
    private Long boardBusinessImgId;
    private Long boardBusinessId;
    private String boardBusinessImgOriginalName;
    private String boardBusinessImgUuid;
    private String boardBusinessImgPath;
    private boolean main;
}
