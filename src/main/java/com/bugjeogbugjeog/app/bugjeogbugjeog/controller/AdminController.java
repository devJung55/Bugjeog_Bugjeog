package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/*")
public class AdminController {


    /* 회원 목록 조회 */
    @GetMapping("/test")
    public String adminMemberList(){
        return "/admin/admin-memberList";
    }

    /* 회원 상세 보기 */

    /* 회원 삭제 */

    /* 회원 수정 */


}
