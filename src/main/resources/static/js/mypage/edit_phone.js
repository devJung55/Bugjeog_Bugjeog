    globalThis.code;
    globalThis.PhoneNumberCheck;
    let codeCheck = false;
    let phoneCheck= false;
    // 휴대폰 검사
    const regPhone = /^010([0|1|6|7|8|9])?([0-9]{3,4})?([0-9]{4})$/;
    const $phoneError = $(".phone-error");
    const $Checkbutton = $(".code-button");
    $phoneError.hide();
    $phone.blur(function(){
        let phoneVal = $phone.val();

        if(!phoneVal){
            $Checkbutton.css("cursor", "inherit");
            $Checkbutton.removeClass("phone-active");
            $phoneError.show();
            $phoneError.text("핸드폰 번호를 입력해주세요.");
            phoneCheck= false;

        }else if(!regPhone.test(phoneVal)){
            $Checkbutton.css("cursor", "inherit");
            $Checkbutton.removeClass("phone-active");
            $phoneError.show();
            $phoneError.text("올바른 형식이 아닙니다.");
            phoneCheck= false;

        } else if(memberPhoneCheck(phoneVal)){
            $Checkbutton.css("cursor", "inherit");
            $Checkbutton.removeClass("phone-active");
            $phoneError.show();
            $phoneError.text("현재 핸드폰 번호와 다른 번호를 입력해주세요.");
            phoneCheck= false;

        }else if(phoneNumberCheck(phoneVal)){
            $Checkbutton.css("cursor", "inherit");
            $Checkbutton.removeClass("phone-active");
            $phoneError.show();
            $phoneError.text("중복된 핸드폰 번호입니다.");
            phoneCheck= false;

        } else {
            $Checkbutton.css("cursor", "pointer");
            $Checkbutton.addClass("phone-active");
            $phoneError.hide();
            $phoneError.text("");
            phoneCheck= true;
            $(".code-button").removeAttr('disabled');
            $(".code-button").addClass('btn_active');
            $editPhoneBtnText.text(sendAuthText);
        }
    });




    function check (phoneNumberCheck){
        return phoneNumberCheck
    }



