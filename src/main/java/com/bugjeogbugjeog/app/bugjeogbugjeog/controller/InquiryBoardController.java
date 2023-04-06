package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardInquiryDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.InquiryPageDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardInquiryVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.InquiryCriteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.InquiryAnswerService;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.InquiryBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/board/inquiry/*")
@RequiredArgsConstructor
@Slf4j
public class InquiryBoardController {
    private final InquiryBoardService inquiryBoardService;
    private final InquiryAnswerService inquiryAnswerService;

    //    리스트
    @GetMapping("list")
    public void showList(Model model, InquiryCriteria criteria) {
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@criteria :" + criteria);
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@list : " + inquiryBoardService.getAllList(criteria));
        model.addAttribute("inquiryVO",inquiryBoardService.getAllList(criteria));
        model.addAttribute("pageDTO", new InquiryPageDTO(criteria, inquiryBoardService.count().intValue()));
    }

    @GetMapping("detail")
    public void detail(HttpServletRequest req, Model model) {
        Long boardInquiryId = Long.parseLong(req.getParameter("boardInquiryId"));
        BoardInquiryDTO dto = inquiryBoardService.getBoard(boardInquiryId);
        model.addAttribute("board", dto);
        model.addAttribute("answers", inquiryAnswerService.getAnswersByBoardInquiryId(boardInquiryId));
    }

    @GetMapping("write")
    public void write(Model model) {
        model.addAttribute(new BoardInquiryVO());
    }

    @PostMapping("write")
    public RedirectView write(BoardInquiryVO boardInquiryVO, HttpServletRequest req) {
        HttpSession session = req.getSession();
        Long memberId = (Long) session.getAttribute("memberId");
        Long businessId = (Long) session.getAttribute("businessId");

        if(memberId != null){
            boardInquiryVO.setMemberId(memberId);
        }else {
            boardInquiryVO.setBusinessId(businessId);
        }

        inquiryBoardService.registerBoard(boardInquiryVO);

        return new RedirectView("/board/inquiry/list");
    }
}
