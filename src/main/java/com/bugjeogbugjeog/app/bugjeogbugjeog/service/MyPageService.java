package com.bugjeogbugjeog.app.bugjeogbugjeog.service;

import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.dao.MypageDAO;
import com.bugjeogbugjeog.app.bugjeogbugjeog.domain.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final MypageDAO mypageDAO;

    //    회원정보 조회
    public MemberVO memberInfo(Long memberId){
        return mypageDAO.findById(memberId);
    };

    //    회원정보 수정
    public void memberUpdate(MemberVO memberVO) {
        mypageDAO.updateById(memberVO);
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

//    핸드폰 중복검사
    public Boolean PhoneNumberCheck(String memberPhoneNumber){
        List<String> phoneNumbers = mypageDAO.findAllToMemberPhoneNumber();
        boolean check = false;

        for (int i = 0; i < phoneNumbers.size(); i++){
            if(phoneNumbers.get(i).equals(memberPhoneNumber)){
                check = true;
            }
        }
        return check;
    }
    
}
