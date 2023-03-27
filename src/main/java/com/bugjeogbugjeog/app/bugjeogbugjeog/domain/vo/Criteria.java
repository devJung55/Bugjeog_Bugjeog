package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Data
public class Criteria {
    private Integer pageNum;
    private Integer amount;

    public Criteria() {
        this(1, 4);
    }

    public Criteria(Integer pageNum, Integer amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }
    public String getListLink() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
                .queryParam("pageNum", this.pageNum)
                .queryParam("amount", this.amount);

        return builder.toUriString();
    }

    public int getStartRow(){
        return (this.pageNum -1) * this.amount;
    }

}
