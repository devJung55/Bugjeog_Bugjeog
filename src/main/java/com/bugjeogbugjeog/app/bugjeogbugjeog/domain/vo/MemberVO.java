package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MemberVO {
    private Long memberId;
    private String memberEmail;
    private String memberPassword;
    private String memberPhoneNumber;
    private String memberName;
    private String memberRegisterDate;
    private Long memberStatus;
    private String memberImgOriginal_name;
    private String memberImgUuid;
    private String memberImgPath;
}
