globalThis.uuid;
FileList.prototype.forEach = Array.prototype.forEach;

// 썸네일
$("input[name='file']").on("change", function(e){
    e.preventDefault();
    const $file = $("input[name=file]")[0].files[0];
    let formData = new FormData();
    formData.append("file", $file);

    $.ajax({
        url: "/mypage/profile/upload-file",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        success: function(uuid) {
            globalThis.uuid = uuid;
            if($file.type.startsWith("image")){
                $fileModal.show();
                $(".file-modal-image").attr("src", `/mypage/profile/display?fileName=${toStringByFormatting(new Date())}/t_${uuid}_${$file.name}`);
            }else{
                alert("이미지 파일만 넣어주세요.");
            }
        }
    });
});

$(".image-save-button").on("click", function(){
    const $file = $("input[name=file]")[0].files[0];
    let member = new Object();

    member.memberImgOriginalName = $file.name;
    member.memberImgUuid = globalThis.uuid;
    member.memberImgPath = toStringByFormatting(new Date());
    member.memberId = 1;
    $.ajax({
        url: "/mypage/profile/file-memeber-save",
        type: "patch",
        data: JSON.stringify(member),
        contentType: "application/json; charset=utf-8",
        success: function(){
            $fileModal.hide();
            $(".img_profile").attr("src",`/mypage/profile/display?fileName=${toStringByFormatting(new Date())}/${uuid}_${$file.name}`);
        }
    });
});


// ========================================================
function leftPad(value) {
    if (value >= 10) {
        return value;
    }

    return `0${value}`;
}

function toStringByFormatting(source, delimiter = '/') {
    const year = source.getFullYear();
    const month = leftPad(source.getMonth() + 1);
    const day = leftPad(source.getDate());

    return [year, month, day].join(delimiter);
}

// ========================================================

// 이름 변경 검사
const $nameInput = $("input[name=membername]");
let memberNameCheck = false;
$nameInput.blur(function(){
    if(!$(this).val()){
        $(".name-error").text("이름을 입력해주세요.");
        memberNameCheck = false;

    }else if($(this).val().length < 2 || $(this).val().length > 4){
        $(".name-error").text("이름은 2자리 이상 4자리 이하로 입력해주세요.");
        memberNameCheck = false;

    }else if(memberServiceCheck.memberNameCheck($(this).val())){
        $(".name-error").text("현재 이름과 동일합니다.");
        memberNameCheck = false;

    }else {
        $(".name-error").text("");
        memberNameCheck = true;
    }
});

// 이름 저장
const $nameSaveButton = $(".name-save");

$nameSaveButton.click(function(){
    if(!memberNameCheck){
        alert("이름을 확인해주세요.");
        return false;
    }
    let memberName = $("input[name=membername]").val();

    memberUpdate.memberNameUpdate(memberName);
});

// 번호 변경 클릭시 input태그 활성화
const $changeButton = $(".change-phoneNumber");
$changeButton.click(() => {
    $("input[name=memberPhoneNumber]").removeAttr("disabled");
    $("input[name=memberPhoneNumber]").removeAttr("readonly");
    $("input[name=memberPhoneNumber]").val("");
    $changeButton.hide();
    $(".code-button").show();
});

// 휴대폰 번호 검사
const regPhone = /^010([0|1|6|7|8|9])?([0-9]{4})?([0-9]{4})$/;
const $memberPhone = $("input[name=memberPhoneNumber]");
const $phoneError = $(".phone-error");
const $codeButton = $(".code-button");
let memberPhoneCheck = false;

$memberPhone.blur(function(){
    let memberPhoneNumber = $memberPhone.val().replaceAll("-","");
    $memberPhone.val(memberPhoneNumber);

    if(!memberPhoneNumber){
        $codeButton.removeClass("button-active");
        $codeButton.attr("disabled", true);
        $phoneError.text("핸드폰 번호를 입력해주세요.");
        memberPhoneCheck = false;


    }else if(memberPhoneNumber.length > 11){
        $codeButton.removeClass("button-active");
        $codeButton.attr("disabled", true);
        $phoneError.text("올바른 형식이 아닙니다.");
        memberPhoneCheck = false;

    } else if(!regPhone.test(memberPhoneNumber)){
        $codeButton.removeClass("button-active");
        $codeButton.attr("disabled", true);
        $phoneError.text("올바른 형식이 아닙니다.");
        memberPhoneCheck = false;

    }else if(memberServiceCheck.memberPhoneCheck(memberPhoneNumber)){
        $codeButton.removeClass("button-active");
        $codeButton.attr("disabled", true);
        $phoneError.text("현재 핸드폰 번호와 다른 번호를 입력해주세요.");
        memberPhoneCheck = false;


    }else if(memberServiceCheck.phoneNumberCheck(memberPhoneNumber)){
        $codeButton.removeClass("button-active");
        $codeButton.attr("disabled", true);
        $phoneError.text("중복된 핸드폰 번호입니다.");
        memberPhoneCheck = false;

    }  else {
        $codeButton.addClass("button-active");
        $codeButton.attr("disabled", false);
        $phoneError.text("");
        memberPhoneCheck = true;

    }
});

// 인증번호 보내기 클릭 시
$(".code-button").click(function(){
    let memberPhoneNumber = $("input[name=memberPhoneNumber]").val();
    if(!memberPhoneCheck){
        alert("핸드폰 번호를 확인해주세요.");
        return false;
    }
    $(".code-check-input").removeAttr("readonly");
    $codeButton.removeClass("button-active");
    $codeButton.attr("disabled", true);
    memberServiceCheck.authSend(memberPhoneNumber);
});

// 인증번호 검사
const $codeInput = $(".code-check-input");
let codeCheck = false;

$codeInput.keyup(function(){
    if(!$codeInput.val()){
        $(".auth-msg").css("color","red");
        $(".auth-msg").text("인증번호를 입력해주세요.")
        codeCheck = false;

    }else if($codeInput.val() != globalThis.code){
        $(".auth-msg").css("color","red");
        $(".auth-msg").text("인증번호가 일치하지 않습니다.");
        codeCheck = false;

    }else {
        $(".auth-msg").css("color","#36f");
        $(".auth-msg").text("인증번호가 일치합니다.");
        codeCheck = true;

    }
});

// 휴대폰 번호 변경
const $phoneUpdateButton = $(".phone-save");
$phoneUpdateButton.click(function(){
    if(!codeCheck){
        alert("인증번호를 확인해주세요.");
        return false;
    }
    let memberPhoneNumber = $("input[name=memberPhoneNumber]").val();

    memberUpdate.memberPhoneNumberUpdate(memberPhoneNumber);
});

// 비밀번호 검사
let passwordCheck = [false, false];
const regPassword = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;
const $newpassword = $("#new-password");
const $newPasswordError = $(".password-error");

$newpassword.blur(function(){
    let memberPassword = $(this).val();

    if(!memberPassword){
        $newPasswordError.text("비밀번호를 입력해주세요.");
        passwordCheck[0] = false;

    }else if(!regPassword.test(memberPassword)){
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

$passwordCheck.blur(function () {
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

$passwordSave.click(function(){
    let memberPassword = $("#new-password").val();
    if(passwordCheck.filter(check => check == true ).length != 2){
        alert("잘못 입력된 정보가 있습니다.");
        return false;
    }
    memberUpdate.memberPasswordUpdate(memberPassword);
});

/*=================================================================*/
globalThis.code;

let memberServiceCheck = (function(){
    // 회원 현재 이름 비교
    function memberNameCheck(memberName){
        let check = false;
        $.ajax({
            url : "/mypage/profile/memberVO",
            type: "get",
            async : false,
            success : function (memberVO) {
                check = memberVO.memberName == memberName;
            }
        });
        return check;
    }

    // 회원 현재 전화번호 비교
    function memberPhoneCheck(memberPhoneNumber){
        let check = false;
        $.ajax({
            url : "/mypage/profile/memberVO",
            type: "get",
            async : false,
            success : function (memberVO) {
                check = memberVO.memberPhoneNumber == memberPhoneNumber;
            }
        });
        return check;
    }

    // 핸드폰 중복검사
    function phoneNumberCheck(memberPhoneNumber){
        let check = false;
        $.ajax({
            url : "/mypage/profile/memberPhoneCheck",
            type: "get",
            data : {"memberPhoneNumber" : memberPhoneNumber},
            async : false,
            success : function(phoneNumberCheck){
                check = phoneNumberCheck;
            }
        });
        return check;
    }

    // 인증번호 발송
    function authSend (memberPhoneNumber) {
        $.ajax({
            url: "/mypage/profile/code",
            type : "post",
            data : {"memberPhoneNumber" : memberPhoneNumber},
            success : function(code){
                console.log(code);
                $(".auth-msg").css("color", "#36f");
                $(".auth-msg").text("인증번호를 발송했습니다.");
                globalThis.code = code;
            }
        });
    }

    return { memberNameCheck : memberNameCheck, memberPhoneCheck : memberPhoneCheck, phoneNumberCheck : phoneNumberCheck, authSend : authSend }
})();

let memberUpdate = (function(){

    function memberNameUpdate(memberName){
        $.ajax({
            url : "/mypage/profile/updateName",
            type: "patch",
            data: {"memberName" : memberName},
            success : function (memberName) {
                $(".change-name").text(memberName + "님, 환영해요.");
                $(".memberName").text(memberName);
                $("input[name=username]").val(memberName);
                $memberNameModal.hide();
            }
        });
    }

    function memberPhoneNumberUpdate (memberPhoneNumber) {
        $.ajax({
            url : "/mypage/profile/phoneNumberUpdate",
            type: "patch",
            data: {"memberPhoneNumber" : memberPhoneNumber},
            success : function (memberPhoneNumber) {
                $(".phoneNumber").text(phoneNumber(memberPhoneNumber));
                $("input[name=memberPhoneNumber]").val(memberPhoneNumber);
                $("input[name=memberPhoneNumber]").attr("readonly", true);
                $("input[name=memberPhoneNumber]").attr("disabled", true);
                $(".code-check-input").attr("readonly", true);
                $(".code-check-input").val("");
                $(".auth-msg").text("");
                $(".change-phoneNumber").show();
                $codeButton.hide();
                $memberPhoneModal.hide();
            }
        });
    }

    function memberPasswordUpdate(memberPassword){
        $.ajax({
            url : "/mypage/profile/updatePassword",
            type : "patch",
            data : { "memberPassword" :btoa(memberPassword)},
            success : function(){
                $("input[type=password]").val("");
                $passwordModal.hide();
            }
        });
    }

    return {memberNameUpdate : memberNameUpdate,memberPhoneNumberUpdate : memberPhoneNumberUpdate, memberPasswordUpdate :memberPasswordUpdate }
})();
