globalThis.uuid;
FileList.prototype.forEach = Array.prototype.forEach;

// 썸네일
$("input[name='file']").on("change", function (e) {
    e.preventDefault();
    const $file = $("input[name=file]")[0].files[0];
    let formData = new FormData();
    formData.append("file", $file);

    $.ajax({
        url: "/myPages/business/upload-file",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        success: function (uuid) {
            globalThis.uuid = uuid;
            if ($file.type.startsWith("image")) {
                $fileModal.show();
                $(".file-modal-image").attr("src", `/mypage/profile/display?fileName=${toStringByFormatting(new Date())}/t_${uuid}_${$file.name}`);
            } else {
                alert("이미지 파일만 넣어주세요.");
            }
        }
    });
});

$(".image-save-button").on("click", function () {
    const $file = $("input[name=file]")[0].files[0];
    let business = new Object();

    business.businessImgOriginalName = $file.name;
    business.businessImgUuid = globalThis.uuid;
    business.businessImgPath = toStringByFormatting(new Date());
    business.businessId = 4;

    $.ajax({
        url: "/myPages/business/file-business-save",
        type: "patch",
        data: JSON.stringify(business),
        contentType: "application/json; charset=utf-8",
        success: function () {
            $fileModal.hide();
            $(".img_profile").attr("src", `/mypages/display?fileName=${toStringByFormatting(new Date())}/${uuid}_${$file.name}`);
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
const $nameInput = $("input[name=businessCeoName]");
let nameCheck = false;
$nameInput.blur(function () {
    if (!$(this).val()) {
        $(".name-error").text("이름을 입력해주세요.");
        nameCheck = false;

    } else if ($(this).val().length < 2) {
        $(".name-error").text("이름은 2자리 이상 입력해주세요.");
        nameCheck = false;

    } else if (businessServiceCheck.businessCeoNameCheck($(this).val())) {
        $(".name-error").text("현재 이름과 동일합니다.");
        nameCheck = false;

    } else {
        $(".name-error").text("");
        nameCheck = true;
    }
});

// 이름 저장
const $nameSaveButton = $(".name-save");

$nameSaveButton.click(function () {
    if (!nameCheck) {
        alert("이름을 확인해주세요.");
        return false;
    }
    let businessCeoName = $("input[name=businessCeoName]").val();
    businessUpdate.businessCeoNameUpdate(businessCeoName);

});

// 전화번호 검사
const regPhone = /^\d{2,3}-?\d{3,4}-?\d{4}$/;
const $phoneError = $(".phone-error");
const $phoneNumber = $("input[name=phoneNumber]");
let phoneCheck = false;
$phoneNumber.blur(function () {
    let phoneVal = $phoneNumber.val().replaceAll("-", "");
    $phoneNumber.val(phoneVal);
    if (!phoneVal) {
        $phoneNumber.css("background-color", "#f2f4f7");
        $phoneError.text("전화 번호를 입력해주세요.");
        phoneCheck = false;
    } else if (!regPhone.test(phoneVal)) {
        $phoneNumber.css("background-color", "#f2f4f7");
        $phoneError.text("올바른 형식이 아닙니다.");
        phoneCheck = false;

    } else if (businessServiceCheck.businessPhoneNumberCheck(phoneVal)) {
        $phoneNumber.css("background-color", "#f2f4f7");
        $phoneError.text("현재 전화 번호와 다른 번호를 입력해주세요.");
        phoneCheck = false;

    } else if (businessServiceCheck.phoneNumberCheck(phoneVal)) {
        $phoneNumber.css("background-color", "#f2f4f7");
        $phoneError.text("중복된 전화 번호입니다.");
        phoneCheck = false;

    } else if (phoneVal.length < 12) {
        $phoneNumber.css("background-color", "#f2f4f7");
        $phoneError.text("전화번호는 11자리 이내로 입력해주세요.");
        phoneCheck = false;

    } else {
        $phoneError.text("");
        phoneCheck = true;
        $phoneNumber.css("background-color", "#fff");

    }
});

// 전화번호 변경완료
const $phoneUpdateButton = $(".phone-save");
$phoneUpdateButton.click(function () {
    let businessPhoneNumber = $("input[name=phoneNumber]").val();
    if (!phoneCheck) {
        alert("전화번호를 확인해주세요.");
        return false;
    }
    businessUpdate.businessPhoneNumberUpdate(businessPhoneNumber);
});

// 회사명 검사
let $companyName = $("#company-name");
let companyNameCheck = false;
let $companyNameError = $(".company-error");
$companyName.blur(() => {

    if (!$companyName.val()) {
        $companyNameError.text("회사명을 입력해주세요.");
        companyNameCheck = false;

    } else if (businessServiceCheck.businessCompanyNameCheck($companyName.val())) {
        $companyNameError.text("현재 회사명 입니다.");
        companyNameCheck = false;

    } else {
        companyNameCheck = true;
        $companyNameError.text("");
    }
});

// 회사명 변경
const $companyNameSave = $(".company-save");
$companyNameSave.click(() => {
    let businessCompanyName = $("#company-name").val();
    if (!companyNameCheck) {
        alert("회사명을 확인해주세요.");
        return false;
    }
    businessUpdate.businessCompanyNameUpdate(businessCompanyName);
});

// 사업자 번호 검사
const regBusinessNumber = /^[0-9]{3}-[0-9]{2}-[0-9]{5}$/;
const $businessNumber = $("#business-number");
let businessNumberCheck = false;
let $businessNumberError = $(".company-error");
$businessNumber.blur(() => {
    let businessNumber = $businessNumber.val();

    if (!businessNumber) {
        $businessNumberError.text("사업자 번호를 입력해주세요.");
        businessNumberCheck = false;

    } else if (!regBusinessNumber.test(businessNumber)) {
        $businessNumberError.text("형식에 맞춰서 입력해주세요.");
        businessNumberCheck = false;

    } else if (businessServiceCheck.businessNumberDuplicationCheck(businessNumber)) {
        $businessNumberError.text("중복된 사업자 번호입니다.");
        businessNumberCheck = false;

    } else if (businessServiceCheck.businessNumberCheck(businessNumber)) {
        $businessNumberError.text("현재 사업자 번호입니다.");
        businessNumberCheck = false;

    } else {
        $businessNumberError.text("");
        businessNumberCheck = true;

    }
});

// 사업자 번호 변경
const $businessNumberSave = $(".business-number-save");

$businessNumberSave.click(() => {
    let businessNumber = $("#business-number").val();
    if (!businessNumberCheck) {
        alert("사업자 번호를 확인해주세요.");
        return false;
    }
    businessUpdate.businessNumberUpdate(businessNumber);

});

// 비밀번호 검사
let passwordCheck = [false, false];
const regPassword = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;
const $newpassword = $("#new-password");
const $newPasswordError = $(".password-error");

$newpassword.blur(function () {
    let password = $(this).val();
    if (!password) {
        $newPasswordError.text("비밀번호를 입력해주세요.");
        passwordCheck[0] = false;
    } else if (!regPassword.test(password)) {
        $newPasswordError.text("최소 8 자, 최소 하나의 문자, 하나의 숫자 및 하나의 특수 문자를 입력해주세요.");
        passwordCheck[0] = false;
    } else {
        passwordCheck[0] = true;
        $newPasswordError.text("");
    }
});

// 비밀번호 재검사
const $passwordCheck = $("#new-password-check");
const $passwordCheckError = $(".password-check-error");

$passwordCheck.blur(function () {
    let password = $(this).val();
    if (!password) {
        $passwordCheckError.text("비밀번호를 입력해주세요.");
        passwordCheck[1] = false;
    } else if (password != $newpassword.val()) {
        $passwordCheckError.text("비밀번호가 일치하지 않습니다.");
        passwordCheck[1] = false;
    } else {
        $passwordCheckError.text("");
        passwordCheck[1] = true;
    }
});

//     비밀번호 변경
const $passwordSave = $(".password-save");

$passwordSave.click(function () {
    let businessPassword = $("#new-password").val();

    if (passwordCheck.filter(check => check == true).length != 2) {
        alert("잘못 입력된 정보가 있습니다.");
        return false;
    }

    businessUpdate.businessPasswordUpdate(businessPassword);
});

/*==========================================================================*/

let businessServiceCheck = (function () {
    // 회원 전화번호 비교
    function businessPhoneNumberCheck(businessPhoneNumber) {
        let check = false;
        $.ajax({
            url: "/myPages/business/businessVO",
            type: "get",
            async: false,
            success: function (businessVO) {
                check = businessVO.businessPhoneNumber == businessPhoneNumber;
            }
        });
        return check;
    }

    // 회원 이름 비교
    function businessCeoNameCheck(businessCeoName) {
        let check = false;
        $.ajax({
            url: "/myPages/business/businessVO",
            type: "get",
            async: false,
            success: function (businessVO) {
                check = businessVO.businessCeoName == businessCeoName;
            }
        });
        return check;
    }

    // 전화번호 중복 검사
    function phoneNumberCheck(phoneNumber) {
        let check = false;
        $.ajax({
            url: "/myPages/business/businessPhoneCheck",
            type: "get",
            data: {"businessPhoneNumber": phoneNumber},
            async: false,
            success: function (phoneNumberCheck) {
                check = phoneNumberCheck;
            }
        });
        return check;
    }

    // 회사명 검사
    function businessCompanyNameCheck(businessCompanyName) {
        let check = false;
        $.ajax({
            url: "/myPages/business/businessVO",
            type: "get",
            async: false,
            success: function (businessVO) {
                check = businessVO.businessCompanyName == businessCompanyName;
            }
        });
        return check;
    }

    // 사업자 번호 검사
    function businessNumberCheck(businessNumber) {
        let check = false;
        $.ajax({
            url: "/myPages/business/businessVO",
            type: "get",
            async: false,
            success: function (businessVO) {
                check = businessVO.businessNumber == businessNumber;
            }
        });
        return check;
    }

    // 사업자 번호 중복 검사
    function businessNumberDuplicationCheck(businessNumber) {
        let check = false;
        $.ajax({
            url: "/myPages/business/businessNumber-check",
            type: "get",
            data: {"businessNumber": businessNumber},
            async: false,
            success: function (result) {
                check = result;
            }
        });
        return check;
    }

    return {
        businessPhoneNumberCheck: businessPhoneNumberCheck,
        businessCeoNameCheck: businessCeoNameCheck,
        phoneNumberCheck: phoneNumberCheck,
        businessCompanyNameCheck: businessCompanyNameCheck,
        businessNumberCheck: businessNumberCheck,
        businessNumberDuplicationCheck: businessNumberDuplicationCheck
    }
})();

let businessUpdate = (function () {

    function businessCeoNameUpdate(businessCeoName) {
        $.ajax({
            url: "/myPages/business/updateBusinessCeoName",
            type: "patch",
            data: {"businessCeoName": businessCeoName},
            success: function (businessCeoName) {
                $(".change-name").text(businessCeoName + "님, 환영해요.");
                $(".businessCeoName").text(businessCeoName);
                $("input[name=businessCeoName]").val(businessCeoName);
                nameCheck = false;
                $(".name-error").text("");
                $ceoNameModal.hide();
            }
        });
    }

    function businessPhoneNumberUpdate(businessPhoneNumber) {
        $.ajax({
            url: "/myPages/business/phoneNumberUpdate",
            type: "patch",
            data: {"businessPhoneNumber": businessPhoneNumber},
            success: function (memberPhoneNumber) {
                $(".phoneNumber").text(phoneNumber(businessPhoneNumber));
                $("input[name=phoneNumber]").val(businessPhoneNumber);
                phoneCheck = false;
                $phoneError.text("");
                $businessModal.hide();
            }
        });
    }

    function businessCompanyNameUpdate(businessCompanyName) {
        $.ajax({
            url: "/myPages/business/companyName-update",
            type: "patch",
            data: {"businessCompanyName": businessCompanyName},
            success: function (businessCompanyName) {
                $("#company-name").val(businessCompanyName);
                $(".companyName").text(businessCompanyName);
                $companyNameError.text("");
                companyNameCheck = false;
                $companyModal.hide();
            }
        });
    }

    function businessNumberUpdate(businessNumber) {
        $.ajax({
            url: "/myPages/business/businessNumber-update",
            type: "patch",
            data: {"businessNumber": businessNumber},
            success: function (businessNumber) {
                $businessNumber.val(businessNumber);
                $(".business-number").text(businessNumber);
                $businessNumberError.text("");
                businessNumberCheck = false;
                $businessNumberModal.hide();
            }
        });
    }

    function businessPasswordUpdate(businessPassword) {
        $.ajax({
            url: "/myPages/business/businessPassword-update",
            type: "patch",
            data: {"businessPassword": btoa(businessPassword)},
            success: function () {

                $passwordModal.hide();
            }
        });
    }

    return {
        businessCeoNameUpdate: businessCeoNameUpdate,
        businessPhoneNumberUpdate: businessPhoneNumberUpdate,
        businessCompanyNameUpdate: businessCompanyNameUpdate,
        businessNumberUpdate: businessNumberUpdate,
        businessPasswordUpdate: businessPasswordUpdate
    }
})();