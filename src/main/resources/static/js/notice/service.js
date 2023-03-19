
/* 스크롤 이벤트 */
/*  스크롤을 할 때마다 로그로 현재 스크롤의 위치가 찍혀나온다.  */

window.addEventListener('scroll', ()=> {
console.log(window.scrollX, window.scrollY);

});


// const vision = document.querySelector(".vision-button")
// const status = document.querySelector(".status-button")
// const service = document.querySelector(".service-button")
// const bugjeogway = document.querySelector(".bugjeogway-button")
// const contact = document.querySelector(".contact-button")

// vision.addEventListener('click', () => {
//     window.scrollTo({ top: 0, behavior: 'smooth' });
// });

const $vision = $(".vision-button");
const $status = $(".status-button")
const $service = $(".service-button")
const $bugjeogway = $(".bugjeogway-button")
const $contact = $(".contact-button")

/* vision 551

status 1101

services 1846

bugjeogway 3446

contacts 4130 */

$vision.click(function(){
    console.log("들옴");
    $('html, body').animate({scrollTop:551},300);
	    return false;
});

$status.click(function(){
    console.log("들옴");
    $('html, body').animate({scrollTop:1101},300);
	    return false;
});
$service.click(function(){
    console.log("들옴");
    $('html, body').animate({scrollTop:1846},300);
	    return false;
});
$bugjeogway.click(function(){
    console.log("들옴");
    $('html, body').animate({scrollTop:3446},300);
	    return false;
});
$contact.click(function(){
    console.log("들옴");
    $('html, body').animate({scrollTop:4130},300);
	    return false;
});