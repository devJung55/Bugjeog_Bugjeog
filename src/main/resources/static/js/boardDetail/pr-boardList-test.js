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
let $allImg = $(".img_size");
$reviewBox.prepend(firstImageImg);
let index = 0; // 슬라이드 인덱스 초기화
let checkArrow = false;
let $temp;
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


$left.click(function() {
    $allImg = $(".img_size");
    if (checkArrow) { return; }
    checkArrow = true;
    $allImg.css("transition", "transform 0.3s");
    $allImg.css("transform", `translate(${-700 * --index}px)`);
    if (index == -1) {
        $temp = $allImg.first().detach();
        $reviewBox.append($temp);
        $allImg = $(".img_size");
        $allImg.css("transition", "transform 0s");
        $allImg.css("transform", `translate(${-700 * 4}px)`);
        setTimeout(() => {
            $temp = $allImg.first().detach();
            $reviewBox.append($temp);
            $allImg = $(".img_size");
            $allImg.css("transition", "transform 0s");
            $allImg.css("transform", `translate(${-700 * 4}px)`);
            index = 3;
        }, 300);
    }
    setTimeout(() => { checkArrow = false }, 300);
});

$right.click(function() {
    $allImg = $(".img_size");
    if (checkArrow) { return; }
    checkArrow = true;
    $allImg.css("transition", "transform 0.3s");
    $allImg.css("transform", `translate(${-700 * ++index}px)`);
    if (index == 5) {
        $temp = $allImg.first().detach();
        $reviewBox.append($temp);
        $allImg = $(".img_size");
        $allImg.css("transition", "transform 0s");
        $allImg.css("transform", `translate(${-700}px)`);
        setTimeout(() => {
            $temp = $allImg.first().detach();
            $reviewBox.append($temp);
            $allImg = $(".img_size");
            $allImg.css("transition", "transform 0s");
            $allImg.css("transform", `translate(${-700}px)`);
            index = 1;
        }, 300);
    }
    setTimeout(() => { checkArrow = false }, 300);
});

