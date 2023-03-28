package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.PageDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.NoticeVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/*")
@Slf4j
public class AdminController {

    private final NoticeService noticeService;
/*


/* 회원 목록 조회 */

    @GetMapping("admin-memberList")
    public void adminMemberList(){

    }


/* 회원 상세 보기 *//*

    @GetMapping("admin-member")
    public void adminMember(){

    }

    */
/* 회원 수정 *//*

    @GetMapping("admin-memberModify")
    public void adminMemberModify(){

    }
*/

    /* 회원 삭제 */


    /* ------------------------------------------------------------------------------------------------------------- */

/*

    */
/* 유통 회원 목록 조회*//*

    @GetMapping("admin-member-companyList")
    public void adminMemberCompanyList(){}

    */
/* 유통 회원 상세 보기 *//*

    @GetMapping("admin-member-company")
    public void adminMemberCompany(){}

    */
/* 유통 회원 수정 *//*

    @GetMapping("admin-member-companyModify")
    public void adminMemberCompanyModify(){}
*/

    /* 유통 회원 삭제 */

    /* ------------------------------------------------------------------------------------------------------------- */
    /* 공지 사항 */

    /* 공지사항 리스트 */
   @GetMapping("admin-noticeList")
    public String noticeList(Criteria criteria, Model model){

       model.addAttribute("noticeVO", noticeService.showList(criteria));
       model.addAttribute("pageDTO", new PageDTO(criteria, noticeService.count()));
       return "/admin/admin-noticeList";

   }

    /* 공지사항 조회 */
    @GetMapping("admin-notice/{noticeId}")
    public String notice(@PathVariable Long noticeId, Model model ){
        model.addAttribute(noticeService.showNotice(noticeId));
        return "admin/admin-notice";
    }

       /* 공지사항 작성 페이지 이동 */
    @GetMapping("admin-noticeWrite")
    public void AddNotice(Model model){
        model.addAttribute(new NoticeVO());
    }

    /* 공지사항 작성 완료 */
    @PostMapping("admin-noticeWrite")
    public RedirectView AddNotice(NoticeVO noticeVO, RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("noticeTitle", noticeVO.getNoticeTitle());
        redirectAttributes.addAttribute("noticeContent", noticeVO.getNoticeContent());
        noticeService.add(noticeVO);
        return new RedirectView("admin-noticeList");
    }

    /* 공지사항 삭제 */
    @DeleteMapping("admin-delete")
    @ResponseBody
    public void removeNotice(@RequestParam("checkedIds[]") List<String> noticeIds){
        noticeService.remove(noticeIds);
    }


    /* ------------------------------------------------------------------------------------------------------------- */


}
