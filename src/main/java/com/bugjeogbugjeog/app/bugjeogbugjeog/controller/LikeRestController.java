package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.FreeLikeVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.FreeLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes/*")
public class LikeRestController {

    private final FreeLikeService likeService;

    // 좋아요 카운트 1 증가
    @PostMapping("likeUp")
    public void likeInsert(@RequestBody FreeLikeVO freeLikeVO) {
        likeService.likeInsert(freeLikeVO);
    }

    // 좋아요 카운트 1 감소
    @DeleteMapping("likeDown")
    public void likeDown(@RequestBody FreeLikeVO freeLikeVO){
        likeService.likeDown(freeLikeVO);
    }

    // tbl_board_free의 boardFreeLike 업데이트
    @PatchMapping("likeCountUpdate")
    public void businessLikeCountUpdate(@RequestParam("boardFreeId") Long boardFreeId){
        likeService.countUp(boardFreeId);
    }

    // 좋아요를 누른 상태인지 확인
    @PostMapping("like-check")
    public Boolean likeCheck(@RequestBody FreeLikeVO freeLikeVO){
        return likeService.likeCheck(freeLikeVO);
    }

    // 각 게시판 별 좋아요 갯수
    @GetMapping("likeCount")
    public Integer getLikeCount(@RequestParam("boardFreeId") Long boardFreeId){
        return likeService.getlikeCount(boardFreeId);
    }

}
