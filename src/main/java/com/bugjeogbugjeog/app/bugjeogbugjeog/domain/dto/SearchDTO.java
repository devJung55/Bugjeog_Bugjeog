package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.enums.SearchEnum;
import lombok.Getter;

@Getter
public class SearchDTO {
//    private final String ORDER_BY_DESC = "desc";
    private final String BOARD_FREE_ID = "board_free_id";
    private final String BOARD_FREE_REGISTER_DATE = "board_free_register_date";
    private final String BOARD_FREE_LIKE = "board_free_like";
    private final String BOARD_BUSINESS_REGISTER_DATE = "board_business_register_date";
    private final String BOARD_BUSINESS_GRADE_AVERAGE = "board_business_grade_average";
    private String orderColumn;

    public SearchDTO setOrderColumn(SearchEnum searchEnum){
        switch (searchEnum){
            case BOARD_FREE_ID:
                orderColumn = this.BOARD_FREE_ID;
                break;
            case BOARD_FREE_REGISTER_DATE:
                orderColumn = this.BOARD_FREE_REGISTER_DATE;
                break;
            case BOARD_FREE_LIKE:
                orderColumn = this.BOARD_FREE_LIKE;
                break;
            case BOARD_BUSINESS_REGISTER_DATE:
                orderColumn = this.BOARD_BUSINESS_REGISTER_DATE;
                break;
            case BOARD_BUSINESS_GRADE_AVERAGE:
                orderColumn = this.BOARD_BUSINESS_GRADE_AVERAGE;
                break;
            default:
                orderColumn = null;
        }

        return this;
    }
}
