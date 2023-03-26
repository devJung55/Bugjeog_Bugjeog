package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/*")
public class AdminController {


    /* 회원 목록 조회 */
    @GetMapping("admin-memberList")
    public void adminMemberList(){}

    /* 회원 상세 보기 */
    @GetMapping("admin-member")
    public void adminMember(){}

    /* 회원 수정 */
    @GetMapping("admin-memberModify")
    public void adminMemberModify(){}

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



}
