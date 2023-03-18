const $searchButton = $(".search_button");
const $searchBar = $(".search_bar");
const $searchClose = $(".search_close");

$searchButton.on("click", function(e) {
    if($searchBar.css("display") == 'none'){
        $searchBar.css("display", "block");
    }
});

$searchClose.on("click", function(e) {
    $searchBar.css("display", "none");
});


/* ---------------- nav 이벤트 ---------------- */

const $navs = $(".aria_label");

$navs.each((i, nav) => {
    $(nav).on("click", function(e) {
        if($(this).hasClass("border_bottom") === true) {
            $(this).removeClass("border_bottom");
        } else {
            $(this).addClass("border_bottom");
        }
    });
    if($(nav).hasClass("border_bottom") === true) {
        console.log("들어옴")
        $(nav).removeClass("border_bottom");
    }
});

