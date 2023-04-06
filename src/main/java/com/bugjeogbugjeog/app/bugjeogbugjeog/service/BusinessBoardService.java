package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.BusinessBoardDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.BusinessBoardImgDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.AdminCriteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardBusinessDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BusinessReviewDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.PageDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Qualifier("businessBoard")
@Primary
@RequiredArgsConstructor
public class BusinessBoardService {
    private final BusinessBoardDAO businessBoardDAO;
    private final BusinessBoardImgDAO businessBoardImgDAO;

    //    게시글 목록
    public List<BoardBusinessDTO> getList(Criteria criteria, String sort, String category){
        return businessBoardDAO.findAll(criteria, sort, category);
    }

    //    게시글 추가
    @Transactional(rollbackFor = Exception.class)
    public void register(BoardBusinessWriteDTO boardBusinessWriteDTO){
        List<BoardBusinessImgVO> files = boardBusinessWriteDTO.getFiles();
        businessBoardDAO.save(boardBusinessWriteDTO.toVO());
        Long sequence = getSequence();
        for(int i=0; i<files.size(); i++){
            if(i == 0){
                files.get(i).setMain(true);
            }
            files.get(i).setBoardBusinessId(sequence);
            businessBoardImgDAO.save(files.get(i));
        }
    }

    //    현재 시퀀스 가져오기
    public Long getSequence(){
        return businessBoardDAO.findLastInsertId();
    }

    //    관리자페이지 목록
    public List<BoardBusinessDTO> getListByPage(AdminCriteria adminCriteria){
        return businessBoardDAO.findAllByPage(adminCriteria);
    }

    //    pr게시판 목록 개수
    public int getCount(){
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
