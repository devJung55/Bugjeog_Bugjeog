package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MypageDTO {
    private Long memberId;
    private String memberEmail;
    private String memberName;
    private String memberImgOriginal_name;
    private String memberImgUuid;
    private String memberImgPath;
}
