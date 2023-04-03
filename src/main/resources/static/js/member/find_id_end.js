/*-------------------- 메인으로 돌아가기 submit 이벤트 --------------------*/


const $goToMain = $(".find-end-backBorder");

$goToMain.on("click", function(e) {
    $(document.accountForm).attr("action", "/main/");
    $(document.accountForm).submit();
});


/*-------------------- 비밀번호 변경하기 hover 이벤트 --------------------*/


const $emailBox = $(".no-css-wt");
globalThis.memberEmail = "";
globalThis.businessEmail = "";

$emailBox.each((i, v) => {
    $(v).hover(function(e) {
        if($($(v).children().children().children()[1]).css("display") == "none") {
            $(v).children().css("background", "#f8f8f8");
            $($(v).children().children().children()[1]).css("display", "block");
            $($(v).children().children().children()[2]).css("color", "black");
            $($(v).children().children().children()[0]).css("display", "block")
            $($(v).children().children().children()[2]).css("display", "none");
        } else {
            $(v).children().css("background", "#3366FF");
            $(v).children().css("cursor", "pointer");
            $($(v).children().children().children()[1]).css("display", "none");
            $($(v).children().children().children()[0]).css("display", "none")
            $($(v).children().children().children()[2]).css("color", "#fff");
            $($(v).children().children().children()[2]).css("display", "block");
        }
    });
});


/*-------------------- button submit 경로 이벤트 --------------------*/


const $goToChangePasswordButton = $(".go-to-password");

$goToChangePasswordButton.each((i, button) => {
    $(button).on("click", function() {
        let email = $(this).siblings().text().split(": ")[1];
        let $form = $(document.accountForm);

        if($(this).attr("name") == "member") {
            $form.attr("action", "/member/memberPasswordChange");
            $form.append(`<input type='hidden' value="${email}" name='memberEmail'>`);
            $(document.accountForm).submit();
        } else {
            $form.attr("action", "/member/businessPasswordChange");
            $form.append(`<input type='hidden' value="${email}" name='businessEmail'>`);
            $(document.accountForm).submit();
        }
    })
});

/*-------------------- 뒤로가기 --------------------*/

let $goToBack = $(".find-right-arrow");

$goToBack.on("click", function(e) {
    $(document.accountForm).attr("action", "/member/findAccount");
    $(document.accountForm).submit();
});
