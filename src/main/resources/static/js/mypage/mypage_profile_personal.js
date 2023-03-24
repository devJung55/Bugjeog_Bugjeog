const $likeButtons = $('div.PostItem_like>label');
console.log($likeButtons);

$likeButtons.on('click', (e) => {
    var $img = $(e.target)[0];
    console.log($img);
    if($img.classList.contains('liked')){
        $img.classList.remove('liked');
        $($img).attr('src', '/image/mypage/like_before.png');
    } else {
        $img.classList.add('liked');
        $($img).attr('src', '/static/image/mypage/like_after.png');
    }
});