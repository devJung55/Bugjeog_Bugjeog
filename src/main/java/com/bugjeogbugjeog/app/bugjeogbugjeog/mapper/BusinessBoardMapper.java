package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardBusinessDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BusinessBoardMapper {

    //    추가
    public void insert(BoardBusinessVO boardBusinessVO);

    //    삭제
    public void delete(Long orderId);

    //    조회(이미지까지)
    public BoardBusinessDTO select(Long boardBusinessId);

    //    목록
    public List<BoardBusinessDTO> selectAll();

    public List<BoardBusinessDTO> selectAllByBusinessId(Long businessId);

    //    썸네일 포함 목록
    public List<BoardBusinessDTO> selectAllList();

    //    썸네일 포함 목록
    public List<BoardBusinessDTO> selectAllListBySearch(Map<String, Object> searchMap);
}
