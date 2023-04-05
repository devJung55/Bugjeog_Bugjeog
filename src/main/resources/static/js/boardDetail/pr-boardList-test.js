function afterDetail() {
     //앞뒤로 붙일 img 태그 만들기
    const lastImageImg = document.createElement("img");
    const firstImageImg = document.createElement("img");
    // 왼쪽 버튼
    const $left = $("#main_img_btn_left");
    // 오른쪽 버튼
    const $right = $("#main_img_btn_right");
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
    let lastImgSrc = $($reviewImage[0]).attr("src");
    console.log($reviewImage);
    let firstImgSrc = $($reviewImage[$reviewImage.length - 1]).attr("src");
    //배너를 감싸고 있는 div의 마지막 자식으로 img태그 추가
    $reviewBox.append();
    //추가한 img태그의 src에 첫번째 data1의 주소값을 넣어줌
    $(lastImageImg).attr("src", `${lastImgSrc}`).addClass("img_size");

    //추가한 img태그의 src에 마지막 자식요소의 주소값을 넣어줌
    $(firstImageImg).attr("src", `${firstImgSrc}`).addClass("img_size");
    // 배너들을 감싸고 있는 div에 width를 구한다음 넣어줌
    $reviewBox.css("width", `${700 * $reviewImage.length + 2}`);
    $reviewBox.prepend(firstImageImg).append(lastImageImg);
    $reviewBox.css("transform", `translate(-700px)`);

    $left.click(function () {
        console.log("왼쪽 버튼");
        $allImg = $(".file-banner-box");
        bannerCount = $allImg.length;
        if (checkArrow) {
            return;
        }
        checkArrow = true;
        $allImg.css("transition", "transform 0.3s");
        $allImg.css("transform", `translate(${-700 * --index}px)`);
        if (index == 0) {
            index = $reviewImage.length;
            setTimeout(() => {
                $allImg.css("transition", "transform 0s");
                $allImg.css("transform", `translate(${-700 * $reviewImage.length}px)`);
            }, 300);
        }
        setTimeout(() => {
            checkArrow = false
        }, 300);
    });

    $right.click(function () {
        console.log("왼쪽 버튼");
        $allImg = $(".file-banner-box");
        if (checkArrow) {
            return;
        }
        checkArrow = true;
        index++;
        $allImg.css("transition", "transform 0.3s");
        $allImg.css("transform", `translate(${-700 * index}px)`);
        console.log(index);
        if (index == ($reviewImage.length+1)) {
            index = 1;
            setTimeout(() => {
                $allImg.css("transition", "transform 0s");
                $allImg.css("transform", `translate(-700px)`);
            }, 300);
        }
        setTimeout(() => {
            checkArrow = false
        }, 300);
    });

}




// const $top = $("#up_btn");
//
// $top.click(function () {
//     $('html, body').animate({scrollTop: 0}, 400);
//     return false;
// });

//        맨위로 버튼
const $modalTop = $(".modal-top-active");
const $modalTopButton = $("#up_btn");
$modalTopButton.click(function () {
    $modalTop.animate({scrollTop: 0}, 400);
    return false;
});
