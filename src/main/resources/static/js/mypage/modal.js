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
        $memberPhoneModal.hide();
    });
})

/* 파일 모달창 닫기*/
const $fileModal = $(".file-modal-display");
const $fileExitButton = $(".file-modal-exit");
const $fileCancelButton = $(".image-cancel-button");

$fileExitButton.click(function(){
    $fileModal.hide();
});

$fileCancelButton.click(function(e){
    e.preventDefault();
    $fileModal.hide();
});