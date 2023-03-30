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




/* 게시글 목록 불러오기*/

const boardContainer = $('#listContainer')
    console.log(boardContainer);

const createDOM = function (boardLists) {
    console.log("괴롭다");
    let text= `

            <article class="content-box">
                <div class="content-box-layout">
                    <a href="#" class="content-member-info-box">
                        <div class="content-member-info-box-layout">
                            <div class="content-member-text-left">
                                <div class="content-member-image-box">
                                    <div class="content-member-image-size">

                                         <img src="/image/boardList/distributor_icon.png">
                                    </div>
                                </div>
                                <div class="content-member-info">
                                    <div class="content-persnal-distributor-box">
                                        <div class="content-register-name-box">
                                             <span>임종욱</span>
                                        </div>
                                        <div class="content-persnal-distributor">
                                             <div class="content-member-type">유통업체</div>
                                        </div>
                                    </div>
                                        <span class="register-date">1시간 전</span>
                                 </div>
                            </div>
                        </div>
                    </a>
                </div>
                <a href="/FreeBoards/detail" class="content hasImage">
                            <h3 class="content-title">
                             어느회사 가야될까요... 4년제 갓졸업 캐릭터디자인 신입입니다.
                            </h3>
                            <p class="content-detail">
                            (연봉 2500) 20년 된 안정된 회사고 인지도 있는 작업물 꽤 있음. 입사 후에 빡세게 안조이고 체계적이고 여유있게 배울수있을것같음 신입한테 큰일 바로 안시킴. 야근 자주 없을듯. 지하철 조금 불편? 근데 돈이 최저수준
                            (연봉 협상 가능) 스타트업 회사. 개발보다는 외주 받는 하청업체에 가까움. 디자이너가 급하고 나랑 꼭 같이 일하고싶다고 연봉도 최대한 맞춰줄테니 거의 제발 와달라는 식으로 절절맴. 연봉도 높고 지하철도 괜찮지만 이렇게까지 절절 매는 이유가 있지 않을까 고민. 입사후 신입인데도 주요업무 시킬것같음
                            </p>
                            <!-- 이미지를 넣었다면 여기에 넣어주기 없다면 없애기 -->
                            <!-- 이미지를 넣었다면 감싸고있는 a태그에 hasImage클래스 넣어주기 표시해둠 -->
                            <div>
                                <img src="https://image.wanted.co.kr/optimize?src=https%3A%2F%2Fstatic.wanted.co.kr%2Fcommunity%2F2023%2F3%2Fe636fef8cd94bc0c9c921510c71ffae98c9c636065c75c113e4dd42a027129c9&w=384&q=90" class="board-detail-image">
                            </div>
                            <!-- 이미지 끝 -->
                            <div class="like-reply-count-box">
                                <div class="like-count-box">
                                    <svg width="18" height="18" viewBox="0 0 18 18">
                                        <path fill="currentColor" d="M13.353 2.214c.082.164.15.332.204.502.325 1.032.13 2.08-.396 3.092l-.105.191L16.253 6a.75.75 0 0 1 .743.648l.007.102v5.75a.75.75 0 0 1-.106.385l-.058.084-3.004 3.75a.75.75 0 0 1-.472.273L13.25 17H9.22a.75.75 0 0 1-.101-1.493l.102-.007h3.668l2.614-3.264V7.5h-3.91a.75.75 0 0 1-.604-1.195l.066-.077c.137-.14.36-.415.584-.778.5-.808.702-1.6.487-2.283a1.858 1.858 0 0 0-.113-.278c-.278-.551-1.075-.442-1.075-.056a3.17 3.17 0 0 1-.777 2.125c-.293.338-.59.555-.774.647l-.472.292c-.89.568-1.459 1.04-1.762 1.409l-.097.128-.058.095v.062l-.004.016-.006.093a.75.75 0 0 1-.641.641l-.102.007-.102-.007a.75.75 0 0 1-.648-.743V7.5H2.496v8h2.999l-.001-4.535.007-.102a.75.75 0 0 1 1.493.102v5.286l-.007.102a.75.75 0 0 1-.743.648H1.747l-.102-.007a.75.75 0 0 1-.648-.743v-9.5l.007-.102A.75.75 0 0 1 1.747 6h4.498l.066.005c.387-.38.92-.796 1.621-1.256l.472-.3.253-.154c.07-.035.217-.143.37-.32.226-.26.37-.576.403-.969l.008-.173c0-2.082 2.972-2.491 3.915-.619z"></path>
                                    </svg>
                                    <span class="like-count">10</span>
                                 </div>
                                <div class="reply-count-box">
                            <svg width="18" height="18" viewBox="0 0 18 18">
                        <path fill="currentColor" transform="matrix(-1 0 0 1 18 0)" d="M9 1c4.377 0 8 3.14 8 7s-3.623 7-8 7c-.317 0-.593-.026-.954-.088l-.395-.074-.205-.043-3.295 2.089a.75.75 0 0 1-.968-.143l-.067-.09a.75.75 0 0 1 .143-.968l.09-.067 3.55-2.25a.75.75 0 0 1 .551-.1l.652.132.301.052c.228.036.408.05.597.05 3.592 0 6.5-2.52 6.5-5.5S12.592 2.5 9 2.5C5.407 2.5 2.5 5.02 2.5 8c0 1.858 1.039 3.573 2.773 4.348a.75.75 0 1 1-.612 1.37C2.37 12.693 1 10.432 1 8c0-3.86 3.622-7 8-7z"></path>
                        </svg>
                        <span class="reply-count">5</span>
                    </div>
                </div>
            </a>
        </article>
`
    return text;

    }

    console.log("할꺼자나");
    console.log(boardLists);

boardLists.forEach((boardLists, i) => {
    boardContainer.append(createDOM(boardLists));
});