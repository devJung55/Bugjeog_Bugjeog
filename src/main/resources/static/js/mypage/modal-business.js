const errorMsg = $(".error-msg");

// 파일 모달창 닫기
const $fileModal = $(".file-modal-display");
const $fileExitButton = $(".file-modal-exit");

$fileExitButton.each((i, e) => {
    $(e).click(() => {
        $fileModal.hide();
    });
});

// 회사명 변경 모달
const $companyModalButton = $(".company-modal-open");
const $companyModal = $(".company-modal-display");
const $companyModalExit = $(".company-modal-exit");
$companyModalButton.click(function(){
    $companyModal.show();
});

$companyModalExit.each((i, e) => {
    $(e).click(function(){
        companyNameCheck = false;
        errorMsg.text("");
        $("#company-name").val(businessVO.businessCompanyName);
        $companyModal.hide();
    });
});

// 대표자명 변경 모달
const $ceoNameModal = $(".ceoName-modal");
const $ceoNameModalButton = $(".ceoName-modal-button");
const $ceoNameModalExit = $(".ceoName-exit");
$ceoNameModalButton.click(function(){
    $ceoNameModal.show();
});

$ceoNameModalExit.each((i , e) => {
    $(e).click(function(e){
        $("input[name=businessCeoName]").val(businessVO.businessCeoName);
        errorMsg.text("");
        nameCheck = false;
        $ceoNameModal.hide();
    });
})

// 대표 전화번호 변경 모달
const $businessModal = $(".business-phone-modal");
const $businessModalButton = $(".business-phone-modal-button");
const $businessModalExit = $(".business-phone-exit");

$businessModalButton.click(() => {
    $businessModal.show();
});

$businessModalExit.each((i, e) => {
    $(e).click(() => {
        $("input[name=phoneNumber]").val(businessVO.businessPhoneNumber);
        errorMsg.text("");
        phoneCheck = false;
        $businessModal.hide();
    });
});

// 사업자 번호 변경 모달
const $businessNumberModal = $(".business-number-modal-display");
const $businessNumberModalButton = $(".business-number-modal-button");
const $businessNumberModalExit = $(".business-number-modal-exit");

$businessNumberModalButton.click(() => {
    $businessNumberModal.show();
});

$businessNumberModalExit.each((i, e) => {
    $(e).click(() => {
        $("#business-number").val(businessVO.businessNumber);
        businessNumberCheck =false;
        errorMsg.text("");
        $businessNumberModal.hide();
    });
});

// 비밀번호 변경 모달
const $passwordModalButton = $(".password-change-modal");
const $passwordModal = $(".password-modal-display");
const $passwordModalExit = $(".password-modal-exit");

$passwordModalButton.click(function(e){
    $("input[type=password]").val("");
    $(".password-error").text("");
    $(".password-check-error").text("");
    $passwordModal.show();
});

$passwordModalExit.each((i, e) => {
    $(e).click(() => {
        $passwordModal.hide();
    });
});