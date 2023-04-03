package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.InquiryBoardDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.AdminCriteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.InquiryDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardInquiryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InquiryBoardService {
    private final InquiryBoardDAO inquiryBoardDAO;

    /* 관리자 ------------------------------------------------------------------------ */

    // 문의 목록
    public List<BoardInquiryVO> adminFindAll(AdminCriteria adminCriteria){
        return inquiryBoardDAO.getInquiryList(adminCriteria);}

    // 문의 조회
    public InquiryDTO adminFindByInquiry(Long boardInquiryId){
        return inquiryBoardDAO.getInquiry(boardInquiryId);}

    // 문의 삭제
    public void removeInquiry(List<String> boardInquiryIds){
        boardInquiryIds.stream().map(boardInquiryId -> Long.parseLong(boardInquiryId)).forEach(inquiryBoardDAO::deleteInquiry);
    }

    // 문의 카운트
    public int count(){return inquiryBoardDAO.count();}
}
