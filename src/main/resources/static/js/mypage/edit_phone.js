    // 휴대폰 검사
    const regPhone = /^010([0|1|6|7|8|9])?([0-9]{3,4})?([0-9]{4})$/;
    const $phone = $("input[name='mobile']");
    const $phoneError = $(".phone-error");
    const $Checkbutton = $(".find-take-btn");
    $phoneError.hide();
    $phone.blur(function(){
        let phoneVal = $phone.val();
        if(!regPhone.test(phoneVal)||!phoneVal){
            $Checkbutton.css("cursor", "inherit");
            $Checkbutton.removeClass("phone-active");
            $phoneError.show();
            $phoneError.text(!phoneVal ? "핸드폰 번호를 입력해주세요." : "올바른 형식이 아닙니다.")
        }else {
            $Checkbutton.css("cursor", "pointer");
            $Checkbutton.addClass("phone-active");
            $phoneError.hide();
            $phoneError.text("");
            $btnAuthNums.removeAttr('disabled');
            $btnAuthNums.addClass('btn_active');
        }
    });

    // 인증번호 보내기 클릭 시 
    $Checkbutton.click(function(){
        $(".authcode-input").addClass("authcode-input-active");
        $(".auth-msg").show();
    });

    const $authcode = $(".authcode-input");
    const $authCheckButton = $(".certification-box");
    $authcode.keyup(function(){
        if($authcode.val().length == 4){
            $(".auth-msg").hide();
            $authCheckButton.show();
        }
    });