package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.AdminCriteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardFreeDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.SearchDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.enums.SearchEnum;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.FreeBoardService;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/main/*")
@RequiredArgsConstructor
@Slf4j
public class MainController {
    private final MemberService memberService;
    private final FreeBoardService freeBoardService;

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

    @GetMapping("boards/{boardType}/{orderTypeNumber}")
    @ResponseBody
    public List<BoardFreeDTO> getBoards(@PathVariable String boardType, @PathVariable Integer orderTypeNumber){
        if(boardType.equals("free")){
            int rowCount = 8;
            int total = 0;
            int pageCount = 0;

            AdminCriteria criteria = new AdminCriteria();
            criteria.create(1, rowCount, total, pageCount);
            criteria.setSearchDTO(new SearchDTO().setOrderColumn(SearchEnum.values()[orderTypeNumber]));

            return freeBoardService.getListWithName(criteria);
        }

        return null;
    }
}
