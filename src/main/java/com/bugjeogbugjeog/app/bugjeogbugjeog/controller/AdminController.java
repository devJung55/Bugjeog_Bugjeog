package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.NoticeVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/*")
public class AdminController {

    private final NoticeService noticeService;

    /* 회원 목록 조회 */
    @GetMapping("admin-memberList")
    public void adminMemberList(){

    }

    /* 회원 상세 보기 */
    @GetMapping("admin-member")
    public void adminMember(){

    }

    /* 회원 수정 */
    @GetMapping("admin-memberModify")
    public void adminMemberModify(){

    }

    /* 회원 삭제 */


    /* ------------------------------------------------------------------------------------------------------------- */


    /* 유통 회원 목록 조회*/
    @GetMapping("admin-member-companyList")
    public void adminMemberCompanyList(){}

    /* 유통 회원 상세 보기 */
    @GetMapping("admin-member-company")
    public void adminMemberCompany(){}

    /* 유통 회원 수정 */
    @GetMapping("admin-member-companyModify")
    public void adminMemberCompanyModify(){}

    /* 유통 회원 삭제 */

    /* ------------------------------------------------------------------------------------------------------------- */
    /* 공지 사항 */

    /* 공지사항 리스트 */
   @GetMapping("admin-noticeList")
    public void noticeList(Model model){
        model.addAttribute("notices",noticeService.showList());
    }

    /* 공지사항 조회 */
    @GetMapping("admin-notice")
    public void notice(Long noticeId, Model model){
        model.addAttribute(noticeService.showNotice(noticeId));
    }
    /* 공지사항 작성 */
    @PostMapping("admin-noticeWrite")
    public RedirectView AddNotice(NoticeVO noticeVO){
        noticeService.add(noticeVO);
        return new RedirectView("admin-noticeList");
    }

    /* 공지사항 작성 완료 */
    @GetMapping("admin-noticeWrite")
    public void AddNotice(Model model){
        model.addAttribute(new NoticeVO());
    }

    /* 공지사항 삭제 */
    @PostMapping("admin-noticeList")
    public RedirectView removeNotice(Long noticeId){
        noticeService.remove(noticeId);
        return new RedirectView("admin-noticeList");
    }


    /* ------------------------------------------------------------------------------------------------------------- */


}
