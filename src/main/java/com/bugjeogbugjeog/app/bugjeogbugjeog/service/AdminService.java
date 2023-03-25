package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.AdminDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminDAO adminDAO;

    /* 회원 목록 조회 */
    public List<MemberVO> showList(){ return adminDAO.findAll(); }

}
