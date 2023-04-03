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
        }
    });

    $passwordCheck.blur(function(){
        $passwordCheckError.show();
        if($password.val() != $passwordCheck.val()){
            $passwordCheckError.text("비밀번호를 확인해주세요.");
            passwordCheck2 = false;
        }else {
            $passwordCheckError.hide();
            passwordCheck2 = true;
        }
    });

    const $button = $(".pw-countinue-border");

    $button.on("click", function(e) {
        let $form = $(document.passwordForm);
        let memberPassword = $("input[name=memberPassword]").val();
        if(passwordCheck1 && passwordCheck2) {
            $form.attr("action", "/member/memberPasswordChange?memberEmail=" + memberEmail + "&memberPassword=" + memberPassword);
            $form.submit();
        }
    });

    const $goToLogin = $(".pw-left-box");

    $goToLogin.on("click", function(e) {
        const $form = $(document.passwordForm);

        $form.attr("action", "/member/login");
        $form.attr("method", "get");
        $form.submit();
    });