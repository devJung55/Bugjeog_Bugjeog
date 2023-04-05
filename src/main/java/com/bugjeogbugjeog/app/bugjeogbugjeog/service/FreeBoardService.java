package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.FreeBoardDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.FreeBoardImgDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.AdminCriteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardFreeDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardFreeImgVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardFreeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Qualifier("freeBoard")
@Primary
@RequiredArgsConstructor
public class FreeBoardService {
    private final FreeBoardDAO freeBoardDAO;
    private final FreeBoardImgDAO freeBoardImgDAO;

    //    추가
    @Transactional(rollbackFor = Exception.class)
    public void registerBoard(BoardFreeVO boardFreeVO, BoardFreeImgVO boardFreeImgVO) {
        freeBoardDAO.save(boardFreeVO);

        if(boardFreeImgVO.getBoardFreeImgOriginalName() != null && boardFreeImgVO.getBoardFreeImgUuid() != null){
            boardFreeImgVO.setBoardFreeImgPath(getPath());
            boardFreeImgVO.setBoardFreeId(boardFreeVO.getBoardFreeId());
            freeBoardImgDAO.save(boardFreeImgVO);
        }
    }

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

    /* 자유게시판 게시글 목록조회 */
    public List<BoardFreeDTO> getListWithName(AdminCriteria adminCriteria){

        List<BoardFreeDTO> list = freeBoardDAO.findListWithName(adminCriteria);
        list.forEach(board -> board.setBoardFreeImgVOs(freeBoardImgDAO.findListByBoardId(board.getBoardFreeId())));

        return list;
    }

    /* 자유게시판 총 갯수 */
    public Integer getTotal(){
        return freeBoardDAO.findTotal();
    }

    /* 관리자 ************************************************************************** */

    /* 자유 게시판 목록 */
    public List<BoardFreeDTO> adminShowList(AdminCriteria adminCriteria){return freeBoardDAO.adminFindAll(adminCriteria);}

    /* 자유 게시판 조회  */
    public BoardFreeDTO adminShow(Long boardFreeId){return freeBoardDAO.adminFindById(boardFreeId);}

    /* 자유 게시판 삭제 */
    public void adminRemove(Long boardFreeId){
        freeBoardDAO.adminDelete(boardFreeId);
    }

    /* 카운트 */
    public Long count(){return freeBoardDAO.count();}

    //    현재 날짜 경로 구하기
    private String getPath() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
}
