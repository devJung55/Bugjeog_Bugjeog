/* 카테고리 버튼 이벤트 */
const $cateButton = $(".cate-button");

$cateButton.each((i, e) => {

    $(e).click(function(){
        $cateButton.removeClass("cate-button-active");
        $(e).addClass("cate-button-active");
    });
});

// 버튼을 클릭하면 그 버튼의 value를 val에 담아줌
$('.cate-button').on('click', function () {
    console.log($(this).val());
    $('#catergorys').val($(this).val());
});

$('.loca-button').on('click', function () {
    console.log($(this).val());
    $('#foods').val($(this).val());
});


// 적용하기 버튼 클릭시 categoryValue 와 foodValue 둘중 하나라도 없다면 alert
// $('#submit-button').on('click', function() {
//     var categoryValue = $('#catergorys').val();
//     var foodValue = $('#foods').val();
//
//     console.log(categoryValue);
//     console.log(foodValue);
//     if (!categoryValue || !foodValue) {
//         alert('지역선택 또는 카테고리를 입력해 주세요.');
//         return false; // form submit 막기
//     }else{
//         alert('변경되었습니다.')
//     }
// });
$('#submit-button').on('click', function() {
    var categoryValue = $('#catergorys').val();
    var foodValue = $('#foods').val();

    console.log(categoryValue);
    console.log(foodValue);
    if (!categoryValue || !foodValue) {
        $('.modal_fail').css('display', 'block');
    } else {
        $('.modal_success').css('display', 'block');
    }});

$('.modal_confirm').on('click', function() {
    $("form[name=regionForm]").submit();
});



/* 카테고리 버튼 이벤트 */
const $locaButton = $(".loca-button");

$locaButton.each((i, e) => {

    $(e).click(function(){
        $locaButton.removeClass("loca-button-active");
        $(e).addClass("loca-button-active");
    });
});
