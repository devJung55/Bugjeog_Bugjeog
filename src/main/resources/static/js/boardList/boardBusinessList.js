function showList(category) {
    /* 작성글 목록 */
    // const boards = JSON.parse($boards); /* boards 정의해야 함 */
    console.log("들2");
    const $ul = $($('ul.boardList-info-box')[0]);
    console.log($ul);
    $ul.empty();
    let text = ``;
    ${board}.forEach(board => {
        // src = contextPath + "/static/img/board/noImage.png";
        text += `
                <li class="info-box-layout"  th:object="${boardBusinessDTO}">
                    <a href="/board/business/detail?boardId=${board.boardBusinessId}">
                        <div class="image-box">
                            <img src="${board.boardBusinessImgPath}/${boardBusinessImgUuid}_${board.boardBusinessImgOriginalName}" class="info-image">
                        </div>
                        <div class="info-section-box">
                            <div class="cate-title-box">
                                <span class="cate-title-style">
                                    <span class="cate-title"> ${board.boardBusinessCategory}</span>
                                </span>
                            </div>
                            <p class="circulation-title"> ${board.boardBusinessTitle}</p>
                        </div>
                        <div class="location-box">
                            <div class="location-box-layout">${board.boardBusinessLocation}</div>
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
    $ul.append(text);
}