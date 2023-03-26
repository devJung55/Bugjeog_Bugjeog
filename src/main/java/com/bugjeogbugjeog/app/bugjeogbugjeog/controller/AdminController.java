package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admins/*")
public class AdminController {


    /* 회원 목록 조회 */
    @GetMapping("/admins/admin-memberList")
    public void adminMemberList(){
    }

    /* 회원 상세 보기 */
    @GetMapping("/admins/admin-member")
    public void adminMember(){

    }

    /* 회원 삭제 */

    /* 회원 수정 */
    @GetMapping("/admins/admin-memberModify")
    public void adminMemberModify(){

    }


}
