package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BoardFreeImgVO {
    private Long board_free_img_id;
    private Long board_free_id;
    private String board_free_img_original_name;
    private String board_free_img_uuid;
    private String board_free_img_path;
}
