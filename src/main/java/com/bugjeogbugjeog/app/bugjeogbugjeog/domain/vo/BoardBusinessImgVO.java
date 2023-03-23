package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BoardBusinessImgVO {
    private Long board_business_img_id;
    private Long board_business_id;
    private String board_business_img_original_name;
    private String board_business_img_uuid;
    private String board_business_img_path;
}
