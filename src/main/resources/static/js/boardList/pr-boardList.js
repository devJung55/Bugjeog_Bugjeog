    /* 스크롤 이벤트 */
    const $sticky = $(".select-button-box");
    const $top = $(".scroll-top-button");

    $(window).scroll(function(){
        let scrollY = window.scrollY;
        if(scrollY < 390){
            $sticky.removeClass("select-button-box-active");
            $top.hide();
        }else {
            $sticky.addClass("select-button-box-active");
            $top.show();
        }
    });

    /* sort 버튼 이벤트 */
    const $sortButton = $(".sort-button");

    $sortButton.each((i, e) => {
        $(e).click(function(){
            $sortButton.removeClass("sort-button-active");
            $(e).addClass("sort-button-active");
        });
    });


    /* 카테고리 버튼 이벤트 */
    const $cateButton = $(".cate-button");
    
    $cateButton.each((i, e) => {

        $(e).click(function(){
            $cateButton.removeClass("cate-button-active");
            $(e).addClass("cate-button-active");
            $.ajax({
                url: ""
            });
        });
    });

    /* 상단으로 올리는 버튼 */
    $top.click(function(){
        $('html, body').animate({scrollTop:0},400);
	    return false;
    });
