const $registerButton = $(".board-register-button");

$(window).scroll(function(){
    let scrollY = window.scrollY
    if(scrollY < 100){
        $registerButton.hide();
    }else {
        $registerButton.show();
    }
});