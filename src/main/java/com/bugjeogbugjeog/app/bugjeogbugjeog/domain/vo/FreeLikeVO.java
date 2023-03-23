package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class FreeLikeVO {
    private Long freeLikeId;
    private Long memberId;
    private Long businessId;
    private Long boardFreeId;
}
