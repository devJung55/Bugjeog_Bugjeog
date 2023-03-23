const $btnSave = $('button.modal_save');
const $btnAuthNums = $($('button.auth_number')[0]);
const $inputPhone = $('input.modal_input.column');
const editPhoneBtnText = $('span.edit_phone_number')[0];
const $editPhoneBtnText = $(editPhoneBtnText);
const $modalCloseBtns = $('button.modal_cancel');

const $authSendMsg = $($(".auth_msg_done")[0]);
const authNumSededText = "인증번호가 요청되었습니다.";

const $authRemainTimeMsg = $($('.auth_remain_time')[0]);
const authTimeMsg = "유효시간";

const sendAuthText = "인증번호 받기";
const plzEnterAuth = "인증번호 입력";
const editPhoneText = "번호 변경";

$btnAuthNums.on('click', (e) => {
    console.log($editPhoneBtnText.text().trim() == "번호 변경");
    if($btnAuthNums.hasClass('btn_active')){
        switch($editPhoneBtnText.text().trim()){
            case "번호 변경":
                $btnAuthNums.removeClass('btn_active');
                $btnAuthNums.attr('disabled', 'disabled');
                $editPhoneBtnText.text(sendAuthText);
                $phone.removeAttr('readonly');
                addHidden($authSendMsg);
                break;
            case sendAuthText:
                // 인증번호 전송 서비스 필요
                $editPhoneBtnText.text("인증번호 재전송");
                $btnAuthNums.removeClass('btn_active');
                $btnAuthNums.attr('disabled', 'disabled');
                $phone.attr('readonly', 'readonly');
                removeHidden($authSendMsg);
                $authSendMsg.text(authNumSededText);//  인증번호가 요청되었습니다.
                // $.ajax({
                //     success:{
                //         $authRemainTimeMsg.text(authTimeMsg + "");// 유효시간 4:00
                //     }
                // });
                break;
            
        }
    }
    // if ($btnAuthNums.hasClass('btn_active') & $editPhoneBtnText.text().trim() == "번호 변경") {
    //     $btnAuthNums.removeClass('btn_active');
    //     $btnAuthNums.attr('disabled', 'disabled');
    //     $editPhoneBtnText.text(sendAuthText);
    //     $phone.removeAttr('readonly');
    //     addHidden($authSendMsg);
    // }

    // if ($btnAuthNums.hasClass('btn_active') & $editPhoneBtnText.text().trim() == sendAuthText) {
    //     // 인증번호 전송 서비스 필요
    //     $editPhoneBtnText.text("인증번호 재전송");
    //     $btnAuthNums.removeClass('btn_active');
    //     $btnAuthNums.attr('disabled', 'disabled');
    //     $phone.attr('readonly', 'readonly');
    //     removeHidden($authSendMsg);
    //     $authSendMsg.text(authNumSededText);//  인증번호가 요청되었습니다.
    //     // $.ajax({
    //     //     success:{
    //     //         $authRemainTimeMsg.text(authTimeMsg + "");// 유효시간 4:00
    //     //     }
    //     // });
    // }
});

$($modalCloseBtns).on('click', (e) => {
    // var thisBtn = e.currentTarget;
    // console.log($(e.currentTarget));
    // console.log($(this));
    console.log($('button.modal-close')[0]);
    $('div>button.modal-close.is-large').click();
});



function addHidden(target) {
    if ($(target).hasClass('is-hidden')) {
        $(target).addClass('is-hidden');
    }
}

function removeHidden(target) {
    if (!$(target).hasClass('is-hidden')) {
        $(target).removeClass('is-hidden');
    }
}