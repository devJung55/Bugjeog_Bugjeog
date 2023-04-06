package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.InquiryBoardDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.AdminCriteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardInquiryDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.InquiryDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardInquiryVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.InquiryCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("inquiryBoard")
@Primary
@RequiredArgsConstructor
public class InquiryBoardService {
    private final InquiryBoardDAO inquiryBoardDAO;

    //    추가
    public void registerBoard(BoardInquiryVO boardInquiryVO, String memberType) {
        inquiryBoardDAO.save(boardInquiryVO, memberType);
    }

    //    추가
    public void registerBoard(BoardInquiryVO boardInquiryVO) {
        inquiryBoardDAO.save(boardInquiryVO);
    }

    //    삭제
//    public void remove(Long businessId) {
//        inquiryBoardDAO.deleteById(businessId);
//    }

    //    리스트 조회
    public List<BoardInquiryDTO> showList(){
        return inquiryBoardDAO.findAllByIdToInquire();
    }


    public BoardInquiryDTO getBoard(Long boardInquiryId){
        return inquiryBoardDAO.findOneByBoardInquiryId(boardInquiryId);
    }
    //    조회(이미지들까지)
//    public BoardBusinessDTO getBoard(Long boardBusinessId) {
//        return inquiryBoardDAO.findById(boardBusinessId);
//    }

//    public List<BoardBusinessDTO> getBoardsByBusinessId(Long businessId) {
//        return inquiryBoardDAO.findByBusinessId(businessId);
//    }


    /* 관리자 ------------------------------------------------------------------------ */

    // 문의 목록
    public List<BoardInquiryDTO> adminFindAll(AdminCriteria adminCriteria){
        return inquiryBoardDAO.getInquiryList(adminCriteria);}

    // 문의 조회
    public InquiryDTO adminFindByInquiry(Long boardInquiryId){
        return inquiryBoardDAO.getInquiry(boardInquiryId);}

    // 문의 삭제
    public void removeInquiry(Long boardInquiryId){
//        boardInquiryIds.stream().map(boardInquiryId -> Long.parseLong(boardInquiryId)).forEach(inquiryBoardDAO::deleteInquiry);
        inquiryBoardDAO.deleteInquiry(boardInquiryId);
    }

    // 문의 카운트
    public Long count(){return inquiryBoardDAO.count();}

    public List<BoardInquiryVO> getAllList(InquiryCriteria criteria){
        return inquiryBoardDAO.findAllList(criteria);
    }

}
