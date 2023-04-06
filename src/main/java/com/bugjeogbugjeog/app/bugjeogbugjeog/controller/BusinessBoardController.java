package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardBusinessDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessImgVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BoardBusinessWriteDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.BusinessBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/board/business/*")
@RequiredArgsConstructor
@Slf4j
public class BusinessBoardController {
    private final BusinessBoardService businessBoardService;

    @GetMapping("list")
    public void goList(){;}

    @GetMapping("list/{page}")
    @ResponseBody
    public List<BoardBusinessDTO> getList(@PathVariable("page") int page, String sort, String category){
        Criteria criteria = new Criteria();
        criteria.setPageNum(page);
        log.info("====================================" + page);
        return businessBoardService.getList(criteria, sort, category);
    }

    @GetMapping("write")
    public void write(){;}

    @PostMapping("write")
    public RedirectView write(BoardBusinessWriteDTO boardBusinessWriteDTO, HttpSession session){
        boardBusinessWriteDTO.setBusinessId((Long)session.getAttribute("businessId"));
        businessBoardService.register(boardBusinessWriteDTO);
        return new RedirectView("/board/business/list");
    }
}
