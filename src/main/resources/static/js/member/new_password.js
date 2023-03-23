    const regPassword = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;
    const $password = $("input[name='password']");
    const $passwordError = $(".pw-wrong-num");
    const $passwordCheckError = $(".pw-reCheck");
    const $passwordCheck = $("input[name='passwordConfirm']");

    $password.blur(function(){
        $passwordError.show();
        let passwordVal = $password.val();
        if(!passwordVal){
            $passwordError.text("비밀번호를 입력해주세요.");
            $passwordError.removeClass("font-size");
        }else if(!regPassword.test(passwordVal)){
            $passwordError.addClass("font-size");
            $passwordError.text("최소 8 자, 최소 하나의 문자, 하나의 숫자 및 하나의 특수 문자를 입력해주세요.");
        }else {
            $passwordError.hide();
            $passwordError.text("");
        }
    });

    $passwordCheck.blur(function(){
        $passwordCheckError.show();
        if($password.val() != $passwordCheck.val()){
            $passwordCheckError.text("비밀번호를 확인해주세요.");
        }else {
            $passwordCheckError.hide();
        }
    });
