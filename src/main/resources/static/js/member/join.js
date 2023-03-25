
    // 이메일 검사
    const regEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    const $email = $("input[type='email']");
    const $emailError = $(".email-error");

    let emailCheck = false;
    let nameCheck = false;
    let phoneNumberCheck = false;
    let authCodeCheck = false;
    let passwordCheck1 = false;
    let passwordCheck2 = false;
    let allCheckBox = false;
    let must1CheckBox = false;
    let must2CheckBox = false;

    $email.blur(function(){
        let emailVal = $email.val();
        if(!emailVal){
            $emailError.text("이메일을 입력해주세요.");
        }else if(!regEmail.test(emailVal)){
            $emailError.text("이메일 형식에 맞춰서 작성해주세요.");
        }else {
            $emailError.text("");
            emailCheck = true;
            console.log(emailCheck);
        }
    });

    // 이름 검사
    const $name = $("input[name='memberName']");
    const $nameError = $(".name-error");

    $name.blur(function(){
        let nameVal = $name.val();
        if(!nameVal){
            $nameError.text("이름을 입력해주세요.");
        }else {
            $nameError.text("");
            nameCheck = true;
            console.log(nameCheck);
        }
    });

    // 휴대폰 검사
    const regPhone = /^010([0|1|6|7|8|9])?([0-9]{3,4})?([0-9]{4})$/;
    const $phone = $(".join-phone-number");
    const $phoneError = $(".phone-error");
    const $Checkbutton = $(".join-num-check-button");

    $phone.blur(function(){
        let phoneVal = $phone.val();
        $phoneError.show();
        if(!phoneVal){
            $Checkbutton.removeClass("phone-active");
            $Checkbutton.css("cursor", "inherit");
            $phoneError.text("핸드폰 번호를 입력해주세요.");
        }else if(!regPhone.test(phoneVal)){
            $Checkbutton.css("cursor", "inherit");
            $Checkbutton.removeClass("phone-active");
            $phoneError.text("올바른 형식이 아닙니다.");
        }else {
            $Checkbutton.css("cursor", "pointer");
            $Checkbutton.addClass("phone-active");
            $phoneError.hide();
            $phoneError.text("");
            phoneNumberCheck = true;
            console.log(phoneNumberCheck);
        }
    });

    // 인증번호 보내기 클릭 시
    $Checkbutton.click(function(){
        $(".authcode-input").addClass("authcode-input-active");
        $(".auth-msg").show();
    });

    const $authcode = $(".authcode-input");
    const $authCheckButton = $(".authcode-check-button");
    $authcode.keyup(function(){
        if($authcode.val().length == 4){
            $(".auth-msg").hide();
            $authCheckButton.show();
            authCodeCheck = true;
        }
    });
    // 비밀번호 검사
    const regPassword = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;
    const $password = $("input[name='memberPassword']");
    const $passwordError = $(".password-error");
    const $passwordCheckError = $(".password-check-error");
    const $passwordCheck = $("input[name='passwordConfirm']");

    $password.blur(function(){
        $passwordError.show();
        let passwordVal = $password.val();
        if(!passwordVal){
            $passwordError.text("비밀번호를 입력해주세요.");
            $passwordError.removeClass("font-size");
        }else if(!regPassword.test(passwordVal)){
            $passwordError.addClass("font-size");
        }else {
            $passwordError.hide();
            $passwordError.text("");
            passwordCheck1 = true;
            console.log(passwordCheck1);
        }
    });

    $passwordCheck.blur(function(){
        $passwordCheckError.show();
        if($password.val() != $passwordCheck.val()){
            $passwordCheckError.text("비밀번호를 확인해주세요.");
        }else {
            $passwordCheckError.hide();
            passwordCheck2 = true;
            console.log(passwordCheck2);
        }
    });

	$("#allSelect").click(function() {
        console.log("눌림");
	    if($("#allSelect").is(":checked")) {
            $("input[name=check]").prop("checked", true);
            $(".join-terms-agree").addClass("checkbox-active-box");
            $(".checkbox-display").show();
            allCheckBox = true;
            console.log(allCheckBox);
        }else {
            $(".checkbox-display").hide();
            $(".join-terms-agree").removeClass("checkbox-active-box");
            $("input[name=check]").prop("checked", false);
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
            console.log(i);

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

    const $joinButton = $(".join-jjoin-btn-border");
    let $must1 = $(".must1");
    let $must2 = $(".must2");

    $joinButton.on("click", function(e) {
        if(emailCheck && nameCheck && phoneNumberCheck && authCodeCheck && passwordCheck1 && passwordCheck2  && must1CheckBox && must2CheckBox) {
            $(document.joinForm).submit();
        } else if(emailCheck && nameCheck && phoneNumberCheck && authCodeCheck && passwordCheck1 && passwordCheck2 && allCheckBox) {
            $(document.joinForm).submit();
        }
    });

    $must1.on("click", function(e) {
        if($must1.is(":checked")) {
            must1CheckBox = true;
        }
    });

    $must2.on("click", function(e) {
        if($must2.is(":checked")) {
            must2CheckBox = true;
        }
    });