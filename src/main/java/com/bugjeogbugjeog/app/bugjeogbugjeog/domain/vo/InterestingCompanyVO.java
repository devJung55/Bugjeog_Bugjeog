package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class InterestingCompanyVO {
    private Long interesting_company_id;
    private Long member_id;
    private Long business_id;
}
