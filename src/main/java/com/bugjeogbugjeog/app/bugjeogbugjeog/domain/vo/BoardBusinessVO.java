package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BoardBusinessVO {
    private Long boardBusinessId;
    private Long businessId;
    private String boardBusinessTitle;
    private String boardBusinessContent;
    private String boardBusinessRegisterDate;
    private String boardBusinessUpdateDate;
    private Long boardBusinessStatus;
}
