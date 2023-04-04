package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao;


import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.AdminCriteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardFreeDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardFreeVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.FreeBoardImgMapper;
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.FreeBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class FreeBoardDAO {
    private final FreeBoardMapper freeBoardMapper;
    private final FreeBoardImgMapper freeBoardImgMapper;

    //    추가
    public void save(BoardFreeVO boardFreeVO){ freeBoardMapper.insert(boardFreeVO); }

    //    삭제
    public void remove(Long boardFreeId){ freeBoardMapper.delete(boardFreeId);}

    //    조회(이미지 정보까지)
//    public BoardFreeVO findById(Long memberId){ return freeBoardMapper.select(memberId);}
//    public BoardFreeDTO findById(Long memberId){ return freeBoardMapper.select(memberId);}
    public BoardFreeDTO findById(Long boardFreeId){
        return freeBoardMapper.select(boardFreeId);
    }

   //    게시글 전체 조회
    public List<BoardFreeVO> findAll(){
        return freeBoardMapper.selectAll();
    }

    //    목록(대표 이미지 하나) 기본 = 게시글 전체 조회
//    public List<BoardFreeVO> findAll(){ return freeBoardMapper.selectAllList(); }

    //    목록(대표 이미지 하나) 특정 카테고리로 검색
    public List<BoardFreeVO> findAll(Map<String, Object> searchMap){ return freeBoardMapper.selectAllListBySearch(searchMap); }

    /*--------------------------------------------------------------------------------------------*/

    // 자유게시판 글 목록
    public List<BoardFreeDTO> findByIdBoardFreeVO(Long memberId, Criteria criteria) {
        return freeBoardMapper.freeList(memberId, criteria);
    }

    // 자유게시판 갯수
    public Integer getFreeBoardTotal(Long memberId){
        return freeBoardMapper.freeCount(memberId);
    }

    // 유통업자 자유게시판 글 목록
    public List<BoardFreeVO> findByIdBusinessBoardFreeVO(Long businessId, Criteria criteria) {
        return freeBoardMapper.businessFreeList(businessId, criteria);
    }

    // 유통업자 자유게시판 갯수
    public Integer getFreeBoardBusinessTotal(Long businessId){
        return freeBoardMapper.businessFreeCount(businessId);
    }










//    ==================================================================================================
//    private final FreeBoardMapper freeBoardMapper;
//
//    //    게시글 추가
//    public void save(BoardFreeVO boardFreeVO){
//        freeBoardMapper.insert(boardFreeVO);
//    }
//
//    //    게시글 조회
//    public BoardFreeVO findById(Long boardFreeId){
//        return freeBoardMapper.select(boardFreeId);
//    }
//
//    //    게시글 수정
//    public void setBoardVO(BoardFreeVO boardFreeVO){
//        freeBoardMapper.update(boardFreeVO);
//    }
//
//    //    게시글 삭제
//    public void delete(Long boardFreeId){
//        freeBoardMapper.delete(boardFreeId);
//    }
//
//    //    게시글 전체 조회
//    public List<BoardFreeVO> findAll(){
//        return freeBoardMapper.selectAll();
//    }

    /* 자유게시판 게시글 목록조회 */
    public List<BoardFreeDTO> findListWithName(AdminCriteria adminCriteria){
        return freeBoardMapper.selectWithName(adminCriteria);
    }

    /* 자유게시판 총 갯수 */
    public Integer findTotal(){
        return freeBoardMapper.getTotal();
    }

    /* 관리자 *********************************************************************************** */

    /* 자유 게시판 목록 */
    public List<BoardFreeVO> adminFindAll(AdminCriteria adminCriteria){return freeBoardMapper.adminSelectAllFree(adminCriteria);}

    /* 자유 게시판 조회  */
    public BoardFreeDTO adminFindById(Long boardFreeId){return freeBoardMapper.adminSelectFree(boardFreeId);}

    /* 관리자 삭제 */
    public void adminDelete(Long boardFreeId){freeBoardMapper.deleteAdmin(boardFreeId);}
}
