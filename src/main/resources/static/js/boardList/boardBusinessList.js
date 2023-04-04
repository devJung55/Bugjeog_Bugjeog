/* 작성글 목록 */
function showBoardsList(boards) {

    $($('ul.boardList-info-box')[0]).empty();
    let text = ``;
    boards.forEach(board => {
        console.log(`${board.boardBusinessImgFullPath}`);
        text += `
                <li class="info-box-layout"  th:object="${board}">
                    <a href="/board/business/detail?boardId=${board.boardBusinessId}">
                        <div class="image-box">
                            <img src="/imgs/business/display?fileName=${board.boardBusinessImgPath + '/t_' + board.boardBusinessImgUuid + '_' + board.boardBusinessImgOriginalName}" class="info-image">
                        </div>
                        <div class="info-section-box">
                            <div class="cate-title-box">
                                <span class="cate-title-style">
                                    <span class="cate-title"> ${board.businessCategory}</span>
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
                    </a>
                </li>
			`;
    });
    $($('ul.boardList-info-box')[0]).append(text);

}