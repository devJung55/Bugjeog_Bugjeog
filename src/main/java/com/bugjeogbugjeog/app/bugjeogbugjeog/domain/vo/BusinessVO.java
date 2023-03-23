package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BusinessVO {
    private Long businessId;
    private String businessEmail;
    private String businessPassword;
    private String businessCeoName;
    private Long businessNumber;
    private String businessCompanyName;
    private String businessPhoneNumber;
    private String businessEstablishmentDate;
    private String businessRegisterDate;
    private Long businessStatus;
    private String businessImgOriginalName;
    private String businessImgUuid;
    private String businessImgPath;
}
