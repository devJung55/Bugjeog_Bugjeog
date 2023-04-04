package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class InquiryCriteria extends Criteria {
    private Integer pageNum;
    private Integer amount;

    public InquiryCriteria() {; }
    public InquiryCriteria(Integer pageNum, Integer amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }

}
