package com.bugjeogbugjeog.app.bugjeogbugjeog.controller;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.AdminCriteria;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BusinessDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.*;
import com.bugjeogbugjeog.app.bugjeogbugjeog.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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

    @GetMapping("admin-memberList/{page}")
    @ResponseBody
    public Map<String, Object> memberListShow(@PathVariable("page") Integer page, AdminCriteria adminCriteria) throws Exception{
        int total = memberService.count().intValue();
        if (adminCriteria.getPage() == 0){
            adminCriteria.create(1,10,total,10);
        } else {
            adminCriteria.create(page,10, total,10);
        }

        Map<String, Object> info = new HashMap<>();

        info.put("members",memberService.adminMemberShowList(adminCriteria));
        info.put("criteria",adminCriteria);

        return info;
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
    @DeleteMapping("admin-memberDelete")
    @ResponseBody
    public void removeMember(@RequestParam("checkedIds[]") List<Long> checkIds){
        log.info("delete들어옴");
        log.info(checkIds.toString());
        for (int i=0; i < checkIds.size(); i++){
            memberService.removeMember(checkIds.get(i));
        }
    }



    /* ------------------------------------------------------------------------------------------------------------- */


    /* 유통 회원 목록 조회*/

    @GetMapping("admin-member-companyList")
    public String memberCompanyList(Model model){
        return "admin/admin-member-companyList";
    }

    @GetMapping("admin-member-companyList/{page}")
    @ResponseBody
    public Map<String, Object> memberCompanyListShow(@PathVariable("page") Integer page, AdminCriteria adminCriteria) throws Exception{
        int total = businessService.count().intValue();
        log.info("ajax 들어옴@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        log.info(page.toString());
        if (adminCriteria.getPage() == 0){
            adminCriteria.create(1,10,total,10);
        } else {
            adminCriteria.create(page,10, total,10);
            log.info(adminCriteria.toString());
            log.info(String.valueOf(adminCriteria.getOffset()));
        }
        log.info(businessService.adminShowListBusiness(adminCriteria).toString());

        Map<String, Object> info = new HashMap<>();
        info.put("businesses",businessService.adminShowListBusiness(adminCriteria));
        info.put("criteria",adminCriteria);

        return info;
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
    @DeleteMapping("admin-businessDelete")
    @ResponseBody
    public void removeBusiness(@RequestParam("checkedIds[]")  List<Long> checkIds){
        log.info("delete들어옴");
        log.info(checkIds.toString());
        for (int i=0; i < checkIds.size(); i++){
            businessService.removeBusiness(checkIds.get(i));
        }
    }

    /* ------------------------------------------------------------------------------------------------------------- */
    /* 공지 사항 */

    /* 공지사항 리스트 */
    @GetMapping("admin-noticeList")
    public String noticeListShow(){
        return "/admin/admin-noticeList";
    }

    @GetMapping("admin-noticeList/{page}")
    @ResponseBody
    public Map<String, Object> noticeListShow(@PathVariable("page") Integer page, AdminCriteria adminCriteria) throws Exception{
        log.info("ajax 들어옴@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        log.info(page.toString());
        int total = noticeService.count().intValue();
        if (adminCriteria.getPage() == 0){
            adminCriteria.create(1,10,total,10);
        } else {
            adminCriteria.create(page,10, total,10);
            log.info(adminCriteria.toString());
            log.info(String.valueOf(adminCriteria.getOffset()));
        }

        log.info(noticeService.adminShowList(adminCriteria).toString());

        Map<String, Object> info = new HashMap<>();

        info.put("notices", noticeService.adminShowList(adminCriteria));
        info.put("criteria",adminCriteria);

        return info;
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
    @DeleteMapping("admin-noticeDelete")
    @ResponseBody
    public void removeNotice(@RequestParam("checkedIds[]") List<Long> checkIds){
        log.info("delete들어옴");
        log.info(checkIds.toString());
        for (int i=0; i < checkIds.size(); i++){
            noticeService.remove(checkIds.get(i));
        }
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


    /* 유통 게시글 상세 보기*/
    @GetMapping("admin-distribution/{boardBusinessId}")
    public String adminBoardCompany(@PathVariable Long boardBusinessId, Model model){
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        log.info(businessBoardService.getBoardById(boardBusinessId).getBusinessStatus().toString());
        log.info(businessBoardService.getImagesById(boardBusinessId).toString());
        model.addAttribute("board", businessBoardService.getBoardById(boardBusinessId));
        model.addAttribute("images", businessBoardService.getImagesById(boardBusinessId));
        return "admin/admin-distribution";
    }

    /* 유통 게시판 수정 */

    /* 유통 게시판 삭제 */
    @DeleteMapping("admin-distributionDelete")
    @ResponseBody
    public void removeDistribution(@RequestParam("checkedIds[]")List<Long> checkIds){
        log.info("delete들어옴");
        log.info(checkIds.toString());
        for (int i=0; i < checkIds.size(); i++){
            businessBoardService.remove(checkIds.get(i));
        }
    }

    /* ------------------------------------------------------------------------------------------------------------- */

    /* 자유 게시판 목록 */
    @GetMapping("admin-freeBoardList")
    public String freeBoardShowList() {return "/admin/admin-freeboardList";}

    @GetMapping("admin-freeBoardList/{page}")
    @ResponseBody
    public Map<String, Object> freeBoardShowList(@PathVariable Integer page, AdminCriteria adminCriteria) throws Exception{
        log.info("ajax 들어옴@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        log.info(page.toString());
        int total = freeBoardService.count().intValue();
        if (adminCriteria.getPage() == 0){
            adminCriteria.create(1,10,total,10);
        } else {
            adminCriteria.create(page,10, total,10);
            log.info(adminCriteria.toString());
            log.info(String.valueOf(adminCriteria.getOffset()));
        }
        log.info(freeBoardService.adminShowList(adminCriteria).toString());

        Map<String, Object> info = new HashMap<>();

        info.put("frees",freeBoardService.adminShowList(adminCriteria));
        info.put("criteria",adminCriteria);
        return info;
    }

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
    @DeleteMapping("admin-freeDelete")
    @ResponseBody
    public void removeFree(@RequestParam("checkedIds[]") List<Long> checkIds){
        log.info("delete들어옴");
        log.info(checkIds.toString());
        for (int i=0; i < checkIds.size(); i++){
            freeBoardService.adminRemove(checkIds.get(i));
        }
    }

    /* ------------------------------------------------------------------------------------------------------------- */

    /* 문의 게시판 목록 */
    @GetMapping("admin-inquiryList")
    public String inquiryShowList(){return "/admin/admin-inquiryList";}

    @GetMapping("admin-inquiryList/{page}")
    @ResponseBody
    public Map<String, Object> inquiryShowList(@PathVariable("page") Integer page, AdminCriteria adminCriteria) throws Exception{
        log.info("ajax 들어옴@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        log.info(page.toString());
        int total = inquiryBoardService.count().intValue();
        if (adminCriteria.getPage() == 0){
            adminCriteria.create(1,10,total,10);
        } else {
            adminCriteria.create(page,10, total,10);
            log.info(adminCriteria.toString());
            log.info(String.valueOf(adminCriteria.getOffset()));
        }
        log.info(inquiryBoardService.adminFindAll(adminCriteria).toString());

        Map<String, Object> info = new HashMap<>();

        info.put("inquiries",inquiryBoardService.adminFindAll(adminCriteria));
        info.put("criteria",adminCriteria);

        return info;
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
    @DeleteMapping("admin-inquiryDelete")
    @ResponseBody
    public void removeInquiry(@RequestParam("checkedIds[]") List<Long> checkIds){
        log.info("delete들어옴");
        log.info(checkIds.toString());
        for (int i=0; i < checkIds.size(); i++){
            inquiryBoardService.removeInquiry(checkIds.get(i));
        }
    }

    /* ------------------------------------------------------------------------------------------------------------- */
}