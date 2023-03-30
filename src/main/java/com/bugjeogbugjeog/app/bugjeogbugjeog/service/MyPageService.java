package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.*;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardFreeLikeDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.BoardReplyDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dto.MyPageInquireDTO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.*;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final MemberDAO memberDAO;
    private final FreeLikeDAO freeLikeDAO;
    private final InquiryBoardDAO inquiryBoardDAO;
    private final FreeBoardDAO freeBoardDAO;
    private final ReplyDAO replyDAO;
    private final BusinessDAO businessDAO;

    //    회원 정보 조회
    public MemberVO memberInfo(Long memberId){
        return memberDAO.findById(memberId);
    };

    //    회원 정보 수정
    public void memberUpdate(MemberVO memberVO) {
        memberDAO.updateById(memberVO);
    }

    //    회원 탈퇴
    public void memberWithdraw(Long memberId) { memberDAO.deleteById(memberId);}

    //    파일 저장
    public void fileSave(MemberVO member){
        MemberVO memberVO = memberDAO.findById(member.getMemberId());

        memberVO.setMemberImgOriginalName(member.getMemberImgOriginalName());
        memberVO.setMemberImgPath(member.getMemberImgPath());
        memberVO.setMemberImgUuid(member.getMemberImgUuid());

        memberDAO.updateById(memberVO);
    }

    //    이름 변경
    public void updateName(Long memberId, String memberName){
        MemberVO memberVO = memberDAO.findById(memberId);
        memberVO.setMemberName(memberName);
        memberDAO.updateById(memberVO);
    }

    //    핸드폰 번호 변경
    public void updatePhoneNumber(Long memberId , String memberPhoneNumber){
        MemberVO memberVO = memberDAO.findById(memberId);
        memberVO.setMemberPhoneNumber(memberPhoneNumber);
        memberDAO.updateById(memberVO);
    }

    // sms 발송 서비스
    public String memberSMS(String memberPhoneNumber){
        String apiKey = "";
        String apiSecret = "";
        String fromNumber = "";
        String code = "";

        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            code += random.nextInt(10);
        }
        Message coolsms = new Message(apiKey, apiSecret);

        HashMap<String, String> params = new HashMap<>();
        params.put("to", memberPhoneNumber);
        params.put("from", fromNumber);
        params.put("type", "sms");
        params.put("text", "[북적북적] 인증번호 "+ code +" 를 입력하세요.");
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
        return code;
    }

    //    핸드폰 중복 검사
    public Boolean phoneNumberCheck(String PhoneNumber){
        List<String> phoneNumbers = memberDAO.findAllToMemberPhoneNumber();
        boolean check = false;

        for (int i = 0; i < phoneNumbers.size(); i++){
            if(phoneNumbers.get(i).equals(PhoneNumber)){
                check = true;
            }
        }
        return check;
    }

    // 비밀 번호 변경
    public void updatePassword(Long memberId, String memberPassword){
        MemberVO memberVO = memberDAO.findById(memberId);
        memberVO.setMemberPassword(memberPassword);
        memberDAO.updateById(memberVO);
    }

    // 문의 게시판 목록
    public MyPageInquireDTO inquireList(Long memberId, Criteria criteria){
        List<BoardInquiryVO> inquires = inquiryBoardDAO.findAllByIdToInquire(memberId,criteria);
        List<Long> status = new ArrayList<>();
        MyPageInquireDTO myPageInquireDTO = new MyPageInquireDTO();

        for(int i =0; i < inquires.size(); i++){
            Long inquiryBoardId = inquires.get(i).getBoardInquiryId();
            status.add(inquiryBoardDAO.inquireAnswer(inquiryBoardId));
        }

        myPageInquireDTO.setAnswerStatus(status);
        myPageInquireDTO.setInquire(inquires);

        return myPageInquireDTO;
    }

    // 문의 게시판 개수
    public Integer inquireCount(Long memberId){
        return inquiryBoardDAO.getCountToInquire(memberId);
    };

    // 좋아요 한 게시물 목록
    public BoardFreeLikeDTO likeList(Long memberId, Criteria criteria){
        BoardFreeLikeDTO boardFreeLikeDTO = new BoardFreeLikeDTO();
        List<MemberVO> memberVOs = new ArrayList<>();
        List<BusinessVO> businessVOS = new ArrayList<>();
        List<Integer> boardReplyCounts = new ArrayList<>();
        List<BoardFreeVO> boardFreeVOS = freeLikeDAO.findAllToLike(memberId,criteria);

        boardFreeVOS.stream().map(data -> data.getBoardFreeId()).forEach(data -> boardReplyCounts.add(replyDAO.getReplyCount(data)));
        boardFreeVOS.stream().map(data -> data.getMemberId()).forEach(data -> memberVOs.add(memberDAO.findById(data)));
        boardFreeVOS.stream().map(data -> data.getBusinessId()).forEach(data -> businessVOS.add(businessDAO.findByIdToBusiness(data)));

        boardFreeLikeDTO.setBoardReplyCounts(boardReplyCounts);
        boardFreeLikeDTO.setBusinessVOS(businessVOS);
        boardFreeLikeDTO.setMemberVOs(memberVOs);
        boardFreeLikeDTO.setBoardFreeVOs(boardFreeVOS);

        return boardFreeLikeDTO;
    }

    // 좋아요 게시물 갯수
    public Integer likeCount(Long memberId){
        return freeLikeDAO.getCountToLike(memberId);
    }

    // 댓글 달린 보드 리스트
    public BoardReplyDTO replyBoardFreeList(Long memberId, Criteria criteria){
        BoardReplyDTO boardReplyDTO = new BoardReplyDTO();
        List<MemberVO> memberVOs = new ArrayList<>();
        List<BusinessVO> businessVOS = new ArrayList<>();
        List<BoardFreeVO> boardFreeVOS = replyDAO.findAllBoardFreeToMember(memberId,criteria);

        boardFreeVOS.stream().map(data -> data.getMemberId()).forEach(data -> memberVOs.add(memberDAO.findById(data)));
        boardFreeVOS.stream().map(data -> data.getBusinessId()).forEach(data -> businessVOS.add(businessDAO.findByIdToBusiness(data)));

        boardReplyDTO.setBusinessVOS(businessVOS);
        boardReplyDTO.setMemberVOS(memberVOs);
        boardReplyDTO.setBoardFreeVOS(boardFreeVOS);

        return boardReplyDTO;
    }

    // 댓글 단 게시판의 갯수
    public Integer replyBoardFreeCount(Long memberId, Criteria criteria){
        return replyDAO.findAllBoardFreeToMember(memberId,criteria).size();
    }

    // 각 보드의 댓글 리스트
    public List<FreeReplyVO> replyList(Long memberId, Long boardFreeId){
        return replyDAO.findAllFreeReplyToMember(memberId, boardFreeId);
    }

    // 댓글 갯수
    public Integer replyCount(Long memberId){
        return replyDAO.getReplyTotal(memberId);
    };

    // 자유게시판 목록 가져오기
    public List<BoardFreeVO> freeList(Long memberId, Criteria criteria){
        return freeBoardDAO.findByIdBoardFreeVO(memberId, criteria);
    }

    // 자유게시판 개수
    public Integer freeCount(Long memberId){
        return freeBoardDAO.getFreeBoardTotal(memberId);
    }

    // 게시판 각각의 개수
    public Map<String, Integer> allcount(Long memberId){
        Map<String, Integer> allCount = new HashMap<>();

        allCount.put("freeBoardCount",freeBoardDAO.getFreeBoardTotal(memberId));
        allCount.put("replyCount", replyDAO.getReplyTotal(memberId));
        allCount.put("likeBoardCount", freeLikeDAO.getCountToLike(memberId));
        allCount.put("inquireCount", inquiryBoardDAO.getCountToInquire(memberId));
        allCount.put("interestingCount",memberDAO.getInterestingCountById(memberId));

        return allCount;
    }

}
