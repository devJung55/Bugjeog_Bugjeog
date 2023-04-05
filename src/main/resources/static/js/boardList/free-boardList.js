const $writeButton = $(".write-button");
const $registerButton = $(".board-register-button");
const $modal = $("#modal");
const $modalExitButton = $(".modal-confirm-no-button");
let $page = 1;

$writeButton.click(function () {
    $modal.show();
});

$modalExitButton.click(function () {
    $modal.hide();
});

$registerButton.click(function () {
    $modal.show();
});

$(window).scroll(function () {
    let scrollY = window.scrollY
    if (scrollY < 100) {
        $registerButton.hide();
    } else {
        $registerButton.show();
    }
});

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

/* 게시글 목록 불러오기*/

const boardContainer = $('#listContainer')
const createDOM = function (board) {

    let isWriterMember = board.memberId == null ? false : true;
    let writerName = isWriterMember ? board.memberName : board.businessCeoName;
    let writtenDate = board.boardFreeRegisterDate.split(" ")[0];
    let writtenTime = board.boardFreeRegisterDate.split(" ")[1];
    let timeText = getTimePassed(writtenDate, writtenTime);

    let text = `

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
                                             <!--memberId, businessId인지 구분하는 법-->
                                             <span>${writerName}</span>
                                        </div>
                                        <div class="content-persnal-distributor">
                                                            <!--유통업체인지 개인인지 구분 or 위에 의해서 따라오는건지-->
                                             <div class="content-member-type">${isWriterMember ? '일반회원' : '유통업체'}</div>
                                        </div>
                                    </div>
                                        <!--시간 구하는 법-->
                                        <span class="register-date">${timeText}</span>
                                 </div>
                            </div>
                        </div>
                    </a>
                </div>
                <a href="/free-boards/detail/${board.boardFreeId}" class="content hasImage">
                            <h3 class="content-title">${board.boardFreeTitle}</h3>
                            <p class="content-detail">
                            ${board.boardFreeContent}</p>
                            `
    if (board.boardFreeImgVOs[0] != null) {
        text += `<div>
                    <img src="/free-boards/imgs/dispay?fileName=${board.boardFreeImgVOs[0].boardFreeImgPath}/t_${board.boardFreeImgVOs[0].boardFreeImgUuid}_${board.boardFreeImgVOs[0].boardFreeImgOriginalName}" class="board-detail-image">
                </div>`
    }
    text += `
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

let getBoards = function list() {
    $.ajax({
        url: `/free-boards/boards?page=${$page}`,
        type: "GET",
        success: function (result) {
            console.log(result);
            result.forEach((board, i) => {
                boardContainer.append(createDOM(board));
            });

            if (result.length == 0) {
                $(window).scroll().unbind();
            }
        }
    });
}

/* 처음에 우선 실행 */
getBoards();

$(window).scroll(
    function () {
        if ($(this).scrollTop() + $(this).height() == $(document).height()) {
            $page++;
            getBoards();
        }
    }
);