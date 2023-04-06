package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.AdminCriteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardFreeDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.PageDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.SearchDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.enums.SearchEnum;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.BusinessBoardService;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.FreeBoardService;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/main/*")
@RequiredArgsConstructor
@Slf4j
public class MainController {
    private final MemberService memberService;
    private final FreeBoardService freeBoardService;
    private final BusinessBoardService businessBoardService;

    /*화면 이동*/
    @GetMapping("")    //url 부분
    public String mainPage(HttpSession httpSession){
        if(httpSession.getAttribute("memberId") != null) {  // 자영업자 로그인 시 분기처리
            httpSession.getAttribute("memberId");
            log.info("memberId : " + httpSession.getAttribute("memberId"));

        } else if(httpSession.getAttribute("businessId") != null) { // 비지니스 로그인 시 분기처리
            httpSession.getAttribute("businessId").toString();
            log.info("businessId : " + httpSession.getAttribute("businessId"));

        } else if(httpSession.getAttribute("memberVO") != null){    // 카카오 및 네이버 로그인 시 분기처리
            httpSession.setAttribute("memberId", memberService.loginNaverOauth(((MemberVO)httpSession.getAttribute("memberVO")).getMemberEmail()));
            log.info("memberId : " + httpSession.getAttribute("memberId"));
        }
        return "/main/main";
    }
    /* http://localhost:10000/main/ */

    @GetMapping("boards/{boardType}/{orderType}")
    @ResponseBody
    public <T> List<T> getBoards(@PathVariable String boardType, @PathVariable String orderType){
        int rowCount = 8;
        int total = 0;
        int pageCount = 0;

        if(boardType.equals("free")){

            AdminCriteria criteria = new AdminCriteria();
            criteria.create(1, rowCount, total, pageCount);
            switch (orderType){
                case "recent":
                    criteria.setSearchDTO(new SearchDTO().setOrderColumn(SearchEnum.BOARD_FREE_REGISTER_DATE));
                    break;
                case "popular":
                    criteria.setSearchDTO(new SearchDTO().setOrderColumn(SearchEnum.BOARD_FREE_LIKE));
                    break;
            }

            return (List<T>) freeBoardService.getListWithName(criteria);
        } else if(boardType.equals("business")){

            Criteria criteria = new Criteria(1, rowCount);
            PageDTO pageDTO = new PageDTO(criteria, total);

            switch (orderType){
                case "recent":
                    pageDTO = pageDTO.setSearchDTO(new SearchDTO().setOrderColumn(SearchEnum.BOARD_BUSINESS_REGISTER_DATE));
                    break;
                case "grade":
                    pageDTO = pageDTO.setSearchDTO(new SearchDTO().setOrderColumn(SearchEnum.BOARD_BUSINESS_GRADE_AVERAGE));
                    break;
            }

//            return (List<T>) businessBoardService.getList(pageDTO);
            return null;
        }

        return null;
    }

    //    파일 디스플레이
    @GetMapping("imgs/display")
    @ResponseBody
    public byte[] display(String fileName) throws IOException {
        return FileCopyUtils.copyToByteArray(new File("C:/upload", fileName));
    }
}
