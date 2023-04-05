package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardInquiryDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.PageDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardInquiryVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.InquiryAnswerService;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.InquiryBoardService;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.InquiryCriteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
//@RequestMapping("/board/inquiry")
@RequiredArgsConstructor
@Slf4j
public class InquiryBoardController {
    private final InquiryBoardService inquiryBoardService;
    private final InquiryAnswerService inquiryAnswerService;

    @GetMapping(value = {"/board/inquiry", "/board/inquiry/"})
    public RedirectView defaultRoot() {
        return new RedirectView("/board/inquiry/list");
    }

    //    리스트
    @GetMapping("/board/inquiry/list")
    public void showList(Model model, InquiryCriteria inquiryCriteria) {
        model.addAttribute("pageDTO", new PageDTO(inquiryCriteria, Integer.parseInt(inquiryBoardService.count().toString())));
        model.addAttribute("boards", inquiryBoardService.showList(inquiryCriteria));
    }

    @GetMapping("/board/inquiry/list/ajax")
    @ResponseBody
    public void getList(Model model, @RequestBody() Long startPage) {

    }

    @GetMapping("/board/inquiry/detail")
    public void detail(HttpServletRequest req, Model model) {
        Long boardInquiryId = Long.parseLong(req.getParameter("boardInquiryId"));
        BoardInquiryDTO dto = inquiryBoardService.getBoard(boardInquiryId);
        model.addAttribute("board", dto);
        model.addAttribute("answers", inquiryAnswerService.getAnswersByBoardInquiryId(boardInquiryId));
    }

    @GetMapping("/board/inquiry/write")
    public void write(Model model) {
        model.addAttribute(new BoardInquiryVO());
    }

    @PostMapping("/board/inquiry/write")
    public RedirectView write(BoardInquiryVO boardInquiryVO, HttpServletRequest req) {
        System.out.println(boardInquiryVO.getBoardInquiryStatus());
        if (!req.getParameter("businessId").isEmpty() || !req.getParameter("memberId").isEmpty()) {
            boardInquiryVO.setBusinessId(Long.parseLong(req.getParameter("businessId")));
            boardInquiryVO.setMemberId(Long.parseLong(req.getParameter("memberId")));
            inquiryBoardService.registerBoard(boardInquiryVO);
        }
//        if (req.getParameter("businessId") != null) {
////            boardInquiryVO.setBusinessId(Long.parseLong(req.getParameter("businessId")));
//            boardInquiryVO.setBusinessId(Long.parseLong(req.getParameter("businessId")));
//            boardInquiryVO.setMemberId(null);
//            inquiryBoardService.registerBoard(boardInquiryVO, "business");
//        } else {
//            boardInquiryVO.setBusinessId(null);
//            boardInquiryVO.setMemberId(Long.parseLong(req.getParameter("memberId")));
//            inquiryBoardService.registerBoard(boardInquiryVO, "member");
//        }
        return new RedirectView("/board/inquiry/list");
    }
}
