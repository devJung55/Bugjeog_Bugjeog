package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BusinessVO {
    private Long business_id;
    private String business_email;
    private String business_password;
    private String business_ceo_name;
    private Long business_number;
    private String business_company_name;
    private String business_phone_number;
    private String business_establishment_date;
    private String business_register_date;
    private Long business_status;
    private String business_img_original_name;
    private String business_img_uuid;
    private String business_img_path;
}
