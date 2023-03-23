package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class FreeLikeVO {
    private Long free_like_id;
    private Long member_id;
    private Long business_id;
    private Long board_free_id;
}
