package com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardFreeVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class BoardFreeLikeDTO {
    private List<Integer> boardReplyCounts;
    private List<MemberVO> memberVOs;
    private List<BusinessVO> businessVOS;
    private List<BoardFreeVO> boardFreeVOs;
}
