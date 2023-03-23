package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class InterestingCompanyVO {
    private Long interestingCompanyId;
    private Long memberId;
    private Long businessId;
}
