//앞뒤로 붙일 img 태그 만들기
const lastImageImg = document.createElement("img");
const firstImageImg = document.createElement("img");
// 왼쪽 버튼
const $left = $("#left_arrow");
// 오른쪽 버튼
const $right = $("#right_arrow");
// 해당 div에 추가된 이미지들 전부 가져옴
let $reviewImage = $(".img_size");
// 해당 이미지들을 감싸고 있는 div ()
let $reviewBox = $(".file-banner-box");
// 슬라이드 카운트 세기 위함
let bannerCount;
let $allImg = $(".img_size");
$reviewBox.prepend(firstImageImg);
let index = 1; // 슬라이드 인덱스 초기화
let checkArrow = false;
let data1 = $($reviewImage[0]).attr("src");
let data2 = $($reviewImage[2]).attr("src");

//배너를 감싸고 있는 div의 마지막 자식으로 img태그 추가
$reviewBox.append(lastImageImg);
//추가한 img태그의 src에 첫번째 data1의 주소값을 넣어줌
$(lastImageImg).attr("src", `${data1}`);
//review-active를 넣어서 갯수 세기 위함
$(lastImageImg).addClass("img_size");

//추가한 img태그의 src에 마지막 자식요소의 주소값을 넣어줌
$(firstImageImg).attr("src", `${data2}`);

$(firstImageImg).addClass("review-active");
// 배너들을 감싸고 있는 div에 width를 구한다음 넣어줌
$reviewBox.css("width", `${700 * 5}`);
$reviewBox.prepend(firstImageImg);
$reviewBox.append(lastImageImg);
$(firstImageImg).addClass("img_size");
$(lastImageImg).addClass("img_size");

$reviewBox.css("transform" , `translate(-700px)`);


$left.click(function() {
    $allImg = $(".file-banner-box");
    bannerCount = $allImg.length;
    if (checkArrow) { return; }
    checkArrow = true;
    $allImg.css("transition", "transform 0.3s");
    $allImg.css("transform", `translate(${-700 * --index}px)`);
    if (index == 0) {
        index = 3;
        setTimeout(() => {
            $allImg.css("transition", "transform 0s");
            $allImg.css("transform", `translate(${-700 * 3}px)`);
        }, 300);
    }
    setTimeout(() => { checkArrow = false }, 300);
});

$right.click(function() {
    $allImg = $(".file-banner-box");
    if (checkArrow) { return; }
    checkArrow = true;
    index++;
    $allImg.css("transition", "transform 0.3s");
    $allImg.css("transform", `translate(${-700 * index}px)`);
    console.log(index);
    if (index == 4) {
        index = 1;
        setTimeout(() => {
            $allImg.css("transition", "transform 0s");
            $allImg.css("transform", `translate(-700px)`);
        }, 300);
    }
    setTimeout(() => { checkArrow = false }, 300);
});

$(document).ready(function () {
    // 등록 버튼 초기 색상 설정
    $('button[type="submit"]').css("color", "rgb(196, 196, 196)").css("background-color", "rgb(242, 244, 247)");

    // textarea 입력시 등록 버튼 색상 변경
    $('#reply_textarea').on('input', function () {
        if ($(this).val().length > 0) {
            $('button[type="submit"]').css("color", "white").css("background-color", "blue");
        } else {
            $('button[type="submit"]').css("color", "rgb(196, 196, 196)").css("background-color", "rgb(242, 244, 247)");
        }
    });
});

// const $top = $("#up_btn");
//
// $top.click(function () {
//     $('html, body').animate({scrollTop: 0}, 400);
//     return false;
// });

//       맨위로 버튼
const $modalTop = $(".modal-top-active");
const $modalTopButton = $("#up_btn");
$modalTopButton.click(function () {
    $modalTop.animate({scrollTop: 0}, 400);
    return false;
});

$(document).ready(function () {
    // 등록 버튼 초기 색상 설정
    $('button[type="submit"]').css("color", "rgb(196, 196, 196)").css("background-color", "rgb(242, 244, 247)");

    // textarea 입력시 등록 버튼 색상 변경
    $('#reply_textarea').on('input', function () {
        if ($(this).val().length > 0) {
            $('button[type="submit"]').css("color", "white").css("background-color", "blue");
        } else {
            $('button[type="submit"]').css("color", "rgb(196, 196, 196)").css("background-color", "rgb(242, 244, 247)");
        }
    });
});