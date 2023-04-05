const $stage = $(".distribution_list");
const $freeBoardSelecter = $(".second_list");
const $freeBoardSortWrapper = $(".free_board_sort");
const $businessBoardSortWrapper = $(".business_board_sort");
let $rankingHeader = $(".ranking_header h2");

let getTimePassed = function (writtenDate, writtenTime) {
    const [yyyy, mm, dd] = writtenDate.split("-");
    const [hh, MM, ss] = writtenTime.split(":");
    const currentTime = new Date();
    const givenTime = new Date();
    givenTime.setFullYear(yyyy, mm - 1, dd);
    givenTime.setHours(hh, MM, ss);

    const betweenTime = Math.floor((currentTime.getTime() - givenTime.getTime()) / 1000 / 60);

    if (betweenTime < 1) return '방금전';
    if (betweenTime < 60) {
        return `${betweenTime}분전`;
    }

    const betweenTimeHour = Math.floor(betweenTime / 60);
    if (betweenTimeHour < 24) {
        return `${betweenTimeHour}시간전`;
    }

    const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
    if (betweenTimeDay < 30) {
        return `${betweenTimeDay}일전`;
    }

    const betweenTimeMonth = Math.floor(betweenTimeDay / 30);
    if (betweenTimeMonth < 12) {
        return `${betweenTimeMonth}개월전`;
    }

    const betweenTimeYear = Math.floor(betweenTimeDay / 365);
    return `${betweenTimeYear}년전`;
}

const $doAjax = function (ajaxConfig, callback) {
    $.ajax({
        url: ajaxConfig.url,
        type: ajaxConfig.type,
        success: function (result) {
            callback(result);
        }
    });
}

const createFreeBoardDOM = function (board) {
    let isWriterMember = board.memberId == null ? false : true;
    let writerName = isWriterMember ? board.memberName : board.businessCeoName;
    let writtenDate = board.boardFreeRegisterDate.split(" ")[0];
    let writtenTime = board.boardFreeRegisterDate.split(" ")[1];
    let timeText = getTimePassed(writtenDate, writtenTime);

    let text = `
        <article class="content-box">
            <div class="content-box-layout">
                <a href="javascript:void(0)" class="content-member-info-box">
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
                                        <span>${board.memberName}</span>
                                    </div>
                                    <div class="content-persnal-distributor">
                                        <div class="content-member-type">${isWriterMember ? '일반회원' : '유통업체'}</div>
                                    </div>
                                </div>
                                <span class="register-date">${timeText}</span>
                            </div>
                            <button type="button">
                                <svg style="background: none; cursor: pointer;" width="22" height="22" viewBox="0 0 18 18" fill="none" xmlns="https://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M3.58065 1C3.25997 1 3 1.26206 3 1.58533V16.4138C3 16.8632 3.48164 17.145 3.86873 16.922L9.00004 13.9662L14.1313 16.922C14.5184 17.145 15 16.8632 15 16.4138V1.58533C15 1.26206 14.74 1 14.4194 1H9.00004H3.58065ZM8.71195 12.7838C8.89046 12.681 9.10961 12.681 9.28812 12.7838L13.8387 15.4052V2.17067H9.00004H4.1613V15.4052L8.71195 12.7838Z" fill="white"></path>
                                    <path class="book-mark" d="M9.28812 12.7838C9.10961 12.681 8.89046 12.681 8.71195 12.7838L4.1613 15.4052V2.17067H9.00004H13.8387V15.4052L9.28812 12.7838Z" fill="black" fill-opacity="0.25"></path>
                                </svg>
                            </button>
                        </div>
                    </div>
                </a>
            </div>
            <a href="" class="content hasImage">
                <h3 class="content-title">${board.boardFreeTitle}</h3>
                <p class="content-detail">${board.boardFreeContent}</p>
                <!-- 이미지를 넣었다면 여기에 넣어주기 없다면 없애기 -->
                <!-- 이미지를 넣었다면 감싸고있는 a태그에 hasImage클래스 넣어주기 표시해둠 -->
                <div>
                    <!-- <img src="https://image.wanted.co.kr/optimize?src=https%3A%2F%2Fstatic.wanted.co.kr%2Fcommunity%2F2023%2F3%2Fe636fef8cd94bc0c9c921510c71ffae98c9c636065c75c113e4dd42a027129c9&w=384&q=90" class="board-detail-image"> -->
                </div>
                <!-- 이미지 끝 -->
                <div class="like-reply-count-box">
                    <div class="like-count-box">
                        <svg width="18" height="18" viewBox="0 0 18 18">
                            <path fill="currentColor" d="M13.353 2.214c.082.164.15.332.204.502.325 1.032.13 2.08-.396 3.092l-.105.191L16.253 6a.75.75 0 0 1 .743.648l.007.102v5.75a.75.75 0 0 1-.106.385l-.058.084-3.004 3.75a.75.75 0 0 1-.472.273L13.25 17H9.22a.75.75 0 0 1-.101-1.493l.102-.007h3.668l2.614-3.264V7.5h-3.91a.75.75 0 0 1-.604-1.195l.066-.077c.137-.14.36-.415.584-.778.5-.808.702-1.6.487-2.283a1.858 1.858 0 0 0-.113-.278c-.278-.551-1.075-.442-1.075-.056a3.17 3.17 0 0 1-.777 2.125c-.293.338-.59.555-.774.647l-.472.292c-.89.568-1.459 1.04-1.762 1.409l-.097.128-.058.095v.062l-.004.016-.006.093a.75.75 0 0 1-.641.641l-.102.007-.102-.007a.75.75 0 0 1-.648-.743V7.5H2.496v8h2.999l-.001-4.535.007-.102a.75.75 0 0 1 1.493.102v5.286l-.007.102a.75.75 0 0 1-.743.648H1.747l-.102-.007a.75.75 0 0 1-.648-.743v-9.5l.007-.102A.75.75 0 0 1 1.747 6h4.498l.066.005c.387-.38.92-.796 1.621-1.256l.472-.3.253-.154c.07-.035.217-.143.37-.32.226-.26.37-.576.403-.969l.008-.173c0-2.082 2.972-2.491 3.915-.619z"></path>
                        </svg>
                        <span class="like-count">${board.boardFreeLike}</span>
                    </div>
                    <div class="reply-count-box">
                        <svg width="18" height="18" viewBox="0 0 18 18">
                            <path fill="currentColor" transform="matrix(-1 0 0 1 18 0)" d="M9 1c4.377 0 8 3.14 8 7s-3.623 7-8 7c-.317 0-.593-.026-.954-.088l-.395-.074-.205-.043-3.295 2.089a.75.75 0 0 1-.968-.143l-.067-.09a.75.75 0 0 1 .143-.968l.09-.067 3.55-2.25a.75.75 0 0 1 .551-.1l.652.132.301.052c.228.036.408.05.597.05 3.592 0 6.5-2.52 6.5-5.5S12.592 2.5 9 2.5C5.407 2.5 2.5 5.02 2.5 8c0 1.858 1.039 3.573 2.773 4.348a.75.75 0 1 1-.612 1.37C2.37 12.693 1 10.432 1 8c0-3.86 3.622-7 8-7z"></path>
                        </svg>
                        <span class="reply-count">${board.replyCount}</span>
                    </div>
                </div>
            </a>
        </article>
    `

    return text;
}

const createBusinessBoardDOM = function (board) {

    let date = board.boardBusinessRegisterDate.split(" ")[0];
    let text =
        `
        <li>
            <div class="distribution_cards">
                <a href="javascript:void(0)">
                    <header/>
                    <div class="card_body">
                        <div class="distribution_card_position">[${board.businessCategory}] ${board.boardBusinessTitle}</div>
                        <div class="distribution_card_company_name">${date}</div>
                        <div class="distribution_card_location">
                            ${board.businessLocation} | 평균 평점 : ${board.boardBusinessGradeAverage}
                        </div>
                    </div>
                </a>
            </div>
        </li>
        `
    return text;
}

const setConfig = function (boardType, orderType) {
    return {
        url: `/main/boards/${boardType}/${orderType}`,
        type: "GET",
    }
}

const boardService = (function () {
    /**
     * hearderText는 orderType이 없거나
     * */
    function showFreeBoard(orderType) {
        let boardType = "free";
        orderType = orderType == undefined ? 'recent' : orderType;
        let headerText = orderType == undefined || orderType === 'recent' ? "최신 자유게시판 게시물" : "인기 자유게시판 게시물";

        console.log(orderType);

        $doAjax(
            /**$(".distribution_list")
             * ajax 성공 시 $(".distribution_list")를 비우고 다시 채운다.
             * 인기순, 최신순에 따라 헤더 텍스트가 달라진다.
             * */
            setConfig(boardType, orderType),
            (result) => {
                $businessBoardSortWrapper.css("display", "none");
                $freeBoardSortWrapper.css("display", "flex");
                $stage.empty();
                $rankingHeader.text(headerText);

                result.forEach((board, i) => {
                    if (i < 4) {
                        $stage.eq(0).append(createFreeBoardDOM(board));
                    } else $stage.eq(1).append(createFreeBoardDOM(board));
                });
            }
        );
    }

    function showBusinessBoard(orderType) {
        let boardType = "business";
        orderType = orderType == undefined ? 'recent' : orderType;
        let headerText = orderType == undefined || orderType === 'recent' ? "최신 기업게시판 게시물" : "평점 높은 기업게시판 게시물";

        $doAjax(
            setConfig(boardType, orderType),
            (result) => {
                $freeBoardSortWrapper.css("display", "none");
                $businessBoardSortWrapper.css("display", "flex");
                $stage.empty();
                $rankingHeader.text(headerText);
                console.log(result);

                result.forEach((board, i) => {
                    if (i < 4) {
                        $stage.eq(0).append(createBusinessBoardDOM(board));
                    } else $stage.eq(1).append(createBusinessBoardDOM(board));
                });
            }
        );
    }

    return {showFreeBoard: showFreeBoard, showBusinessBoard : showBusinessBoard}
})();

/* 페이지 로딩시 실행 */
boardService.showBusinessBoard();

$freeBoardSelecter.on("click", function () {
    boardService.showFreeBoard();
});

