package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.FreeBoardDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.FreeBoardImgDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Qualifier("freeBoard")
@Primary
@RequiredArgsConstructor
public class FreeBoardService {
    private FreeBoardDAO freeBoardDAO;
    private FreeBoardImgDAO freeBoardImgDAO;

//    추가
    public void registerFreeBoard(FreeBoardDAO freeBoardDAO){}
//    삭제

//    조회

//    목록
}
