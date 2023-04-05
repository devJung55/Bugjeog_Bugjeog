package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardFreeImgVO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class BoardFreeDTO {
    private Long boardFreeId;/* 게시글ID */
    private Long businessId;/* 사업자ID */
    private Long memberId;/* 멤버ID */
    private String boardFreeTitle;/* 게시글 제목 */
    private String boardFreeContent;/* 게시글 내용 */
    private String boardFreeRegisterDate;/* 게시글 등록일 */
    private String boardFreeUpdateDate;/* 게시글 수정일 */
    private Long boardFreeLike;
    private Long boardFreeImgId;/* 썸네일 이미지 ID */
    private String boardFreeImgOriginalName;/* 썸네일 이미지 원본 이름 */
    private String boardFreeImgUuid;/* 썸네일 이미지 고유번호 */
    private String boardFreeImgPath;/* 썸네일 이미지 경로 */
    private String boardBusinessImgFullPath; /* ?? */

    List<BoardFreeImgVO> boardFreeImgVOs;

    private String businessEmail;
    private String businessCeoName;
    private String businessCompanyName;
    private String businessPhoneNumber;
    private Long businessStatus;
    private String businessImgUuid;
    private String businessImgPath;
    private String businessImgOriginalName;

    private String memberEmail;
    private String memberPhoneNumber;
    private String memberName;
    private Long memberStatus;
    private String memberImgOriginalName;
    private String memberImgUuid;
    private String memberImgPath;

    private Long prevBoardId;
    private Long nextBoardId;

    private Integer replyCount;
}
