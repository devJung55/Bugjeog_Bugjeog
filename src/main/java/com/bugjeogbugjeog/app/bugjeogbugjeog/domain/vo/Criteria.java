package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Data
@AllArgsConstructor
public class Criteria {
    private Integer pageNum;
    private Integer amount;
    private String keyword;

    public Criteria() {
        this(1, 8);
    }
    public Criteria(Integer pageNum, Integer amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }

    public String getListLink() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
                .queryParam("pageNum", this.pageNum)
                .queryParam("amount", this.amount)
                .queryParam("keyword", this.keyword);

        return builder.toUriString();
    }
}
