/* 작성글 목록 */
function showBoardList(boards) {
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
                    </label>
                </li>
			`;
    });
    $ul.append(text);
    let modalBtn = $('.modal_button');
    modalClickFunc(modalBtn);
}

function modalClickFunc(modalBtn) {
    $(modalBtn).on("click", function (e) {
        alert($(e.currentTarget));
        console.log($(e.currentTarget));
    })
}


// function showBoardsAppend(boards) {
//     let text = ``;
//     boards.forEach(board => {
//         text += `
//                 <li class="info-box-layout"  th:object="${board}">
//                     <label class="modal_button" name="${board.boardBusinessId}">
//                         <div class="image-box">
//                             <img src="/imgs/business/display?fileName=${board.boardBusinessImgPath + '/t_' + board.boardBusinessImgUuid + '_' + board.boardBusinessImgOriginalName}" class="info-image">
//                         </div>
//                         <div class="info-section-box">
//                             <div class="cate-title-box">
//                                 <span class="cate-title-style">
//                                     <span class="cate-title"> ${board.businessCategory}</span>
//                                 </span>
//                             </div>
//                             <p class="circulation-title">${board.boardBusinessTitle}</p>
//                         </div>
//                         <div class="location-box">
//                             <div class="location-box-layout">${board.businessLocation}</div>
//                         </div>
//                         <div class="review-count-box">
//                             <div class="icon-box">
//                                 <div class="review-icon-box">
//                                     <img src="/image/boardList/review_icon.png" class="review-icon">
//                                 </div>
//                                 <div>
//                                     <span class="review-count">${board.boardBusinessReviewCount}</span>
//                                 </div>
//                             </div>
//                             <div class="icon-box">
//                                 <div class="review-icon-box">
//                                     <img src="/image/boardList/star_icon.png" class="review-grade-icon">
//                                 </div>
//                                 <div>
//                                     <span class="review-grade-count">${board.boardBusinessGradeAverage}</span>
//                                 </div>
//                             </div>
//                         </div>
//                     </label>
//                 </li>
// 			`;
//     });
//     $($('ul.boardList-info-box')[0]).append(text);
// }

// /* 리스트 삽입 js */
// function showSubBoardsList(boards) {
//     $($('ul.boardList-info-box')[0]).empty();
//     let text = ``;
//     boards.forEach(board => {
//         text += `
//            <li class="info-box-layout"  th:object="${board}">
//                 <label class="modal_button" name="${board.boardBusinessId}">
//                 <!-- 상세보기 모달 버튼 -->
//                     <div class="image-box">
//                         <img src="/imgs/business/display?fileName=${board.boardBusinessImgPath + '/t_' + board.boardBusinessImgUuid + '_' + board.boardBusinessImgOriginalName}" class="info-image">
//                     </div>
//                     <div class="info-section-box">
//                         <div class="cate-title-box">
//                             <span class="cate-title-style">
//                                 <span class="cate-title">${board.businessCategory}</span>
//                             </span>
//                         </div>
//                         <p class="circulation-title">${board.boardBusinessTitle}</p>
//                     </div>
//                     <div class="location-box">
//                         <div class="location-box-layout">${board.businessLocation}</div>
//                     </div>
//                     <div class="review-count-box">
//                         <div class="icon-box">
//                             <div class="review-icon-box">
//                                 <img src="/image/boardList/review_icon.png" class="review-icon">
//                             </div>
//                             <div>
//                                 <span class="review-count">${board.boardBusinessReviewCount}</span>
//                             </div>
//                         </div>
//                         <div class="icon-box">
//                             <div class="review-icon-box">
//                                 <img src="/image/boardList/star_icon.png" class="review-grade-icon">
//                             </div>
//                             <div>
//                                 <span class="review-grade-count">${board.boardBusinessGradeAverage}</span>
//                             </div>
//                         </div>
//                     </div>
//                 </label>
//             </li>
// 			`;
//     });
//     $($('ul.boardList-info-box')[0]).append(text);
// };