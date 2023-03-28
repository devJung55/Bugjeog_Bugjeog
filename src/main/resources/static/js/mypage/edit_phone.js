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

    // 인증번호 보내기 클릭 시
    $(".code-button").click(function(){

        if(!phoneCheck){
            alert("핸드폰 번호를 확인해주세요.");
            return false;
        }

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
           type: "patch",
           data: {"memberPhoneNumber" : memberPhoneNumber},
           success : function (memberPhoneNumber) {
                alert("핸드폰 번호 변경 완료");
                $(".phoneNumber").text(phoneNumber(memberPhoneNumber));
                $("#modal-edit_phone").removeClass('is-active');
           }
       });
   });

    // 비밀번호 검사
    let passwordCheck = [false, false];
    const regPassword = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;
    const $newpassword = $("#new-password");
    const $newPasswordError = $(".password-error");

    $newpassword.blur(function(e){
        e.preventDefault();
        let password = $(this).val();
        if(!password){
            $newPasswordError.text("비밀번호를 입력해주세요.");
            passwordCheck[0] = false;
        }else if(!regPassword.test(password)){
            $newPasswordError.text("최소 8 자, 최소 하나의 문자, 하나의 숫자 및 하나의 특수 문자를 입력해주세요.");
            passwordCheck[0] = false;
        }else {
            passwordCheck[0] = true;
            $newPasswordError.text("");
        }
    });

    // 비밀번호 재검사
     const $passwordCheck = $("#new-password-check");
     const $passwordCheckError = $(".password-check-error");

     $passwordCheck.blur(function (e) {
         e.preventDefault();
        let password = $(this).val();
         if(!password){
            $passwordCheckError.text("비밀번호를 입력해주세요.");
             passwordCheck[1] = false;
         }else if(password != $newpassword.val()){
             $passwordCheckError.text("비밀번호가 일치하지 않습니다.");
             passwordCheck[1] = false;
         }else {
             $passwordCheckError.text("");
             passwordCheck[1] = true;
         }
     });

//     비밀번호 변경
    const $passwordSave = $(".password-save");

    $passwordSave.click(function(e){
        e.preventDefault();
        if(passwordCheck.filter(check => check == true ).length != 2){
            alert("잘못 입력된 정보가 있습니다.");
            return false;
        }

        $.ajax({
            url : "/mypage/profile/updatePassword",
            type : "patch",
            data : { "memberPassword" :btoa($newpassword.val())},
            success : function(){
                alert("비밀번호 변경 완료");
                $passwordModal.hide();
            }
        });
    });