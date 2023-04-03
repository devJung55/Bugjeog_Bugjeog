package com.bugjeogbugjeog.app.bugjeogbugjeog.mapper;

<<<<<<< HEAD
=======
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardInquiryAnswerVO;
>>>>>>> ddc8d66d5f91b5b6d83e4425ffeaae58a0fd8a4e
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardInquiryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InquiryAnswerMapper {
<<<<<<< HEAD
    public List<BoardInquiryVO> selectAllByBoardInquiryId(Long boardInquiryId);
=======

    // 문의 작성
    public void insert(BoardInquiryAnswerVO boardInquiryAnswerVO);

>>>>>>> ddc8d66d5f91b5b6d83e4425ffeaae58a0fd8a4e
}
