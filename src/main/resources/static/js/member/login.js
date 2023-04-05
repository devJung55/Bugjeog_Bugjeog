// 이메일 정규식
const $email = $("input[name=email]");
const $errorMessageEmail = $(".error-message-email");

let $emailValid=false;

function emailCheck(){
    $email.on("blur", function(e){
        var emailValue = $email.val(); 
        var rgbEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

        if(emailValue.length == 0){    // 빈문자열이 들어왔을 때
            $errorMessageEmail.css("display", "block");
            $errorMessageEmail.css("color", "red");
            $errorMessageEmail.text("이메일을 입력해주세요.");
            return false;
        } else if(rgbEmail.test(emailValue)){
            $errorMessageEmail.css("display", "none");
            return true;
        } else {
            $errorMessageEmail.css("display", "block");
            $errorMessageEmail.css("color", "red"); // 올바른 이메일 형식이 아닐 때
            $errorMessageEmail.text("이메일 형식으로 입력해주세요.");
            return false;
        }
    });
}


// 비밀번호 
const $password = $("input[type=password]");
const $errorMessagePassword = $(".error-message-password");
const $loginBt = $('.form-button-countinueEmail');

function passwordCheck(){
    $password.on("blur", function(e){
        var passwordValue = $password.val(); 

        if(passwordValue.length == 0){    // 빈문자열이 들어왔을 때
            $errorMessagePassword.css("display", "block");
            $errorMessagePassword.css("color", "red");
            $errorMessagePassword.text("비밀번호를 입력해주세요.");
        } else {
            $errorMessagePassword.css("display", "none");
        }
    });
}

const $loginButton = $(".form-button-countinueEmail");
const $id=$(".id-input");
const $pw=$(".password-input");
let check1=false;
let check2=false;



// 아이디 입력 확인
$id.change(function(){
    if($id.val()!=""){
        check1=true;
        if(!emailCheck()){
            // alert("here")
            $loginBt.attr("disabled", true);
            return;
        };
        if(check2 ){
            $loginBt.removeAttr("disabled");
        }
        return;
    }
    $loginBt.attr("disabled", true);
    check1=false;
})


// 비번 입력 확인
$pw.change(function(){
    if($pw.val()!=""){
        check2=true;
        if(check1){
            $loginBt.removeAttr("disabled");
        }
        // if(!emailCheck()){
        //     $loginBt.attr("disabled", true);
        //     return;
        // };
        passwordCheck();
        return;
    }
    $loginBt.attr("disabled", true);

    check2=false;
})


/*-------------------------- 회원가입 a href 이벤트 --------------------------*/


const $signUpButton = $(".sign-up");

$signUpButton.on("click", function(e) {
    $(this).attr("href", flag == "개인" ? "/member/join" : "/member/business-join");
});


/*-------------------------- 로그인 form submit 이벤트 --------------------------*/


$loginBt.on("click", function(e) {
   $(document.loginForm).attr("action", flag == "개인" ? "/member/login" : "/member/business-login");
   $(document.loginForm).submit();
});


/*-------------------------- 로그인 form 분기 처리  --------------------------*/


const $whichForm = $(".private-business");
globalThis.flag = "개인";

$whichForm.each((i, v) => {
    $(v).on("click", function(e) {
        if($(v).text() == "개인") {
            flag = "개인";
        } else {
            flag = "사업자";
        }
    })
});