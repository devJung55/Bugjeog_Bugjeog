package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardBusinessDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessImgVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.mapper.BusinessBoardImgMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BusinessBoardImgDAO {
    private final BusinessBoardImgMapper businessBoardImgMapper;

    //    이미지 추가
    public void save(BoardBusinessImgVO boardBusinessImgVO) {
        businessBoardImgMapper.insert(boardBusinessImgVO);
    }

//    boardBusinessId 이미지 전부
    public List<BoardBusinessImgVO> findAllByBoardBusinessId(Long boardBusinessId){
        return businessBoardImgMapper.selectAllByBoardBusinessId(boardBusinessId);
    }

    public void saveList(List<BoardBusinessImgVO> boardBusinessImgVOs) {
//        businessBoardImgMapper.insertList(boardBusinessImgVOs);
        boardBusinessImgVOs.stream().forEach(boardBusinessImgVO -> businessBoardImgMapper.insert(boardBusinessImgVO));
    }



//    public void deleteById(Long boardBusinessId){ businessBoardMapper.delete(boardBusinessId);}

    //    조회(이미지 정보까지)
//    public BoardBusinessDTO findById(Long boardBusinessId){ return businessBoardMapper.select(boardBusinessId);}

//    public List<BoardBusinessDTO> findByBusinessId(Long businessId){ return businessBoardMapper.selectAllByBusinessId(businessId);}

    //    목록(대표 이미지 하나)
//    public List<BoardBusinessDTO> findAll(){ return businessBoardMapper.selectAllList(); }

    //    목록(대표 이미지 하나)
//    public List<BoardBusinessDTO> findAll(Map<String, Object> searchMap){ return businessBoardMapper.selectAllListBySearch(searchMap); }



}
