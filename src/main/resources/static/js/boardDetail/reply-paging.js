/* 페이지 찾기 */		/**/
function findPage(currentPage) {
    page = currentPage.dataset.page;
    page *= 1;
    replyService.getList(page);
}

// 페이지 이동		/**/
function movePage(e) {
    // 페이지버튼 전체
    const $buttons = $(".page-button");
    // 현재 페이지가 표시된 버튼
    var $pushedBtn = $(e);
    // 나머지 버튼들에 일반상태 일때 버튼 클래스로 다 주고
    $buttons.attr('class', 'page-button');
    // 클릭된 버튼에만 클릭 됐을 때 클래스로 변경
    $pushedBtn.attr('class', 'page-button-active page-button');

    // 내가 누른 버튼의 텍스트 가져오기 ex) 3번 페이지 누르면 3
    page = $(e).children().children().children().text();

    replyService.getList(page);
}

// 페이지 리스트 뿌리기		/**/
function showPage(criteria) {
    let text = "";

    // 전체 버튼 wrapper
    text+=	`
				<div class="page-button-box">
				`;

    // criteria에 있는 이전 컬럼의 필드 값을 조회해서 prev가 true면 이전 페이지가 있어야하니깐 이전 버튼 생성
    if (criteria.prev){
        text += `
					<div class="">
					  <div class="page-button-margin">
						<div>
						  <img src="/image/admin/left_icon.png" data-page="${criteria.startPage - 1}" class="left-button" onclick="findPage(this)">
						</div>
					  </div>
					</div>
					`;
    }

    // 반복문 돌려서 페이지 이동버튼 뿌려주기
    for (let i = criteria.startPage; i <= criteria.endPage; i++){
        text += `
					<div style="margin-right: 3px; margin-left: 3px;" class="${criteria.page === i ? 'page-button-active ' : ''} page-button" onclick="movePage(this)">
					  <div class="page-button-margin">
						<div>
						  <span>${i}</span>
						</div>
					  </div>
					</div>
					`;
    }

    // criteria에 있는 다음 컬럼의 필드 값을 조회해서 next가 true면 다음 페이지가 있어야하니깐 다음 버튼 생성
    if(criteria.next){
        text += `
					<div class="">
					  <div class="page-button-margin">
						<div>
						  <img src="/image/admin/right_icon.png" data-page="${criteria.endPage + 1}" class="right-button" onclick="findPage(this)">
						</div>
					  </div>
					</div>
		  	</div>
			`;
    }
    $('.paging').html(text);
}