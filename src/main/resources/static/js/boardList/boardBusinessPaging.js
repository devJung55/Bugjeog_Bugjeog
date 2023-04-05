function boardBusinessPagingFunc($pagingWrap, pageDTO) {
    $pagingWrap.empty();
    let text = '';

    text += `
                    <li class="page-button-box">
                        <a href="javascript:ajaxPaging(1)" class="pagingNum" name="pagingFirst_1"><<</a>
                    </li>`;
    if (`${pageDTO.prev}`) {
        text +=
            `
                    <li class="page-button-box">
                        <a href="javascript:ajaxPaging(${pageDTO.startPage - 5})" class="pagingNum" name="pagingPrev_${pageDTO.startPage - 5}"><</a>
                    </li>`;
    }

    let length = `${pageDTO.criteria.pageNum}`;
    for (let i = 0; 0 < length; i++) {
        text += `
                    <li class="page-button-box">
                        <a href="javascript:ajaxPaging(${pageDTO.startPage + i})" class="pagingNum" name="pagingList_${pageDTO.startPage + i}">${pageDTO.startPage + i}</a>
                    </li>
                    `;
    }

    if (`${pageDTO.next}`) {
        text += `
                    <li class="page-button-box">
                        <a href="javascript:ajaxPaging(${pageDTO.endPage + 1})" class="pagingNum" name="pagingNext_${pageDTO.endPage + 1}">></a>
                    </li>
                    `;
    }

    text += `
                    <li class="page-button-box">
                        <a href="javascript:ajaxPaging(${pageDTO.realEnd})" class="pagingNum" name="pagingEnd_${pageDTO.realEnd}">>></a>
                    </li>
        `
    $pagingWrap.append(text);
}

// function ajaxPaging(startPage) {
//     $.ajax({
//         url: "/board/inquiry/list/ajax",
//         method: "GET",
//         data: {"startPage": JSON.parse(startPage)},
//         async: false,
//         contentType: "application/json; charset=utf-8",
//         success: function (result) {
//             let setUp = JSON.parse(result);
//             let boards = JSON.parse(setUp.boards);
//             let pageDTO = JSON.parse(setUp.pageDTO);
//             showInquiryList(boards);
//             pagingFunc(pageDTO);
//         },
//         error: function (e) {
//             console.log($(e));
//         }
//     })
// }