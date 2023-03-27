package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class NoticeVO {
    private Long noticeId;
    private String noticeTitle;
    private String noticeContent;
    private String noticeRegisterDate;
}
