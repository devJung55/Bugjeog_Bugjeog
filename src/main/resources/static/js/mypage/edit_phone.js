    globalThis.code;
    globalThis.PhoneNumberCheck;
    let codeCheck = false;

    // 휴대폰 검사
    const regPhone = /^010([0|1|6|7|8|9])?([0-9]{3,4})?([0-9]{4})$/;
    const $phoneError = $(".phone-error");
    const $Checkbutton = $(".code-button");
    $phoneError.hide();
    $phone.blur(function(){
        let memberPhoneNumber = memberVO.memberPhoneNumber.replaceAll("-","").trim();
        let phoneVal = $phone.val();
        console.log(phoneNumberCheck(phoneVal));

        if(!regPhone.test(phoneVal)||!phoneVal){
            $Checkbutton.css("cursor", "inherit");
            $Checkbutton.removeClass("phone-active");
            $phoneError.show();
            $phoneError.text(!phoneVal ? "핸드폰 번호를 입력해주세요." : "올바른 형식이 아닙니다.")

        }else if(phoneVal == memberPhoneNumber){
            $Checkbutton.css("cursor", "inherit");
            $Checkbutton.removeClass("phone-active");
            $phoneError.show();
            $phoneError.text("현재 핸드폰 번호와 다른 번호를 입력해주세요.");

        }else if(phoneNumberCheck(phoneVal)){
            $Checkbutton.css("cursor", "inherit");
            $Checkbutton.removeClass("phone-active");
            $phoneError.show();
            $phoneError.text("중복된 핸드폰 번호입니다.");
        } else {
            $Checkbutton.css("cursor", "pointer");
            $Checkbutton.addClass("phone-active");
            $phoneError.hide();
            $phoneError.text("");
            $(".code-button").removeAttr('disabled');
            $(".code-button").addClass('btn_active');
            $editPhoneBtnText.text(sendAuthText);
        }
    });

    // 인증번호 보내기 클릭 시
    $(".code-button").click(function(){
        $(".authcode-input").addClass("authcode-input-active");
        $(".auth-msg").show();
        $.ajax({
            url: "/mypage/profile/code",
            type : "post",
            data : {"memberPhoneNumeber" : $phone.val()},
            success : function(code){
                console.log(code);
                $authSendMsg.text(authNumSededText);
                globalThis.code = code;
            }
        });
    });

   // 인증번호 검사
   const $codeInput = $(".code-check-input");

   $codeInput.keyup(function(){
       if(!$codeInput.val()){
           $authSendMsg.css("color","red");
           $authSendMsg.text("인증번호를 입력해주세요.")
           codeCheck = false;
       }else if($codeInput.val() != globalThis.code){
           $authSendMsg.css("color","red");
           $authSendMsg.text("인증번호가 일치하지 않습니다.");
           codeCheck = false;
       }else {
           $authSendMsg.css("color","#36f");
           $authSendMsg.text("인증번호가 일치합니다.");
           codeCheck = true;
       }
   });

   // 핸드폰 중복검사
    function phoneNumberCheck(phoneNumber){
        var check = false;
        $.ajax({
            url : "/mypage/profile/memberPhoneCheck",
            type: "get",
            data : {"memberPhoneNumber" : phoneNumber},
            async : false,
            success : function(phoneNumberCheck){
                check = phoneNumberCheck;
            }
        });
        return check;
    }

    function check (phoneNumberCheck){
        return phoneNumberCheck
    }

   // 휴대폰 번호 변경
   const $phoneUpdateButton = $(".phone-save");
   $phoneUpdateButton.click(function(){
       if(!codeCheck){
            alert("인증번호를 확인해주세요.");
            return false;
       }
       let memberPhoneNumber = $("input[name=mobile]").val();
       $.ajax({
           url : "/mypage/profile/phoneNumberUpdate",
           type: "post",
           data: {"memberPhoneNumber" : memberPhoneNumber},
           success : function (memberPhoneNumber) {
                alert("핸드폰 번호 변경 완료");
                $(".phoneNumber").text(phoneNumber(memberPhoneNumber));
                $("#modal-edit_phone").removeClass('is-active');
           }
       });
   });

   // 이름 변경 검사
    const $nameInput = $("input[name=username]");
    let nameCheck = false;
    $nameInput.blur(function(){
        if(!$(this).val()){
            $(".name-error").text("이름을 입력해주세요.");
            nameCheck = false;
        }else if($(this).val().length < 2){
            $(".name-error").text("이름은 2자리 이상 입력해주세요.");
            nameCheck = false;
        }else if($(this).val() == memberVO.memberName){
            $(".name-error").text("현재 이름과 동일합니다.");
            nameCheck = false;
        }else {
            $(".name-error").text("");
            nameCheck = true;
        }
    });

    // 이름 저장
    const $nameSaveButton = $(".name-save");

    $nameSaveButton.click(function(){
        if(!nameCheck){
            alert("이름을 확인해주세요.");
            return false;
        }
        let memberName = $("input[name=username]").val();
        $.ajax({
            url : "/mypage/profile/updateName",
            type: "post",
            data: {"memberName" : memberName},
            success : function (memberName) {
                alert("이름 변경 완료");
                $(".change-name").text(memberName + "님, 환영해요.");
                $(".memberName").text(memberName);
                $("#modal-edit_name").removeClass('is-active');
            }
        });
    });