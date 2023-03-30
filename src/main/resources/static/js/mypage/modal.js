const $errorMsg = $(".error-msg");

// 이름 변경 모달
const $memberNameModal = $(".member-name-modal");
const $memberNameModalButton = $(".member-name-modal-button");
const $memberNameModalExit = $(".name-modal-exit");

$memberNameModalButton.click(() => {
    $memberNameModal.show();
});

$memberNameModalExit.each((i, e) => {
    $(e).click(() => {
        $("input[name=membername]").val(memberVO.memberName);
        memberNameCheck = false;
        $errorMsg.text("");
        $memberNameModal.hide();
    });
});

// 핸드폰 변경 모달
const $memberPhoneModal = $(".member-phone-modal");
const $memberPhoneModalButton = $(".phone-modal-button");
const $memberPhoneModalExit = $(".phone-modal-exit");

$memberPhoneModalButton.click(() => {
    $memberPhoneModal.show();
});

$memberPhoneModalExit.each((i, e) => {
    $(e).click(() => {
        $(".phoneNumber").text(phoneNumber(memberVO.memberPhoneNumber));
        $("input[name=memberPhoneNumber]").val(memberVO.memberPhoneNumber);
        $("input[name=memberPhoneNumber]").attr("readonly", true);
        $("input[name=memberPhoneNumber]").attr("disabled", true);
        $(".code-check-input").attr("readonly", true);
        $(".code-check-input").val("");
        $errorMsg.text("");
        $(".change-phoneNumber").show();
        $codeButton.hide();
        $memberPhoneModal.hide();
    });
})

/* 파일 모달창 닫기*/
const $fileModal = $(".file-modal-display");
const $fileExitButton = $(".file-modal-exit");
const $fileCancelButton = $(".image-cancel-button");

$fileExitButton.click(function () {
    $fileModal.hide();
});

$fileCancelButton.click(function (e) {
    e.preventDefault();
    $fileModal.hide();
});

// 비밀번호 변경 모달
const $passwordModalButton = $(".password-change-modal");
const $passwordModal = $(".password-modal-display");
const $passwordModalExit = $(".password-modal-exit");
const $passwordCancel = $(".password-cancel");

$passwordModalButton.click(function () {
    $passwordModal.show();
});

$passwordModalExit.each((i, e) => {
    $(e).click(function () {
        $("input[type=password]").val("");
        $errorMsg.text("");
        $passwordModal.hide();
    });
});
