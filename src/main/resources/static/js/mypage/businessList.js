let $pageForm = $(pageForm);

$("a.changePage").on("click", function(e){
    e.preventDefault();
    $pageForm.find("input[name='pageNum']").val($(this).attr("href"));
    $pageForm.submit();
});

if(businessVO.businessImgUuid){
    $(".img_profile").attr("src",`/mypage/profile/display?fileName=${businessVO.businessImgPath}/${businessVO.businessImgUuid}_${businessVO.businessImgOriginalName}`);
}

$(".phoneNumber").text(phoneNumber(businessVO.businessPhoneNumber));

function phoneNumber(phone){

    let phoneNumber;
    if(phone.startsWith("02")){
        phoneNumber = phone.substring(0,2) + "-" +phone.substring(2,6) + "-" + phone.substring(6);
    }else if(phone.startsWith("031")){
        phoneNumber = phone.substring(0,3) + "-" +phone.substring(3,6) + "-" + phone.substring(6);
    }else {
        phoneNumber = phone.substring(0,3) + "-" +phone.substring(3,7) + "-" + phone.substring(7);
    }
    return phoneNumber;
}

function date(date){
    let registerDate = new Date(date);
    return registerDate.getFullYear() +"."+(registerDate.getMonth() + 1) + "." + registerDate.getDate();
}

let $date = $(".AuthorBox_createAt");
$(document).ready(function(){
    $date.each((i, e) => {
        $(e).text(date($(e).text()));
    });
});