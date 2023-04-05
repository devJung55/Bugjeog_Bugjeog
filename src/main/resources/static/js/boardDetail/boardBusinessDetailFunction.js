/* 리스트 삽입 js */
function showSubBoardsList(boards) {
    /*<a th:href="@{/board/business/detail(boardBusinessId=${board.boardBusinessId})}" class="modal_button" name="${board.boardBusinessId}">*/
    $($('ul.boardList-info-box')[0]).empty();
    let text = ``;
    boards.forEach(board => {
        text += `
           <li class="info-box-layout"  th:object="${board}">
                <label class="modal_button" name="${board.boardBusinessId}">
                <!-- 상세보기 모달 버튼 -->
                    <div class="image-box">
                        <img src="/imgs/business/display?fileName=${board.boardBusinessImgPath + '/t_' + board.boardBusinessImgUuid + '_' + board.boardBusinessImgOriginalName}" class="info-image">
                    </div>
                    <div class="info-section-box">
                        <div class="cate-title-box">
                            <span class="cate-title-style">
                                <span class="cate-title">${board.businessCategory}</span>
                            </span>
                        </div>
                        <p class="circulation-title">${board.boardBusinessTitle}</p>
                    </div>
                    <div class="location-box">
                        <div class="location-box-layout">${board.businessLocation}</div>
                    </div>
                    <div class="review-count-box">
                        <div class="icon-box">
                            <div class="review-icon-box">
                                <img src="/image/boardList/review_icon.png" class="review-icon">
                            </div>
                            <div>
                                <span class="review-count">${board.boardBusinessReviewCount}</span>
                            </div>
                        </div>
                        <div class="icon-box">
                            <div class="review-icon-box">
                                <img src="/image/boardList/star_icon.png" class="review-grade-icon">
                            </div>
                            <div>
                                <span class="review-grade-count">${board.boardBusinessGradeAverage}</span>
                            </div>
                        </div>
                    </div>
                </label>
            </li>
			`;
    });
    $($('ul.boardList-info-box')[0]).append(text);
};

/*  */
function showBusinessDetail(board, boardImgs, reviews, boards, member, reviewCount, reviewGrade, isFavorite) {
    $('#all_wrap').empty();
    let text = ``
    text = `
            <div id="all_wrap_div" th:object="${board}">
                <div class="modal-top-active" style="overflow-y: auto; flex: 1 1;">
                    <div id="third_div">
                        <div id="title">
                            <div id="title_div">
                                <div>
                                    <h2 id="title_h2">${board.boardBusinessTitle}</h2>
                                </div>
                                <div id="correction_delete">
                                    <img style="width: 18px" src="/image/board_detail/crystal.png" alt="">
                                    <img style="width: 22px;margin-left: 7px;" src="/image/board_detail/delete.png" alt="">
                                </div>
                                <svg name="favoriteButton" style="background: none; cursor: pointer;" width="25" height="25" viewBox="0 0 18 18" fill="none" xmlns="https://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M3.58065 1C3.25997 1 3 1.26206 3 1.58533V16.4138C3 16.8632 3.48164 17.145 3.86873 16.922L9.00004 13.9662L14.1313 16.922C14.5184 17.145 15 16.8632 15 16.4138V1.58533C15 1.26206 14.74 1 14.4194 1H9.00004H3.58065ZM8.71195 12.7838C8.89046 12.681 9.10961 12.681 9.28812 12.7838L13.8387 15.4052V2.17067H9.00004H4.1613V15.4052L8.71195 12.7838Z" fill="white"></path>
                                    <path class="book-mark" d="M9.28812 12.7838C9.10961 12.681 8.89046 12.681 8.71195 12.7838L4.1613 15.4052V2.17067H9.00004H13.8387V15.4052L9.28812 12.7838Z" fill="black" fill-opacity="0.25"></path>
                                </svg>
                            </div>
                        </div>
                        <section id="section_info">
                            <header style="display: flex;">
                                <section id="main_img">
                                    <div class="relative_size">
                                        <div class="file-banner-box" style="display: flex">`;
    // boardImgs.forEach(boardImg => {
    /*text += `<img style="width: 100%; height: 100%;" src="/imgs/business/display?fileName=${boardImg.boardBusinessImgPath + '/' + boardImg.boardBusinessImgUuid + '_' + boardImg.boardBusinessImgOriginalName}" alt="">`*/
    // });
    boardImgs.forEach(boardImg => {
        text += `
<!--                                            <div style="min-width: 686px; max-width: 686px; height: auto; text-align: center;">-->
                                                <img class="img_size" src="/imgs/business/display?fileName=${boardImg.boardBusinessImgPath + '/' + boardImg.boardBusinessImgUuid + '_' + boardImg.boardBusinessImgOriginalName}" alt="">
<!--                                            </div>-->
                `;
    })
    text += `
                                        </div>
                                            </div>
                                            <label for="main_img_btn_left" class="main_img_btn" id="main_img_btn_left">
                                                <img id="left_arrow" src="/image/board_detail/left_arrow.png" alt="">
                                                <button class="main_img_btn" style="display: none;"></button>
                                            </label>
                                            <label for="main_img_btn_left" class="main_img_btn" id="main_img_btn_right">
                                                <img id="right_arrow" src="/image/board_detail/right_arrow.png" alt="">
                                                <button class="main_img_btn" style="display: none;"></button>
                                            </label>
                                        </section>
                                        <section id="right_section">
                                            <h2 class="title_h2">${board.businessCompanyName}</h2>
                                            <h4 class="h4">설립일</h4>
                                            <h5 class="h5">${board.businessEstablishmentDate}</h5>
                                            <h4 class="h5">대표자: ${board.businessCeoName}</h4>
                                            <h5 class="h4">유통물류센터</h5>
                                            <h4 class="h5">${board.businessLocation}</h4>
                                            <h4 class="h4">슬로건</h4>
                                            <h5 class="h5">고객과 협력사의 동반성장 원칙으로 새로운 유통물류 패러다임 형성</h5>
                                        </section>
                                        <div style="display: flex; justify-content: space-between;">
                                            <div style="margin-left: 10px; flex: 1 1 auto;">
                                                <a></a>
                                            </div>
                                        </div>
                                    </header>
                                    <div style="width: calc(100% - 360px); color: #333;">
                                        <div id="content">
                                            <div>
                                                ${board.boardBusinessContent}
                                    </div>
                                </div>
                            </div>
                        <h2 id="footer_title">이 회사의 모든 글</h2>
                        <ul id="footer_ul">`;
    /* href="/board/business/detail?boardBusinessId=${other.boardBusinessId}">*/
    boards.forEach(other => {
            text += `
                            <li class="footer_li" th:object="${other}">
                                <label class="otherBoards" style="--base-font-size: 10;">
                                    <div class="footer_li_div">
                                        <img class="footer_img" name="${other.boardBusinessId}"
                                        src="/imgs/business/display?fileName=${other.boardBusinessImgPath + '/' + other.boardBusinessImgUuid + '_' + other.boardBusinessImgOriginalName}"
                                        alt="'/image/boardList/no-image-64.png'">
                                    </div>
                                    <div class="event_content">
                                        <p class="event_content_p">${other.boardBusinessContent}</p>
                                    </div>
                                </label>
                            </li>`;
        }
    );
    text += `
                        </ul>
                            <div class="see-more-button">
                                <a id="event_all" href="/board/business/list?businessId=${board.businessId}">
                                    이벤트 모두 보기
                                    <svg width="12" height="12" viewBox="0 0 12 12" margin-left="5px" margin-right="10px">
                                        <path fill="currentColor" d="M3.345 9.72a.75.75 0 0 0 1.06 1.06l4.25-4.25a.75.75 0 0 0 0-1.06l-4.25-4.25a.75.75 0 0 0-1.06 1.06L7.065 6l-3.72 3.72z"></path>
                                    </svg>
                                </a>
                            </div>
                        </div>`;
    if (member != null) {
        text += `
                        <div style="margin-bottom: 20px; margin-top: 20px;" th:object="${member}" th:if="${member != null}">
                            <div class="profile-box" style="position:relative;">
                                <th:block th:if="${member != null}">
                                    <div id="profile_img_nickname">
                                        <div class="profile_img" id="profile_my_img">
                                            <div class="profile-image-login">
                                                <div class="profile-member-status">
<!--                                                    <span class="profile-image">자</span> -->
                                                    <img class="profile-image" src="/imgs/business/display?fileName=${member.memberImgPath + '/' + member.memberImgUuid + '_' + member.memberImgOriginalName}" alt="">
                                                </div>
                                            </div>
                                        </div>
                                        <span id="my_nickname">${member.memberName}</span>
                                    </div>
                                    <div id="form_wrap">
                                        <form action="/board/business/review/write?boardBusinessId=${board.boardBusinessId}" method="post">
                                            <div class="rating-reply">
                                                <input type="radio" id="star1" name="reviewGrade" class="star" value="1" checked/>
                                                <label for="star1" id="label1" style="cursor: pointer;"></label>
                                                <input type="radio" id="star2" name="reviewGrade"  class="star" value="2" />
                                                <label for="star2" id="label2" style="cursor: pointer;"></label>
                                                <input type="radio" id="star3" name="reviewGrade" class="star"  value="3" />
                                                <label for="star3" id="label3" style="cursor: pointer;"></label>
                                                <input type="radio" id="star4" name="reviewGrade" class="star"  value="4" />
                                                <label for="star4" id="label4" style="cursor: pointer;"></label>
                                                <input type="radio" id="star5" name="reviewGrade" class="star" value="5" />
                                                <label for="star5" id="label5" style="cursor: pointer;"></label>
                                            </div>
                                            <textarea id="reply_textarea" placeholder="리뷰 남기기" name="reviewContent"></textarea>
                                            <button type="submit" class="replyRegisterButton">
                                                <span>등록</span>
                                            </button>
                                        </form>
                                    </div>
                                </th:block>
                            </div>
                        </div>`;
    }// end if
    text += `
                        </section>
                        <div id="review_score_box_layout">
                            총 리뷰 개수: ${reviewCount} 개
                            <div class="review-score-box" style="position: absolute; bottom: -30px; left: -5px;">
                            `;
    let reviewsGradeAverage;
    let reviewsGradeTotal = 0;
    reviews.forEach(review => {
        reviewsGradeTotal += `${review.reviewGrade}`;
    });
    reviewsGradeAverage = reviewsGradeTotal / reviews.size;

    for (let i = 0; i < reviewsGradeAverage; i++) {
        text += `
                                <label class="review-score ratingActive"></label>
        `;
    }
    for (let i = 0; i < 5 - reviewsGradeAverage; i++) {
        text += `
                                <label class="review-score"></label>
        `;
    }
    text += `
                            </div>
                        </div>`;

    reviews.forEach(review => {
        text += `
                        <div id="reply_wrap">
                            <div id="reply_wrap_div" style="position: relative;">
                                <div style="display: flex; justify-content: space-between;">
                                    <a style="display: flex; width: 0;">
                                        <div style="display: inline-flex; align-items: center; margin-right: auto;">
                                            <div style="text-align: left; display: flex; align-items: center;">
                                                <div style="position: relative; margin-right: 7px;">
                                                    <div id="reply_my">
                                                        <div class="profile-image-login">
                                                            <div class="profile-member-status">
                                                            <!-- <span class="profile-image">유</span> -->
                                                                <img src="/imgs/business/display?fileName=${review.memberImgPath != null ? (review.memberImgPath + '/' + review.memberImgUuid + '_' + review.memberImgOriginalName) : "member_no_image.png"}" alt="">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div style="display: flex; flex-direction: column;">
                                                    <div style="display: flex; margin-bottom: 3px; align-items: center;">
                                                        <div id="reply_nickname">${review.memberName}</div>
                                                    </div>
                                                    <span id="reply_date">${review.reviewRegisterDate}</span>
                                                    <div class="review-score-box">
                                                    `;
        for (let i = 0; i < `${review.reviewGrade}`; i++) {
            text += `
                                                        <label class="review-score" style="color: #ffcc00 !important;"></label>`;
        }
        for (let i = 0; i < `${5 - review.reviewGrade}`; i++) {
            text += `
                                                        <label class="review-score"></label>`;
        }
        text += `
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                                <div class="replyButtonWrap" style="display: inline-block; position: absolute; right: 0; top: 0;">
                                    <label name="editReply">
                                        <button type="button" id="float_edit">수정</button>
                                    </label>
                                    <label name="deleteReply">
                                        <button type="button" id="float_delete">삭제</button>
                                    </label>
                                </div>
                                <div id="reply_content">${review.reviewContent}</div>
                            </div>
                        </div>
                        </div>
                        </div>
                        <div id="up_div">
                        <button id="up_btn">
                        <span id="up_btn_span">
                            <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="https://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" clip-rule="evenodd" d="M11.2674 5.29328L5.53033 11.0303C5.23744 11.3232 4.76256 11.3232 4.46967 11.0303C4.17678 10.7374 4.17678 10.2626 4.46967 9.96967L11.4697 2.96967C11.5063 2.93306 11.5457 2.90102 11.5873 2.87356C11.8784 2.68135 12.274 2.71339 12.5303 2.96967L19.5303 9.96967C19.8232 10.2626 19.8232 10.7374 19.5303 11.0303C19.2374 11.3232 18.7626 11.3232 18.4697 11.0303L12.7326 5.29328C12.744 5.34522 12.75 5.39918 12.75 5.45455V21.4545C12.75 21.8688 12.4142 22.2045 12 22.2045C11.5858 22.2045 11.25 21.8688 11.25 21.4545V5.45455C11.25 5.39918 11.256 5.34522 11.2674 5.29328ZM11.8387 4.72193C11.8907 4.71054 11.9446 4.70455 12 4.70455C12.0554 4.70455 12.1093 4.71054 12.1613 4.72193L12 4.56066L11.8387 4.72193Z" fill="black"></path>
                            </svg>
                        </span>
                    </button>
                </div>
            </div>`;
    });
    $('#all_wrap').append(text);


    const $submitReply = $($('button.replyRegisterButton')[0]);
    if (businessId == null && memberId != null) {
        $submitReply.removeClass('is-hidden');
        $submitReply.css("color", "rgb(196, 196, 196)").css("background-color", "rgb(242, 244, 247)");

        // textarea 입력시 등록 버튼 색상 변경
        $('#reply_textarea').on('input', function () {
            if ($(this).val().length > 0) {
                $('button[type="submit"]').css("color", "white").css("background-color", "blue");
            } else {
                $('button[type="submit"]').css("color", "rgb(196, 196, 196)").css("background-color", "rgb(242, 244, 247)");
            }
        });
        if (`${member.memberId == memberId}` && memberId != null) {
            $($('.replyButtonWrap')[0]).removeClass('is-hidden');
        } else {
            $($('.replyButtonWrap')[0]).addClass('is-hidden');
        }
    } else {
        $submitReply.addClass('is-hidden');
    }


    let $label = $('label.otherBoards');
    $label.on("click", (e) => {
        // console.log($(e.target));
        // console.log($(e.target).attr('name'));
        let selectBoardId = $(e.target).attr('name');
        detailAjax(selectBoardId);
    });

    const $editReviewButton = $('label[name=editReply]');
    $editReviewButton.on("click", (e) => {
        console.log("수정 버튼 눌림")
        console.log($(e.target));
    });

    const $favoriteButton = $($('svg[name=favoriteButton]')[0]);

    // 즐겨찾기 버튼
    const $bookMarks = $(".book-mark");

    $bookMarks.each((i, bookMark) => {
        $(bookMark).on("click", function () {
            if ($(this).attr("fill") == "rgb(51, 102, 255)") {
                $(this).attr("fill", "black");
            } else {
                $(this).attr("fill", "rgb(51, 102, 255)");
            }
        });
    });

    $favoriteButton.hide();
    if (`${isFavorite != null}`) {
        $favoriteButton.show()
        if (`${isFavorite}`) {
            $favoriteButton.addClass('fill');
        } else {
            $favoriteButton.removeClass('fill');
        }
    } else {
        $favoriteButton.hide();
    }

    $favoriteButton.on("click", (e) => {
        alert("전송 성공");
        console.log(e);
        console.log($(e));

        let $target = $(e.currentTarget);
        console.log("관심 클릭");
        console.log($target);
        if ($target.hasClass("fill")) {
            $target.removeClass("fill");
            $target.attr("fill", "black");
        } else {
            $target.addClass("fill");
            $target.attr("fill", "rgb(51, 102, 255)");
        }
        ajaxFavorite(board.boardBusinessId, member.memberId, $target);
    });

    function ajaxFavorite(boardId, memberId, $target) {
        console.log(boardId);
        console.log(memberId);
        $.ajax({
            url: "/favorite/update",
            method: "PUT",
            data: {
                boardBusinessId: JSON.parse(boardId),
                memberId: JSON.parse(memberId)
            },
            async: false,
            // contentType: "application/json; charset=utf-8",
            success: function () {
                alert("관심 전송 성공");
            },
            error: function (e) {
                alert("관심 전송 실패");
                if ($target.hasClass("fill")) {
                    $target.removeClass("fill");
                } else {
                    $target.addClass("fill");
                }
                console.log(e);
            }

        });
    }
};

/*    let board = [[${board}]];
    let reviews = [[${reviews}]];
    let boards = [[${boardList}]];
    let member = [[${member}]];
    let boardImgs = [[${boardImgs}]];
    let memberImgFullPath = [[${memberImgFullPath}]];
    console.log(board);
    showList(board, reviews, boards, member, boardImgs);
    $(document).ready(function () {
        // 등록 버튼 초기 색상 설정
        $('button[type="submit"]').css("color", "rgb(196, 196, 196)").css("background-color", "rgb(242, 244, 247)");

        // textarea 입력시 등록 버튼 색상 변경
        $('#reply_textarea').on('input', function () {
            if ($(this).val().length > 0) {
                $('button[type="submit"]').css("color", "white").css("background-color", "blue");
            } else {
                $('button[type="submit"]').css("color", "rgb(196, 196, 196)").css("background-color", "rgb(242, 244, 247)");
            }
        });
    });


    const $top = $("#up_btn");

    $top.click(function () {
        $('html, body').animate({scrollTop: 0}, 400);
        return false;
    });

    function showList(board, reviews, boards, member, boardImgs) {
        $('#all_wrap').empty();
        let text = ``
        text = `
    <div id="all_wrap_div" th:object="${board}">
        <div class="modal-top-active" style="overflow-y: auto; flex: 1 1;">
            <div id="third_div">
                <div id="title">
                    <div id="title_div">
                        <div>
                            <h2 id="title_h2">${board.boardBusinessTitle}</h2>
                        </div>
                        <div id="correction_delete">
                            <img style="width: 18px" src="/image/board_detail/crystal.png" alt="">
                            <img style="width: 22px;margin-left: 7px;" src="/image/board_detail/delete.png" alt="">
                        </div>
                        <svg style="background: none; cursor: pointer;" width="25" height="25" viewBox="0 0 18 18" fill="none" xmlns="https://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" clip-rule="evenodd" d="M3.58065 1C3.25997 1 3 1.26206 3 1.58533V16.4138C3 16.8632 3.48164 17.145 3.86873 16.922L9.00004 13.9662L14.1313 16.922C14.5184 17.145 15 16.8632 15 16.4138V1.58533C15 1.26206 14.74 1 14.4194 1H9.00004H3.58065ZM8.71195 12.7838C8.89046 12.681 9.10961 12.681 9.28812 12.7838L13.8387 15.4052V2.17067H9.00004H4.1613V15.4052L8.71195 12.7838Z" fill="white"></path>
                            <path class="book-mark" d="M9.28812 12.7838C9.10961 12.681 8.89046 12.681 8.71195 12.7838L4.1613 15.4052V2.17067H9.00004H13.8387V15.4052L9.28812 12.7838Z" fill="black" fill-opacity="0.25"></path>
                        </svg>
                    </div>
                </div>
                <section id="section_info">
                    <header style="display: flex;">
                        <section id="main_img">
                            <div class="relative_size" style="position: relative; width: 700px; height: 400px;">
                                <div class="file-banner-box">`;
        boardImgs.forEach(boardImg => {
            text += `<img style="width: 100%; height: 100%;" src="/imgs/business/display?fileName=${boardImg.boardBusinessImgPath + '/' + boardImg.boardBusinessImgUuid + '_' + boardImg.boardBusinessImgOriginalName}" alt="">`
        });
        text += `
                        </div>
                            </div>
                            <button class="main_img_btn" id="main_img_btn_left">
                                <img id="left_arrow" src="/image/board_detail/left_arrow.png" alt="">
                            </button>
                            <button class="main_img_btn" id="main_img_btn_right">
                                <img id="right_arrow" src="/image/board_detail/right_arrow.png" alt="">
                            </button>
                        </section>
                        <section id="right_section">
                            <h2 class="title_h2">${board.businessCompanyName}</h2>
                            <h4 class="h4">설립일</h4>
                            <h5 class="h5">${board.businessEstablishmentDate}</h5>
                            <h4 class="h5">대표자: ${board.businessCeoName}</h4>
                            <h5 class="h4">유통물류센터</h5>
                            <h4 class="h5">${board.businessLocation}</h4>
                            <h4 class="h4">슬로건</h4>
                            <h5 class="h5">고객과 협력사의 동반성장 원칙으로 새로운 유통물류 패러다임 형성</h5>
                        </section>
                        <div style="display: flex; justify-content: space-between;">
                            <div style="margin-left: 10px; flex: 1 1 auto;">
                                <a></a>
                            </div>
                        </div>
                    </header>
                    <div style="width: calc(100% - 360px); color: #333;">
                        <div id="content">
                            <div>
                                ${board.boardBusinessContent}
        </div>
        </div>
        </div>
        </section>
        <div id="footer">
        <h2 id="footer_title">이 회사의 모든 글</h2>
        <ul id="footer_ul">`;
        boards.forEach(other => {
            text += `
        <li class="footer_li" th:object="${other}">
        <a style="--base-font-size: 10;" href="/board/business/detail?boardBusinessId=${other.boardBusinessId}">
        <div class="footer_li_div">
        <img class="footer_img" src="${other.boardBusinessImgFullPath || '/image/boardList/no-image-64.png'}" alt="">
        </div>
        <div class="event_content">
        <p class="event_content_p">${other.boardBusinessContent}</p>
        </div>
        </a>
        </li>`;
        }
    );
    text += `
                </ul>
                    <div class="see-more-button">
                        <a id="event_all">
                            이벤트 모두 보기
                            <svg width="12" height="12" viewBox="0 0 12 12" margin-left="5px" margin-right="10px">
                                <path fill="currentColor" d="M3.345 9.72a.75.75 0 0 0 1.06 1.06l4.25-4.25a.75.75 0 0 0 0-1.06l-4.25-4.25a.75.75 0 0 0-1.06 1.06L7.065 6l-3.72 3.72z"></path>
                            </svg>
                        </a>
                    </div>
                </div>
                <div style="margin-bottom: 20px; margin-top: 20px;" th:object="${member}">
                    <div class="profile-box">
                        <div id="profile_img_nickname">
                            <div class="profile_img" id="profile_my_img">
                                <div class="profile-image-login">
                                    <div class="profile-member-status">
                                        <!-- <span class="profile-image">유</span> -->
                                        <img src="${member.memberImgFullPath || ''}" alt="">
                                    </div>
                                </div>
                            </div>
                            <span id="my_nickname">${member.memberName}</span>
                            <div class="rating-reply">
                                <input type="radio" id="star1" name="rating" class="star" value="1" />
                                <label for="star1" id="label1" style="cursor: pointer;"></label>
                                <input type="radio" id="star2" name="rating"  class="star" value="2" />
                                <label for="star2" id="label2" style="cursor: pointer;"></label>
                                <input type="radio" id="star3" name="rating" class="star"  value="3" />
                                <label for="star3" id="label3" style="cursor: pointer;"></label>
                                <input type="radio" id="star4" name="rating" class="star"  value="4" />
                                <label for="star4" id="label4" style="cursor: pointer;"></label>
                                <input type="radio" id="star5" name="rating" class="star" value="5" />
                                <label for="star5" id="label5" style="cursor: pointer;"></label>
                            </div>
                        </div>
                        <div id="form_wrap" th:object="${board}">
                            <form action="/board/business/review/write?boardBusinessId=${board.boardBusinessId}" method="post">
                                <textarea id="reply_textarea" placeholder="리뷰 남기기"></textarea>
                                <button type="submit">
                                    <span>등록</span>
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
                <div id="review_score_box_layout">
                    총 리뷰 개수: 1개
                    <div class="review-score-box" style="position: absolute; bottom: -30px; left: -5px;">
                        <label class="review-score"></label>
                        <label class="review-score"></label>
                        <label class="review-score"></label>
                        <label class="review-score"></label>
                        <label class="review-score"></label>
                    </div>
                </div>`;
    reviews.forEach(review => {
        text += `
                <div id="reply_wrap">
                    <div id="reply_wrap_div">
                        <div style="display: flex; justify-content: space-between;">
                            <a style="display: flex; width: 0;">
                                <div style="display: inline-flex; align-items: center; margin-right: auto;">
                                    <div style="text-align: left; display: flex; align-items: center;">
                                        <div style="position: relative; margin-right: 7px;">
                                            <div id="reply_my">
                                                <div class="profile-image-login">
                                                    <div class="profile-member-status">
                                                    <!-- <span class="profile-image">유</span> -->
                                                    <img src="${memberImgFullPath || ''}" alt="">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div style="display: flex; flex-direction: column;">
                                        <div style="display: flex; margin-bottom: 3px; align-items: center;">
                                        <div id="reply_nickname">${review.memberName}</div>
                                    </div>
                                    <span id="reply_date">${review.reviewRegisterDate}</span>
                                    <div class="review-score-box">
                                        <label class="review-score"></label>
                                        <label class="review-score"></label>
                                        <label class="review-score"></label>
                                        <label class="review-score"></label>
                                        <label class="review-score"></label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </a>
                </div>
                <div id="float_delete">
                            수정&nbsp;&nbsp;&nbsp;삭제
                            </div>
                        <div id="reply_content">${review.reviewContent}</div>
                        </div>
                    </div>
                </div>
                </div>
                <div id="up_div">
                <button id="up_btn">
                <span id="up_btn_span">
                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="https://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" clip-rule="evenodd" d="M11.2674 5.29328L5.53033 11.0303C5.23744 11.3232 4.76256 11.3232 4.46967 11.0303C4.17678 10.7374 4.17678 10.2626 4.46967 9.96967L11.4697 2.96967C11.5063 2.93306 11.5457 2.90102 11.5873 2.87356C11.8784 2.68135 12.274 2.71339 12.5303 2.96967L19.5303 9.96967C19.8232 10.2626 19.8232 10.7374 19.5303 11.0303C19.2374 11.3232 18.7626 11.3232 18.4697 11.0303L12.7326 5.29328C12.744 5.34522 12.75 5.39918 12.75 5.45455V21.4545C12.75 21.8688 12.4142 22.2045 12 22.2045C11.5858 22.2045 11.25 21.8688 11.25 21.4545V5.45455C11.25 5.39918 11.256 5.34522 11.2674 5.29328ZM11.8387 4.72193C11.8907 4.71054 11.9446 4.70455 12 4.70455C12.0554 4.70455 12.1093 4.71054 12.1613 4.72193L12 4.56066L11.8387 4.72193Z" fill="black"></path>
                    </svg>
                </span>
            </button>
        </div>
    </div>`;
    });
    $('#all_wrap').append(text);
    }*/