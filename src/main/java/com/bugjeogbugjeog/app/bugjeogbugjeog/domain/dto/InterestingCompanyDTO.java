package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class InterestingCompanyDTO {
    private Long interestingCompanyId;
    private Long memberId;
    private Long businessId;
    private String businessLocation;
    private String businessCategory;
    private Long boardBusinessId;
    private String boardBusinessTitle;
    private String boardBusinessImgOriginalName;
    private String boardBusinessImgUuid;
    private String boardBusinessImgPath;
    private String businessCompanyName;
    private String board_businessRegisterDate;
    private String board_businessUpdateDate;
    private Long boardBusinessStatus;
}
