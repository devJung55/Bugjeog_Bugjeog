package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class AdminCriteria {
    private int page; // 현재 페이지
    private int rowCount; // 한 페이지의 보여질 데이터 개수
    private int pageCount; //    페이지 단위 수
    private int startPage; //    현재 페이지를 기준으로 시작 페이지
    private int endPage; //    현재 페이지를 기준으로 마지막 페이지
    private int realEnd; //    가장 마지막 페이지
    private boolean prev, next; // 이전 버튼 다음버튼 유무
    private int total; // 데이터의 총 개수
    private int offset; // mysql은 연산 불가능 하므로 연산해서 넘기기 위해 만든 필드

    private SearchDTO searchDTO;

    public void create(int page, int rowCount, int total, int pageCount) {
        this.page = page;
        this.rowCount = rowCount;
        this.total = total;
        this.pageCount = pageCount;
        this.offset = (page - 1) * rowCount;
//        현재 페이지를 기준으로 페이지 단위에 맞춰서 마지막 페이지 계산
        this.endPage = (int)(Math.ceil(page / (double)pageCount)) * pageCount;
        this.startPage = endPage - pageCount + 1;
//        게시글 전체 개수를 통해 가장 마지막 페이지 계산
        this.realEnd = (int)(Math.ceil(total / (double)rowCount));
        if(realEnd < endPage) {
//            게시글이 한 개도 없다면, realEnd는 0이 되고, endPage도 0이 된다.
//            따라서 realEnd가 0이라면 endPage를 1로 변경해주어야 한다.
            endPage = realEnd == 0 ? 1 : realEnd;
        }
        this.prev = startPage > 1;
        this.next = endPage < realEnd;
    }
}