package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.FreeLikeDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.FreeLikeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FreeLikeService {

    private final FreeLikeDAO freeLikeDAO;

    // 좋아요 추가
    public void likeInsert(FreeLikeVO freeLikeVO) {
        freeLikeDAO.saveFreeLike(freeLikeVO);
    }

    // 좋아요 제거
    public void likeDown(FreeLikeVO freeLikeVO) {
        freeLikeDAO.removeFreeLike(freeLikeVO);
    }

    // 좋아요 갯수 업데이트
    public void countUp(Long boardFreeId){
        freeLikeDAO.updateCount(boardFreeId);
    }

    // 좋아요 눌럿는지 확인
    public Boolean likeCheck(FreeLikeVO freeLikeVO){
        return freeLikeDAO.findByIdBoardFreeId(freeLikeVO) == 0;
    }

    // 좋아요 갯수 가져오기
    public Integer getlikeCount(Long boardFreeId){
        return freeLikeDAO.getLikeCount(boardFreeId);
    }
}
