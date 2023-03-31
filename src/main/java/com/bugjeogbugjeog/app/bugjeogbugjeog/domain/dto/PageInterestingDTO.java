package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.InterestingCriteria;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class PageInterestingDTO {
    private Integer startPage;
    private Integer endPage;
    private Integer realEnd;
    private boolean next, prev;
    private Integer total;
    private InterestingCriteria interestingCriteria;

    public PageInterestingDTO(InterestingCriteria interestingCriteria, Integer total){
        this.interestingCriteria = interestingCriteria;
        this.total = total;

        this.endPage = (int)(Math.ceil(interestingCriteria.getPageNum() / (double)interestingCriteria.getAmount())) * interestingCriteria.getAmount();
        this.startPage = this.endPage - 3;
        this.realEnd = (int)Math.ceil(total / (double)interestingCriteria.getAmount());

        if(realEnd < this.endPage){
            this.endPage = realEnd;
        }

        this.prev = this.startPage > 1;
        this.next = this.endPage < realEnd;
    }
}
