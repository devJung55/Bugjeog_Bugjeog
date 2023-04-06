const boardDetailContainer = $('#detailBoards')
console.log(boardDetailContainer);

const createDOM = function (boardFree) {
    console.log(boardFree);
    let text = `
        <section style="flex: 1 0 790px; background-color: #fff;">
            <article style="padding: 56px 39px 40px; background-color: #fff; border: 1px solid #e1e2e3;">
                 <div class="flex-direction">
                    <div style="display: flex; position: relative;">
                        <a href="" class="flex_width" style="margin-right: auto;">
                            <div class="dis_align">
                                <div class="flex_align" style="text-align: left;">
                                    <div class="posi_right">
                                        <div class="left_profile_img profile_img">
                                            <div class="profile-image-login">
                                                <div class="profile-member-status">
                               `
                                if(boardFree.memberId){
                                    if(!boardFree.memberImgUuid){
                                        text += `<img src="/image/boardList/self_employ_icon.png">`
                                    }else {
                                        text += `<img src="${'/mypage/profile/display?fileName=' + boardFree.memberImgPath + '/' + boardFree.memberImgUuid + '_' + boardFree.memberImgOriginalName}">`;
                                    }
                                }else {
                                    if(boardFree.businssImgUuid){
                                        text += `<img src="/image/boardList/distributor_icon.png">`
                                    }else {
                                        text += `<img src="${'/mypage/profile/display?fileName=' + boardFree.businessImgPath + '/' + boardFree.businessImgUuid + '_' + boardFree.businessImgOriginalName}">`;
                                    }
                                }
            text+=             `                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="flex-direction">
                                        <div class="flex_align" style="margin-bottom: 3px;">
                                    `
                                if(boardFree.memberId){
                                    text += `<div class="nickname" id="right_nickname">${boardFree.memberName}</div>
                                                <div class="career_field" style="margin-right: 4px;">
                                                    <div class="career field">개인회원</div>
                                                </div>`
                                }else {
                                    text += `<div class="nickname" id="right_nickname">${boardFree.businessCompanyName}</div>
                                                <div class="career_field" style="margin-right: 4px;">
                                                    <div class="career field">유통업체</div>
                                                </div>`
                                }
                                            

            text+=                  `      </div>
                                            <span id="register_date" class="date">${boardFree.boardFreeRegisterDate}</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
<!--                        <h1 id="title">📌개발자를 준비하며 자주 겪는 고민 - 희망 연봉</h1>-->
                         <h1 id="title">${boardFree.boardFreeTitle}</h1>
                    </div>
                    <div id="content">
                    ${boardFree.boardFreeContent}
<!--                        신입 개발자로 입사할 때 희망 연봉을 어떻게 정해야 할지 고민이 많을 거예요. 희망 연봉은 단어 그대로 입사 지원자가 받고 싶은 연봉이에요.-->
<!--                        연봉은 개발자 실력에 따라 정해져요.-->
<!--                        실력이란 개발 능력뿐만 아니라 학력, 자격증, 나이, 전공, 사회경험, 성격, 외모까지 포함돼요. 하지만 신입은 본인의 실력을 가늠하기가 쉽지 않죠.-->

<!--                        일반적으로 신입 개발자가 본인의 연봉을 결정할 때, 주변 지인 또는 커뮤니티를 참고하곤 하죠. 대부분 이런 방식으로 초봉을 설정하지만, 이 방법은 한 가지 큰 단점이 있어요.-->
<!--                        그건 지인이나 커뮤니티에서 알려주는 대상과 본인의 실력이 다르다는 거죠.-->
                    <div style="margin-bottom: 20px;"></div>
                    `
    /* fileVO로 자바에서 보내야함 */
    if(boardFree.boardFreeImgUuid != null) {
        text += `<div>
                    <img style="width: 100%" src="/free-boards/imgs/dispay?fileName=${boardFree.boardFreeImgPath}/${boardFree.boardFreeImgUuid}_${boardFree.boardFreeImgOriginalName}">
                </div>`
    }
    text += `</div>
                <div id="bottom_action">
                    <div style="width: 78px;">
                        <button class="like_button good-button">
                            <svg class="viewbox like">
                                <path fill="currentColor" d="M13.353 2.214c.082.164.15.332.204.502.325 1.032.13 2.08-.396 3.092l-.105.191L16.253 6a.75.75 0 0 1 .743.648l.007.102v5.75a.75.75 0 0 1-.106.385l-.058.084-3.004 3.75a.75.75 0 0 1-.472.273L13.25 17H9.22a.75.75 0 0 1-.101-1.493l.102-.007h3.668l2.614-3.264V7.5h-3.91a.75.75 0 0 1-.604-1.195l.066-.077c.137-.14.36-.415.584-.778.5-.808.702-1.6.487-2.283a1.858 1.858 0 0 0-.113-.278c-.278-.551-1.075-.442-1.075-.056a3.17 3.17 0 0 1-.777 2.125c-.293.338-.59.555-.774.647l-.472.292c-.89.568-1.459 1.04-1.762 1.409l-.097.128-.058.095v.062l-.004.016-.006.093a.75.75 0 0 1-.641.641l-.102.007-.102-.007a.75.75 0 0 1-.648-.743V7.5H2.496v8h2.999l-.001-4.535.007-.102a.75.75 0 0 1 1.493.102v5.286l-.007.102a.75.75 0 0 1-.743.648H1.747l-.102-.007a.75.75 0 0 1-.648-.743v-9.5l.007-.102A.75.75 0 0 1 1.747 6h4.498l.066.005c.387-.38.92-.796 1.621-1.256l.472-.3.253-.154c.07-.035.217-.143.37-.32.226-.26.37-.576.403-.969l.008-.173c0-2.082 2.972-2.491 3.915-.619z"></path>
                            </svg>
                            <span class="like_count like_count_bottom good-count">3</span>
                        </button>
                    </div>
                    <div style="margin-right: auto;">
                        <button class="like_button">
                            <svg class="viewbox">
                                <path fill="currentColor" transform="matrix(-1 0 0 1 18 0)" d="M9 1c4.377 0 8 3.14 8 7s-3.623 7-8 7c-.317 0-.593-.026-.954-.088l-.395-.074-.205-.043-3.295 2.089a.75.75 0 0 1-.968-.143l-.067-.09a.75.75 0 0 1 .143-.968l.09-.067 3.55-2.25a.75.75 0 0 1 .551-.1l.652.132.301.052c.228.036.408.05.597.05 3.592 0 6.5-2.52 6.5-5.5S12.592 2.5 9 2.5C5.407 2.5 2.5 5.02 2.5 8c0 1.858 1.039 3.573 2.773 4.348a.75.75 0 1 1-.612 1.37C2.37 12.693 1 10.432 1 8c0-3.86 3.622-7 8-7z"></path>
                            </svg>
                            <span class="like_count like_count_bottom reply-count">0</span>
                        </button>
                    </div>
                    <button id="dot_third" style="display: flex;">
                        <svg width="24" height="24" viewBox="0 0 24 24">
                            <path fill="currentColor" d="M12 10a2 2 0 1 1-.001 4.001A2 2 0 0 1 12 10zm7 0a2 2 0 1 1-.001 4.001A2 2 0 0 1 19 10zM5 10a2 2 0 1 1-.001 4.001A2 2 0 0 1 5 10z"></path>
                        </svg>
                    </button>
                    <div id="declaration">
                        <ul id="declaration_ul">
                            <li style="margin-bottom: 2px;">
                                <button class="declaration_button">게시글 신고하기</button>
                            </li>
                            <li>
                                <button class="declaration_button">사용자 신고하기</button>
                            </li>
                        </ul>
                        <div id="bubble">
                        </div>
                    </div>
                </div>
            </article>
            </div>
        </section>
`
    return text;

}

boardDetailContainer.append(createDOM(currentBoard));

function date(date) {
    let registerDate = new Date(date);
    return registerDate.getFullYear() + "." + (registerDate.getMonth() + 1) + "." + registerDate.getDate();
}

$(".date").text(date($(".date").text()));
$(".dates").each((i, e) => {
    $(e).text(date($(e).text()));
});

// detailsOfBoards.forEach((detailsOfBoards, i) => {
//     boardDetailContainer.append(createDOM(detailsOfBoards));
// });

/*=================================== 댓글 ===================================================*/
