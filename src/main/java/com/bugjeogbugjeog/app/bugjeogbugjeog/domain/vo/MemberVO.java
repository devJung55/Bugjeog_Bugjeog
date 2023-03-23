package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MemberVO {
    private Long member_id;
    private String member_email;
    private String member_password;
    private String member_phone_number;
    private String member_name;
    private String member_register_date;
    private Long member_status;
    private String member_img_original_name;
    private String member_img_uuid;
    private String member_img_path;
}
