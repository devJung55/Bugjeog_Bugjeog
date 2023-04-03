package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.InquiryBoardDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardInquiryDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardInquiryVO;
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

    //    조회(이미지들까지)
//    public BoardBusinessDTO getBoard(Long boardBusinessId) {
//        return inquiryBoardDAO.findById(boardBusinessId);
//    }

//    public List<BoardBusinessDTO> getBoardsByBusinessId(Long businessId) {
//        return inquiryBoardDAO.findByBusinessId(businessId);
//    }


}
