package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.AdminCriteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardBusinessDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BusinessDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MemberDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.BusinessVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.Criteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.NoticeVO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.BusinessBoardService;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.BusinessService;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.MemberService;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.NoticeService;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.*;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/*")
@Slf4j
public class AdminController {

    private final NoticeService noticeService;
    private final MemberService memberService;
    private final BusinessService businessService;
    private final BusinessBoardService businessBoardService;
    private final InquiryBoardService inquiryBoardService;
    private final InquiryAnswerService inquiryAnswerService;
    private final FreeBoardService freeBoardService;

    /*  관리자 회원 목록 조회 */
    @GetMapping("admin-memberList")
    public String memberListShow(){
        return "/admin/admin-memberList";
    }

    @PostMapping("admin-memberList")
    @ResponseBody
    public Map<String, Object> memberListShow(@RequestBody Map<String, Object> requestData, AdminCriteria adminCriteria){
        Map<String, Object> result = new HashMap<>();
        int page = (int) requestData.get("page");

        if( page == 0) {
            page = 1;
        }
        adminCriteria.create( page, 10, memberService.count(), 5);
/*

        if( adminCriteria.getPage() == 0) {
            adminCriteria.create( 1, 10, memberService.count(), 5);
        } else {
            adminCriteria.create( adminCriteria.getPage(), 10, memberService.count(), 5);
        }
*/

        List<MemberDTO> members = memberService.adminMemberShowList(adminCriteria);
        log.info(members.toString());
        log.info("-------------------------------------------------------------------------------------------------");
        result.put("members", members);
        result.put("criteria", adminCriteria);
        return result;
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
    public RedirectView adminMemberModify(MemberVO memberVO){
        memberService.updateMember(memberVO);
        return new RedirectView("/admin/admin-member/" + memberVO.getMemberId());
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
    public String memberCompanyList(Model model){
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
    public RedirectView adminMemberCompanyModify(BusinessVO businessVO){
        businessService.setBusiness(businessVO);
        return new RedirectView("/admin/admin-member-company/" + businessVO.getBusinessId());
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
    public void AddNotice(Model model){model.addAttribute(new NoticeVO());}

    /* 공지사항 작성 완료 */
    @PostMapping("admin-noticeWrite")
    public RedirectView AddNotice(NoticeVO noticeVO){
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

    /* 유통 게시판 목록 */
    @GetMapping("admin-distributionList")
    public String distributionShowList(){
        return "admin/admin-distributionList";
    }

    @GetMapping("admin-distributionList/{page}")
    @ResponseBody
    public Map<String, Object> listMobiles(@PathVariable("page") Integer page, AdminCriteria adminCriteria) throws Exception{
        log.info("ajax 들어옴@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        log.info(page.toString());
        int total = businessBoardService.getCount().intValue();
        if (adminCriteria.getPage() == 0){
            adminCriteria.create(1,10,total,10);
        } else {
            adminCriteria.create(page,10, total,10);
            log.info(adminCriteria.toString());
            log.info(String.valueOf(adminCriteria.getOffset()));
        }
        log.info(businessBoardService.getListByPage(adminCriteria).toString());

        Map<String, Object> info = new HashMap<>();

        info.put("boards",businessBoardService.getListByPage(adminCriteria));
        info.put("criteria",adminCriteria);

        return info;
    }

    /* 유통 게시판 조회 */
    @GetMapping("admin-distribution")
    public void distributionShow(){}

    /* 유통 게시글 상세 보기*/
    @GetMapping("admin-board-company/{boardBusinessId}")
    public String adminBoardCompany(@PathVariable Long boardBusinessId, Model model){
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        log.info(businessBoardService.getBoardById(boardBusinessId).getBusinessStatus().toString());
        model.addAttribute("board", businessBoardService.getBoardById(boardBusinessId));
        return "admin/admin-distribution";
    }

    /* 유통 게시판 수정 */

    /* 유통 게시판 삭제 */
    @DeleteMapping("admin-distributionList")
    @ResponseBody
    public void removeDistribution(){}

    /* ------------------------------------------------------------------------------------------------------------- */

    /* 자유 게시판 목록 */
    @GetMapping("admin-freeBoardList")
    public void freeBoardShowList() {}

    /* 자유 게시판 조회 */
    @GetMapping("admin-freeBoard/{boardFreeId}")
    public String freeBoardShow(@PathVariable Long boardFreeId, Model model){
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        log.info(boardFreeId.toString());
        log.info(freeBoardService.adminShow(boardFreeId).toString());
        model.addAttribute(freeBoardService.adminShow(boardFreeId));
        return "admin/admin-freeBoard";
    }

    /* 자유 게시판 수정 */

    /* 자유 게시판 삭제 */
    @DeleteMapping("admin-freeBoardList")
    @ResponseBody
    public void removeFree(@RequestParam("checkedIds[]") List<String> boardFreeId){
        freeBoardService.adminRemove(boardFreeId);}

    /* ------------------------------------------------------------------------------------------------------------- */

    /* 문의 게시판 목록 */
    @GetMapping("admin-inquiryList")
    public void inquiryShowList(){
    }



    /* 문의 게시판 조회 */
    @GetMapping("admin-inquiry/{boardInquiryId}")
    public String inquiryShow(@PathVariable Long boardInquiryId, Model model) {
        model.addAttribute(inquiryBoardService.adminFindByInquiry(boardInquiryId));
        return "admin/admin-inquiry";
    }

    /* 문의 답변 작성 */
    @GetMapping("admin-inquiryWrite")
    public String addInquiryWrite(@RequestParam(value = "boardInquiryId") String boardInquiryId, Model model){
        model.addAttribute("boardInquiryId", boardInquiryId);
        model.addAttribute(new BoardInquiryAnswerVO());
        return "/admin/admin-inquiryWrite";
    }

    /* 문의 답변 작성 완료 */
    @PostMapping("admin-inquiryWrite")
    public RedirectView addInquiryWrite(BoardInquiryAnswerVO boardInquiryAnswerVO){
        inquiryAnswerService.addInquire(boardInquiryAnswerVO);
        return new RedirectView("/admin/admin-inquiry/" + boardInquiryAnswerVO.getBoardInquiryId());
//        return new RedirectView("/admin/admin-inquiry/" + boardInquiryAnswerVO.getBoardInquiryId());
    }

    /* 문의 게시판 삭제 */
    @DeleteMapping("admin-inquiryList")
    @ResponseBody
    public void removeInquiry(@RequestParam("checkedIds[]") List<String> boardInquiryIds){
        inquiryBoardService.removeInquiry(boardInquiryIds);
    }

    /* ------------------------------------------------------------------------------------------------------------- */
}