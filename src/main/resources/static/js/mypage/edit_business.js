// 비밀번호 변경 모달
const $passwordModalButton = $(".password-change-modal");
const $passwordModal = $(".password-modal-display");
const $passwordModalExit = $(".password-modal-exit");
const $passwordCancel = $(".password-cancel");

$passwordModalButton.click(function(e){
    e.preventDefault();
    $("input[type=password]").val("");
    $(".password-error").text("");
    $(".password-check-error").text("");
    $passwordModal.show();
});

$passwordModalExit.click(function(e){
    e.preventDefault();
    $passwordModal.hide();
});

$passwordCancel.click(function(e){
    e.preventDefault();
    $passwordModal.hide();
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