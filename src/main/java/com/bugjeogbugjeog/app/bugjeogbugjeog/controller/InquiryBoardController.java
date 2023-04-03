package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardInquiryDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardInquiryVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.InquiryAnswerService;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.InquiryBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public void showList(Model model) {
        model.addAttribute("boards", inquiryBoardService.showList());
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

}
