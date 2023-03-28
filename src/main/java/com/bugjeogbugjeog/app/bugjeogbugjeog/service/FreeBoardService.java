package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.FreeBoardDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.FreeBoardImgDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardFreeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

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
    public void remove(Long boardFreeId){ freeBoardDAO.delete(boardFreeId);}

    //    조회(이미지들까지)
    public BoardFreeVO getBoard(Long boardFreeId) { return freeBoardDAO.findById(boardFreeId); }

    //    목록(대표 이미지 하나)
    public List<BoardFreeVO> getList() { return freeBoardDAO.findAll(); }

    //    목록(대표 이미지 하나)
    public List<BoardFreeVO> getList(Map<String, Object> searchMap) { return freeBoardDAO.findAll(searchMap); }
}
