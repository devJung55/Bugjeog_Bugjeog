    let emailCheck = false;
    let ceoNameCheck = false;
    let businessNameCheck = false;
    let registerCheck = false;
    let businessNumberCheck = false;
    let phoneNumberCheck = false;
    let passwordCheck1 = false;
    let passwordCheck2 = false;
    let allSelectCheck = false;
    let mustSelectCheck1 = false;
    let mustSelectCheck2 = false;
    let authCodeCheck = false;

    // 이메일 검사
    const regEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    const $email = $("input[type='email']");
    const $emailError = $(".email-error");

    $email.keyup(function(){
        let emailVal = $email.val();
        if(!emailVal){
            $emailError.text("이메일을 입력해주세요.");
            emailCheck = false;
        }else if(!regEmail.test(emailVal)){
            $emailError.text("이메일 형식에 맞춰서 작성해주세요.");
            emailCheck = false;
        } else if(!businessService.checkEmail()) {
            $emailError.text("이미 사용중인 이메일입니다.");
            $emailError.css("color", "red");
            emailCheck = false;
        } else {
            $emailError.text("사용 가능한 이메일입니다.");
            $emailError.css("color", "blue");
            emailCheck = true;
        }
    });

    // 대표자명 검사
    const $name = $(".representative-name");
    const $nameError = $(".name-error");

    $name.blur(function(){
        let nameVal = $name.val();
        if(!nameVal){
            $nameError.text("대표자명을 입력해주세요.");
            ceoNameCheck = false;
        }else {
            $nameError.text("");
            ceoNameCheck = true;
        }
    });

    // 회사명 검사
    const $companyName = $(".company-name");
    const $companyNameError = $(".company-name-error");

    $companyName.blur(function(){
        let companyNameVal = $companyName.val();
        if(!companyNameVal){
            $companyNameError.text("회사명을 입력해주세요.");
            businessNameCheck = false;
        }else {
            $companyNameError.text("");
            businessNameCheck = true;
        }
    });

// 회사명 검사
        const $regCompanyDate = RegExp(/^\d{4}\/(0[1-9]|1[012])\/(0[1-9]|[12][0-9]|3[01])$/);
        const $openCompanyDate = $(".open-company-date");
        const $openCompanyDateError = $(".open-company-date-error");
    
        $openCompanyDate.blur(function(){
            let openCompanyDateVal = $openCompanyDate.val();
            if(!openCompanyDateVal){
                $openCompanyDateError.text("설립일을 입력해주세요.");
                registerCheck = false;
            }else if(!$regCompanyDate.test(openCompanyDateVal)){
                $openCompanyDateError.text("형식에 맞게 입력해주세요.");
                registerCheck = false;
            }else {
                $openCompanyDateError.text("");
                registerCheck = true;
            }
        });

    // 사업자 번호 검사
    const regBusinessNumber = /^[0-9]{3}-[0-9]{2}-[0-9]{5}$/;
    const $businessNumber = $(".business-number-check");
    const $businessNumberError = $(".business-number-error");

    $businessNumber.blur(function(){
        if(!$businessNumber.val()){
            $businessNumberError.text("사업자번호를 입력해주세요.");
            businessNumberCheck = false;
        }else if(!regBusinessNumber.test($businessNumber.val())){
            $businessNumberError.text("형식에 맞춰서 작성해주세요.");
            businessNumberCheck = false;
        } else if(!businessService.checkBusinessNumber()) {
            $businessNumberError.css("color", "red");
            $businessNumberError.text("이미 사용중인 사업자번호입니다.");
            businessNumberCheck = false;
        } else {
            $businessNumberError.css("color", "blue");
            $businessNumberError.text("사용가능한 사업자번호입니다.");
            businessNumberCheck = true;
        }
    });



    // 휴대폰 검사
    const regPhone = /^010([0|1|6|7|8|9])?([0-9]{3,4})?([0-9]{4})$/;
    const $phone = $(".business-phone-number-check");
    const $phoneError = $(".phone-error");
    const $Checkbutton = $(".join-num-check-button");

    $phone.keyup(function(){
        let phoneVal = $phone.val();
        $phoneError.show();

        if(!phoneVal && phoneVal.length != 11){
            $Checkbutton.removeClass("phone-active");
            $Checkbutton.css("cursor", "inherit");
            $phoneError.css("color", "red");
            $phoneError.text("핸드폰 번호를 입력해주세요.");
            phoneNumberCheck = false;
        }else if(!regPhone.test(phoneVal)){
            $Checkbutton.css("cursor", "inherit");
            $Checkbutton.removeClass("phone-active");
            $phoneError.css("color", "red");
            $phoneError.text("올바른 형식이 아닙니다.");
            phoneNumberCheck = false;
        } else if(!businessService.checkPhoneNumber()) {
            $phoneError.css("color", "red");
            $phoneError.html("이미 사용중인 휴대폰번호입니다.");
            phoneNumberCheck = false;
        } else {
            $Checkbutton.css("cursor", "pointer");
            $Checkbutton.addClass("phone-active");
            $phoneError.css("color", "blue");
            $phoneError.html("사용 가능한 휴대폰번호입니다.");
            phoneNumberCheck = true;
        }
    });

    // 인증번호 보내기 클릭 시 
    $Checkbutton.click(function(){
        if(phoneNumberCheck) {
            businessService.sendSMS();
            $(".authcode-input").addClass("authcode-input-active");
            $(".auth-msg").show();
        }
    });

    const $authcode = $(".authcode-check");
    const $authCheckButton = $(".authcode-check-button");
    $authcode.keyup(function(){
        if($authcode.val().length == 4){
            $authCheckButton.show();
        }
    });



    // 비밀번호 검사
    const regPassword = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;
    const $password = $(".join-pw-input");
    const $passwordError = $(".password-error");
    const $passwordCheckError = $(".password-check-error");
    /*const $passwordCheck = $(".join-info-input");*/
    const $passwordCheck = $("input[name='passwordConfirm']");

    $password.blur(function(){
        $passwordError.show();
        let passwordVal = $password.val();
        if(!passwordVal){
            $passwordError.text("비밀번호를 입력해주세요.");
            $passwordError.removeClass("font-size");
            passwordCheck1 = false;
        }else if(!regPassword.test(passwordVal)){
            $passwordError.addClass("font-size");
            $passwordError.text("최소 8 자, 최소 하나의 문자, 하나의 숫자 및 하나의 특수 문자를 입력해주세요.");
            passwordCheck1 = false;
        }else {
            $passwordError.hide();
            $passwordError.text("");
            passwordCheck1 = true;
        }
    });

    $passwordCheck.blur(function(){
        $passwordCheckError.show();

        if($password.val() != $passwordCheck.val() || $passwordCheck.val() == ""){
            $passwordCheckError.text("비밀번호를 확인해주세요.");
            passwordCheck2 = false;
        } else if($password.val() == $passwordCheck.val()){
            $passwordCheckError.hide();
            passwordCheck2 = true;
        }
    });
    
	$("#allSelect").click(function() {
	    if($("#allSelect").is(":checked")) {
            $("input[name=check]").prop("checked", true);
            $(".join-terms-agree").addClass("checkbox-active-box");
            $(".checkbox-display").show();
            allSelectCheck = true;
            mustSelectCheck1 = true;
            mustSelectCheck2 = true;
        }else {
            $(".checkbox-display").hide();
            $(".join-terms-agree").removeClass("checkbox-active-box");
            $("input[name=check]").prop("checked", false);
            allSelectCheck = false;
            mustSelectCheck1 = false;
            mustSelectCheck2 = false;
        }
    });
	
	$("input[name=check]").click(function() {
	    var total = $("input[name=check]").length;
	    var checked = $("input[name=check]:checked").length;
	
	    if(total != checked){
            $($(".join-terms-agree")[0]).removeClass("checkbox-active-box");
            $($(".checkbox-display")[0]).hide();
            $("#allSelect").prop("checked", false);
        } else {
            $($(".join-terms-agree")[0]).addClass("checkbox-active-box");
            $($(".checkbox-display")[0]).show();
            $("#allSelect").prop("checked", true); 
        }    
	});

    $("input[name=check]").each((i, e) => {
        $(e).click(function(){
            if($(e).is(":checked")){
                $($(".checkbox-display")[i+1]).show();
                $($(".check-state")[i]).addClass("checkbox-active-box");
            }else {
                $($(".check-state")[i]).removeClass("checkbox-active-box");
                $($(".checkbox-display")[i+1]).hide();
            }
        });
    });


    /*--------------------- 회원가입 버튼 활성화 이벤트 ---------------------*/


    const $joinInputs = $(".must1, .must2, .join-all-agree-ment, input[type=text], input[type=email], input[type=password]");
    const $must1 = $(".must1");
    const $must2 = $(".must2");

    function send() {
        $joinInputs.trigger("blur");

        if(emailCheck & ceoNameCheck & businessNameCheck & registerCheck & businessNumberCheck & phoneNumberCheck & authCodeCheck & passwordCheck1 & passwordCheck2 & allSelectCheck & mustSelectCheck1 & mustSelectCheck2) {
            $(document.businessForm).submit();
        } else if(emailCheck & ceoNameCheck & businessNameCheck & registerCheck & businessNumberCheck & phoneNumberCheck & authCodeCheck & passwordCheck1 & passwordCheck2 & mustSelectCheck1 & mustSelectCheck2) {
            $(document.businessForm).submit();
        }
    }

    $must1.on("click", function(e) {
        if($must1.is(":checked")) {
            mustSelectCheck1 = true;
        } else {
            mustSelectCheck1 = false;
        }
    });

    $must2.on("click", function(e) {
        if($must2.is(":checked")) {
            mustSelectCheck2 = true;
        } else {
            mustSelectCheck2 = false;
        }
    });


    /*---------------------------- 사업자 회원가입 인증번호 ----------------------------*/


    $(".authcode-check-button").on("click", function(e) {
        if($(".authcode-check").val() == code) {
            $(".auth-msg").html("인증에 성공했습니다.");
            $(".auth-msg").css("color", "blue");
            authCodeCheck = true;
        } else {
            $(".auth-msg").html("인증에 실패했습니다.");
            $(".auth-msg").css("color", "red");
            authCodeCheck = false;
        }
    });


    /*---------------------------- 사업자 회원가입 ajax 모듈화 ----------------------------*/


    globalThis.code = "";

    let businessService = (function() {
        function checkEmail() {
            let check = true;
            let businessEmail = $("input[type=email]").val();

            $.ajax({
                url: "/members/businessEmailsCheck",
                type: "post",
                async : false,
                data: {"businessEmail": businessEmail},
                success: function(result) {
                    if(result == 1) {
                        check = false;
                    } else {
                        check = true;
                    }
                }
            });
            return check;
        }

        function checkPhoneNumber() {
            let check = true;
            let businessPhoneNumber = $(".phone-number-check").val();

            $.ajax({
                url: "/members/businessPhoneNumbersCheck",
                type: "post",
                async : false,
                data: {"businessPhoneNumber": businessPhoneNumber},
                success: function(result) {
                    if(result == 1) {
                        check = false;
                    } else {
                        check = true;
                    }
                }
            });
            return check;
        }

        function checkBusinessNumber() {
            let check = true;
            let businessNumber = $(".business-number-check").val();

            $.ajax({
                url: "/members/businessNumbersCheck",
                type: "post",
                async : false,
                data: {"businessNumber": businessNumber},
                success: function(result) {
                    if(result == 1) {
                        check = false;
                    } else {
                        check = true;
                    }
                }
            });
            return check;
        }
        function sendSMS() {
            let memberPhoneNumber = $(".phone-number-check").val();

            $.ajax({
                url: "/members/code",
                type: "post",
                data: {"memberPhoneNumber": memberPhoneNumber},
                success: function(result) {
                    code = result;
                    console.log(code);
                }
            });
            return code;
        }

        return {checkEmail: checkEmail, checkPhoneNumber: checkPhoneNumber, sendSMS: sendSMS, checkBusinessNumber: checkBusinessNumber}
    })();