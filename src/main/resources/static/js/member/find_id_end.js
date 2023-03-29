/*-------------------- 메인으로 돌아가기 submit 이벤트 --------------------*/


const $goToMain = $(".find-end-backBorder");

$goToMain.on("click", function(e) {
    $(document.accountForm).attr("action", "/main/");
    $(document.accountForm).submit();
});


/*-------------------- 비밀번호 찾으러 가기 hover 이벤트 --------------------*/


const $emailBox = $(".no-css-wt");
const $pText = $(".find-end-emailWrite");

$emailBox.each((i, v) => {
    $(v).hover(function(e) {
        $($(v).children().children().children()[0]).remove();
        console.log($($(v).children().children().children()[1]));
        console.log("들어옴");
    });
});