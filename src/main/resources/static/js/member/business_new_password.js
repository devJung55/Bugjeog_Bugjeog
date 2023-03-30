    const regPassword = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;
    const $password = $("input[name='password']");
    const $passwordError = $(".pw-wrong-num");
    const $passwordCheckError = $(".pw-reCheck");
    const $passwordCheck = $(".passwordCheck");

    let passwordCheck1 = false;
    let passwordCheck2 = false;

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
            console.log(passwordCheck1)
        }
    });

    $passwordCheck.blur(function(){
        $passwordCheckError.show();
        if($password.val() != $passwordCheck.val()){
            $passwordCheckError.text("비밀번호를 확인해주세요.");
            passwordCheck2 = false;
            console.log(passwordCheck2)
        }else {
            $passwordCheckError.hide();
            passwordCheck2 = true;
            console.log(passwordCheck2)
        }
    });

    const $button = $(".pw-countinue-border");

    $button.on("click", function(e) {
        let $form = $(document.businessPasswordForm);
        let businessPassword = $("input[name=businessPassword]").val();
        if(passwordCheck1 && passwordCheck2) {
            $form.attr("action", "/member/businessPasswordChange?businessEmail=" + businessEmail + "&businessPassword=" + businessPassword);
            $form.submit();
        }
    });