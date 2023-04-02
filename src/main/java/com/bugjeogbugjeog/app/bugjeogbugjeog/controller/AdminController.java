package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BusinessDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MemberDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.PageDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.NoticeVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.BusinessService;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.MemberService;
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
    private final MemberService memberService;
    private final BusinessService businessService;


    /*  관리자 회원 목록 조회 */
    @GetMapping("admin-memberList")
    public String memberListShow(){
        return "/admin/admin-memberList";
    }

    @PostMapping("admin-memberList")
    @ResponseBody
    public List<MemberDTO> memberListShow(Criteria criteria){

        if (criteria.getPageNum() == 0) {
            criteria.setPageNum(1);
            criteria.setAmount(6);
        } else {
            criteria.setPageNum(criteria.getPageNum());
            criteria.setAmount(6);
        }
        return memberService.adminMemberShowList(criteria);
    }


    /* 회원 상세 보기 */
    @GetMapping("admin-member/{memberId}")
    public String adminMember(@PathVariable Long memberId, Model model){
        model.addAttribute(memberService.adminMemberShow(memberId));
        return "admin/admin-member";
    }



    /* 회원 수정 */
    @GetMapping("admin-memberModify")
    public String adminMemberModify(Long memberId, Model model){
        model.addAttribute(memberService.showMember(memberId));
        return "admin/admin-memberModify";
    }

    /* 회원 수정 완료 */
    @PostMapping("admin-memberModify")
    public RedirectView adminMemberModify(MemberVO memberVO, RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("memberEmail", memberVO.getMemberEmail());
        redirectAttributes.addAttribute("memberPhoneNumber",memberVO.getMemberPhoneNumber());
        redirectAttributes.addAttribute("memberStatus",memberVO.getMemberStatus());
        memberService.updateMember(memberVO);

        return new RedirectView("/admin/admin-member");
    }

    /* 회원 삭제 */
    @DeleteMapping("admin-deleteMember")
    @ResponseBody
    public void removeMember(@RequestParam("checkedIds[]") List<String> memberIds){
        memberService.removeMember(memberIds);
    }



    /* ------------------------------------------------------------------------------------------------------------- */


    /* 유통 회원 목록 조회*/

    @GetMapping("admin-member-companyList")
    public String memberCompanyList(){
        return "admin/admin-member-companyList";
    }

    @PostMapping("admin-member-companyList")
    @ResponseBody
    public List<BusinessDTO> memberCompanyListShow(Criteria criteria){


        if (criteria.getPageNum() == 0) {
            criteria.setPageNum(1);
            criteria.setAmount(6);
        } else {
            criteria.setPageNum(criteria.getPageNum());
            criteria.setAmount(6);
        }

        return businessService.adminShowListBusiness(criteria);
    }


    /* 유통 회원 상세 보기 */
    @GetMapping("admin-member-company/{businessId}")
    public String adminMemberCompany(@PathVariable Long businessId, Model model){
        model.addAttribute(businessService.adminShowBusiness(businessId));
        return "admin/admin-member-company";
    }


    /* 유통 회원 수정 */
    @GetMapping("admin-member-companyModify")
    public String adminMemberCompanyModify(Long businessId, Model model){
        model.addAttribute(businessService.showBusiness(businessId));
        return "/admin/admin-member-companyModify";
    }

    /* 유통 회원 수정 완료*/
    @PostMapping("admin-member-companyModify")
    public RedirectView adminMemberCompanyModify(BusinessVO businessVO, RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("businessCompanyName", businessVO.getBusinessCompanyName());
        redirectAttributes.addAttribute("businessNumber",businessVO.getBusinessNumber());
        redirectAttributes.addAttribute("businessPhoneNumber", businessVO.getBusinessPhoneNumber());
        businessService.setBusiness(businessVO);

        return new RedirectView("/admin/admin-member-company/{businessId}");
    }


    /* 유통 회원 삭제 */
    @DeleteMapping("admin-deleteBusiness")
    @ResponseBody
    public void removeBusiness(@RequestParam("checkedIds[]")  List<String> businessIds){
        businessService.removeBusiness(businessIds);
    }

    /* ------------------------------------------------------------------------------------------------------------- */
    /* 공지 사항 */

   /* *//* 공지사항 리스트 *//*
   @GetMapping("admin-noticeList")
    public void noticeList(Criteria criteria, Model model){
       model.addAttribute("noticeVO", noticeService.showList(criteria));
       model.addAttribute("pageDTO", new PageDTO(criteria, noticeService.count()));
   }*/

    /* 공지사항 리스트 */
   @GetMapping("admin-noticeList")
    public String noticeListShow(){
       return "/admin/admin-noticeList";
   }

   @PostMapping("admin-noticeList")
   public List<NoticeVO> noticeListShow(Criteria criteria){
       if (criteria.getPageNum() == 0) {
           criteria.setPageNum(1);
           criteria.setAmount(6);
       } else {
           criteria.setPageNum(criteria.getPageNum());
           criteria.setAmount(6);
       }

       return noticeService.showList(criteria);
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
    @DeleteMapping("admin-deleteNotice")
    @ResponseBody
    public void removeNotice(@RequestParam("checkedIds[]") List<String> noticeIds){
        noticeService.remove(noticeIds);
    }


    /* ------------------------------------------------------------------------------------------------------------- */


}
