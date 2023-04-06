package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class BoardBusinessWriteDTO {
    private Long boardBusinessId;
    private Long businessId;
    private String boardBusinessTitle;
    private String boardBusinessContent;
    private List<BoardBusinessImgVO> files;

    public BoardBusinessVO toVO(){
        BoardBusinessVO boardBusinessVO = new BoardBusinessVO();
        boardBusinessVO.setBoardBusinessId(boardBusinessId);
        boardBusinessVO.setBusinessId(businessId);
        boardBusinessVO.setBoardBusinessTitle(boardBusinessTitle);
        boardBusinessVO.setBoardBusinessContent(boardBusinessContent);
        return boardBusinessVO;
    }
}
