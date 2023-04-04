package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.FreeBoardDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.FreeBoardImgDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.AdminCriteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardFreeDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardFreeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Qualifier("freeBoard")
@Primary
@RequiredArgsConstructor
public class FreeBoardService{
    private final FreeBoardDAO freeBoardDAO;
    private final FreeBoardImgDAO freeBoardImgDAO;

    //    추가
    public void registerBoard(BoardFreeVO boardFreeVO) { freeBoardDAO.save(boardFreeVO); }

    //    삭제
    public void remove(Long boardFreeId){ freeBoardDAO.remove(boardFreeId);}

    //    조회(이미지들까지) 게시물 상세조회(?)
//    public BoardFreeVO getBoard(Long boardFreeId) { return freeBoardDAO.findById(boardFreeId); }
    public BoardFreeDTO getBoard(Long boardFreeId) { return freeBoardDAO.findById(boardFreeId); }

    //    목록(대표 이미지 하나)
    public List<BoardFreeVO> getList() { return freeBoardDAO.findAll(); }

    //    목록(대표 이미지 하나)
//    public List<BoardFreeVO> getList(Map<String, Object> searchMap) { return freeBoardDAO.findById(searchMap); }
    public List<BoardFreeDTO> getListBoard(Long boardFreeId){
        BoardFreeDTO boardFreeDTO = freeBoardDAO.findById(boardFreeId);

        /**
         * 이전글, 현재글, 다음글을 리스트에 담는다.
         * */
        return new ArrayList<>(Arrays
                .asList(
                        freeBoardDAO.findById(boardFreeDTO.getPrevBoardId())
                        , boardFreeDTO
                        , freeBoardDAO.findById(boardFreeDTO.getNextBoardId())
                ));
    }


    /* 관리자 ************************************************************************** */

    public List<BoardFreeVO> adminShowList(AdminCriteria adminCriteria){return freeBoardDAO.adminFindAll(adminCriteria);}

    /* 자유 게시판 목록 */
    /* 자유 게시판 조회  */
    public BoardFreeDTO adminShow(Long boardFreeId){return freeBoardDAO.adminFindById(boardFreeId);}

    /* 자유 게시판 삭제 */
    public void adminRemove(List<String> boardFreeIds){
        boardFreeIds.stream().map(boardFreeId -> Long.parseLong(boardFreeId)).forEach(freeBoardDAO::adminDelete);}


}
