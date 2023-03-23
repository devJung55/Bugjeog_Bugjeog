/* ------------------------ 메인 슬라이더 배너 ------------------------  */

HTMLCollection.prototype.forEach = Array.prototype.forEach;
const banner = document.querySelector("div.slick_track");
const banner1 = document.querySelector("a.banner1");
const banner2 = document.querySelector("a.banner2");
const imageDiv = document.querySelectorAll("div.image_image a img");
const lastImageDiv = document.createElement("img");
const firstImageDiv = document.createElement("img");
const next = document.querySelector(".top_banner_next_arrow");
const prev = document.querySelector(".top_banner_prev_arrow");
const buttons = document.querySelectorAll(".buttons button");
let checkArrow = false;
let count = 1;
let auto = setInterval(autoSlide, 2000);
let temp = buttons[0];
let pageCount = document.querySelector("#count-length");

HTMLCollection.prototype.forEach = Array.prototype.forEach;
buttons.forEach(button => {
    button.addEventListener("click", () => {
        clearInterval(auto);
        count = parseInt(button.innerHTML);
        /* changeButtonStyle(); */
        banner.style.transition = "transform 0.3s";
        banner.style.transform = `translate(${-1084 * count}px)`;
        auto = setInterval(autoSlide, 2000);
    });
});

imageDiv.forEach((div, i) => div.setAttribute("src",`/image/main/banner${i+1}.png`));

banner1.appendChild(lastImageDiv);
lastImageDiv.setAttribute("src",`/image/main/banner6.png`);

banner2.insertBefore(firstImageDiv, document.querySelector("div.banner div"));
firstImageDiv.setAttribute("src",`/image/main/banner1.png`);

banner.style.transform = `translate(-1084px)`;

/* function changeButtonStyle() {
    if(temp){
        temp.style.background = "white";
        temp.style.color = "black";
        
    }
    // buttons[count - 1].style.background = "black";
    buttons[count - 1].style.color = "white";
    temp = buttons[count - 1];
    
} */



function autoSlide(){
    banner.style.transition = "transform 0.3s";
    banner.style.transform = `translate(${-1084 * ++count}px)`;
    console.log(count);
    if(count == 7) {
        count = 1;
        setTimeout(function(){
            banner.style.transition = "transform 0s";
            banner.style.transform = "translate(-1084px)";
        }, 300);
    }
    pageCount.innerHTML =`${count} / ${imageDiv.length}`;
    /* changeButtonStyle(); */
}

prev.addEventListener("click", function(){
    if(checkArrow){return;}
    checkArrow = true;
    clearInterval(auto);
    banner.style.transition = "transform 0.3s";
    banner.style.transform = `translate(${-1084 * --count}px)`;
    if(count == 0) {
        count = 6;
        setTimeout(function(){
            banner.style.transition = "transform 0s";
            banner.style.transform = `translate(${-1084 * (imageDiv.length)}px)`;
        }, 300);
    }
    /* changeButtonStyle(); */
    auto = setInterval(autoSlide, 2000);
    setTimeout(()=>{checkArrow = false}, 300);
    pageCount.innerHTML =`${count} / ${imageDiv.length}`;
});

next.addEventListener("click", function(){
    if(checkArrow){return;}
    checkArrow = true;
    clearInterval(auto);
    banner.style.transition = "transform 0.3s";
    banner.style.transform = `translate(${-1084 * ++count}px)`;
    if(count == 7) {
        count = 1;
        setTimeout(function(){
            banner.style.transition = "transform 0s";
            banner.style.transform = "translate(-1084px)";
        }, 300);
    }
    /* changeButtonStyle(); */
    auto = setInterval(autoSlide, 2000);
    setTimeout(()=>{checkArrow = false}, 300);
    pageCount.innerHTML =`${count} / ${imageDiv.length}`;
});


/* ------------------------ 메인 슬라이더 배너 ------------------------  */


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


/* ------------------------ 메인 즐겨찾기 클릭 이벤트 ------------------------  */


const $bookMarks = $(".book-mark");

$bookMarks.each((i, bookMark) => {
    $(bookMark).on("click", function() {
        if($(this).attr("fill") == "rgb(51, 102, 255)") {
            $(this).attr("fill", "black");
        } else {
            $(this).attr("fill", "rgb(51, 102, 255)");
        }
    });
});


/* ------------------------ 메인 즐겨찾기 클릭 이벤트 ------------------------  */


const $prButton = $(".first_list");
const $freeButton = $(".second_list");
const $prBoard = $(".pr_board_wrap");
const $freeBoard = $(".free_board_wrap");

$prButton.on("click", function(e) {
    console.log("들어옴1")
    $freeBoard.css("display", "none");
    $prBoard.css("display", "block"); 
});

$freeButton.on("click", function(e) {
    console.log("들어옴2")
    $prBoard.css("display", "none"); 
    $freeBoard.css("display", "block");
});
