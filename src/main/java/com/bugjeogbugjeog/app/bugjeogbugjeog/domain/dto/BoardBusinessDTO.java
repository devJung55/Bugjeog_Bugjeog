package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BoardBusinessDTO {
    private Long boardBusinessId;/* 게시글ID */
    private Long businessId;/* 사업자ID */
    private String boardBusinessTitle;/* 게시글 제목 */
    private String boardBusinessContent;/* 게시글 내용 */
    private String boardBusinessRegisterDate;/* 게시글 등록일 */
    private String boardBusinessUpdateDate;/* 게시글 수정일 */
    private Long boardBusinessImgId;/* 썸네일 이미지 ID */
    private String boardBusinessImgOriginalName;/* 썸네일 이미지 원본 이름 */
    private String boardBusinessImgUuid;/* 썸네일 이미지 고유번호 */
    private String boardBusinessImgPath;/* 썸네일 이미지 경로 */
    private Long boardBusinessReviewCount;
    private Long boardBusinessGradeAverage;

    /* businessVO */
    private String boardBusinessLocation;
    private String boardBusinessCategory;

}
