// 휴대폰 검사
const regPhone = /^010([0|1|6|7|8|9])?([0-9]{3,4})?([0-9]{4})$/;
const $phone = $("input[name='phoneNumber']");
const $phoneError = $(".phone-error");
const $checkButton = $(".find-take-btn");
const $inputs = $("input.find-enter-pn");

let phoneCheck = false;
let authCodeCheck = false;

$phone.keyup(function(){
    let phoneVal = $phone.val();
    $phoneError.show();
    if(!phoneVal){
        $checkButton.removeClass("phone-active");
        $checkButton.css("cursor", "inherit");
        $checkButton.css("border", "");
        $(".find-take-btn-ment").css("opacity", "0.5");
        $phoneError.text("핸드폰 번호를 입력해주세요.");
        phoneCheck = false;
    }else if(!regPhone.test(phoneVal)){
        $checkButton.css("cursor", "inherit");
        $checkButton.css("border", "");
        $(".find-take-btn-ment").css("opacity", "0.5");
        $checkButton.removeClass("phone-active");
        $phoneError.text("올바른 형식이 아닙니다.");
        phoneCheck = false;
    }else {
        $checkButton.css("cursor", "pointer");
        $checkButton.css("border", "1px solid #3366FF");
        $(".find-take-btn-ment").css("opacity", "unset");
        $checkButton.addClass("phone-active");
        $phoneError.hide();
        $phoneError.text("");
        phoneCheck = true;
    }
});

// 인증번호 보내기 클릭 시
$checkButton.on("click", function() {
    $(".authcode-input").addClass("authcode-input-active");
    $(".send-authcode").css("background", "#fff");
    findIdService.sendSMS();
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


/*-------------------- submit 활성화 이벤트 --------------------*/


const $submitButton = $(".find-countinue");

$submitButton.on("click", function(e) {
    if(phoneCheck && authCodeCheck) {
        $(document.findIdForm).attr("action", "/member/findAccount");
        $(document.findIdForm).submit();
    }
});


/*-------------------- 인증하기 버튼 클릭시 이벤트 --------------------*/


const $authCodemsg = $(".cerfing-ment");

$authCheckButton.on("click", function(e) {
    if($(".authcode-input").val() == code) {
        $authCodemsg.css("color", "blue");
        $authCodemsg.html("인증에 성공했습니다.");
        authCodeCheck = true;
    } else {
        $authCodemsg.css("color", "red");
        $authCodemsg.html("인증에 실패했습니다.");
        authCodeCheck = false;
    }
});


/*-------------------- ajax 모듈화 --------------------*/


globalThis.code = "";

let findIdService = (function() {
    function sendSMS() {
        let memberPhoneNumber = $phone.val();

        $.ajax({
            url: "/members/code",
            type: "post",
            data: {"memberPhoneNumber": memberPhoneNumber},
            success: function (result) {
                code = result;
                console.log(code);
            }
        })
        return code;
    }
    return {sendSMS: sendSMS}
})();