const $writeButton = $(".write-button");
const $registerButton = $(".board-register-button");
const $modal = $("#modal");
const $modalExitButton = $(".modal-confirm-no-button");

$writeButton.click(function(){
    $modal.show();
});

$modalExitButton.click(function(){
    $modal.hide();
});

$registerButton.click(function(){
    $modal.show();
});

$(window).scroll(function(){
    let scrollY = window.scrollY
    if(scrollY < 100){
        $registerButton.hide();
    }else {
        $registerButton.show();
    }
});
