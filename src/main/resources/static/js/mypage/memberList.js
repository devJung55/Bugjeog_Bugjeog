let $pageForm = $(pageForm);

$("a.changePage").on("click", function (e) {
    e.preventDefault();
    $pageForm.find("input[name='pageNum']").val($(this).attr("href"));
    $pageForm.submit();
});

if (memberVO.memberImgUuid) {
    $(".img_profile").attr("src", `/mypage/profile/display?fileName=${memberVO.memberImgPath}/${memberVO.memberImgUuid}_${memberVO.memberImgOriginalName}`);
}

$(".phoneNumber").text(phoneNumber(memberVO.memberPhoneNumber));

function phoneNumber(phoneNumber) {
    var phone1 = phoneNumber.substring(0, 3);
    var phone2 = phoneNumber.substring(3, 7);
    var phone3 = phoneNumber.substring(7);
    let phone = phone1 + "-" + phone2 + "-" + phone3;
    return phone;
}

let $date = $(".AuthorBox_createAt");
$(document).ready(function () {
    $date.each((i, e) => {
        $(e).text(date($(e).text()));
    });
});

function date(date) {
    let registerDate = new Date(date);
    return registerDate.getFullYear() + "." + (registerDate.getMonth() + 1) + "." + registerDate.getDate();
}

