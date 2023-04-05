package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.BusinessBoardDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.BusinessBoardImgDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.AdminCriteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardBusinessDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BusinessReviewDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Qualifier("businessBoard")
@Primary
@RequiredArgsConstructor
public class BusinessBoardService {
    private final BusinessBoardDAO businessBoardDAO;

    //    추가
    public void registerBoard(BoardBusinessVO boardBusinessVO) {
        businessBoardDAO.save(boardBusinessVO);
    }

    //    삭제
    public void remove(Long businessId) { businessBoardDAO.deleteById(businessId); }

    //    조회(이미지들까지)
    public BoardBusinessDTO getBoard(Long boardBusinessId) {
        return businessBoardDAO.findById(boardBusinessId);
    }

    public List<BoardBusinessDTO> getBoardsByBusinessId(Long businessId) {
        return businessBoardDAO.findByBusinessId(businessId);
    }

    //    목록(대표 이미지 하나)
    public List<BoardBusinessDTO> getList() {
        return businessBoardDAO.findAll();
    }

    //    목록(대표 이미지 하나 + 검색)
    public List<BoardBusinessDTO> getList(Map<String, Object> searchMap) {
        return businessBoardDAO.findAll(searchMap);
    }

    //    관리자페이지 목록
    public List<BoardBusinessDTO> getListByPage(AdminCriteria adminCriteria){
        return businessBoardDAO.findAllByPage(adminCriteria);
    }

    //    pr게시판 목록 개수
    public Long getCount(){
        return businessBoardDAO.findCount();
    }

    //    관리자 페이지 상세보기
    public BoardBusinessDTO getBoardById(Long boardBusinessId){
        return businessBoardDAO.findBoardById(boardBusinessId);
    }

    //    보드아이디에 맞춰 이미지 정보 가져오기
    public List<BoardBusinessDTO> getImagesById(Long boardBusinessId){
        return businessBoardDAO.findImages(boardBusinessId);
    }

}
