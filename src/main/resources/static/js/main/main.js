/* ------------------------ 메인 슬라이더 배너 ------------------------  */

HTMLCollection.prototype.forEach = Array.prototype.forEach;
const banner = document.querySelector("div.banner");
const imageDiv = document.querySelectorAll("div.banner div");
const lastImageDiv = document.createElement("div");
const firstImageDiv = document.createElement("div");
const next = document.querySelector(".next");
const prev = document.querySelector(".prev");
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
        changeButtonStyle();
        banner.style.transition = "transform 0.3s";
        banner.style.transform = `translate(${-1060 * count}px)`;
        auto = setInterval(autoSlide, 2000);
    });
});

imageDiv.forEach((div, i) => div.style.backgroundImage = `url(../../static/image/main/banner${i+1}.webp)`)

banner.appendChild(lastImageDiv);
lastImageDiv.style.backgroundImage = `url(../../static/image/main/banner1.webp)`;

banner.insertBefore(firstImageDiv, document.querySelector("div.banner div"));
firstImageDiv.style.backgroundImage = `url(../../static/image/main/banner${imageDiv.length}.webp)`

banner.style.transform = `translate(-1060px)`;

function changeButtonStyle() {
    if(temp){
        temp.style.background = "white";
        temp.style.color = "black";
        
    }
    // buttons[count - 1].style.background = "black";
    buttons[count - 1].style.color = "white";
    temp = buttons[count - 1];
    
    pageCount.innerHTML =`${count} / ${imageDiv.length}`;
}

function autoSlide(){
    banner.style.transition = "transform 0.3s";
    banner.style.transform = `translate(${-1060 * ++count}px)`;
    console.log(count);
    if(count == 7) {
        count = 1;
        setTimeout(function(){
            banner.style.transition = "transform 0s";
            banner.style.transform = "translate(-1060px)";
        }, 300);
    }
    changeButtonStyle();
}

prev.addEventListener("click", function(){
    if(checkArrow){return;}
    checkArrow = true;
    clearInterval(auto);
    banner.style.transition = "transform 0.3s";
    banner.style.transform = `translate(${-1060 * --count}px)`;
    if(count == 0) {
        count = 6;
        setTimeout(function(){
            banner.style.transition = "transform 0s";
            banner.style.transform = `translate(${-1060 * (imageDiv.length)}px)`;
        }, 300);
    }
    changeButtonStyle();
    auto = setInterval(autoSlide, 2000);
    setTimeout(()=>{checkArrow = false}, 300);
});

next.addEventListener("click", function(){
    if(checkArrow){return;}
    checkArrow = true;
    clearInterval(auto);
    banner.style.transition = "transform 0.3s";
    banner.style.transform = `translate(${-1060 * ++count}px)`;
    if(count == 7) {
        count = 1;
        setTimeout(function(){
            banner.style.transition = "transform 0s";
            banner.style.transform = "translate(-1060px)";
        }, 300);
    }
    changeButtonStyle();
    auto = setInterval(autoSlide, 2000);
    setTimeout(()=>{checkArrow = false}, 300);
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