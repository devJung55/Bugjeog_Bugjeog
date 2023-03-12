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