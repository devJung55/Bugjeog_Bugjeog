/* 작성글 목록 */
function showListPost(lists) {
    lists = JSON.parse(lists); /* boards 정의해야 함 */
    const $ul = $boardListWrapper;
    let text = "";
    lists.forEach(list => {
        // src = contextPath + "/static/img/board/noImage.png";
        text += `
                <article class="PostItem_wrapper">
                    <div class="PostItem_top">
                        <a href="">
                            <div class="AuthorBox_Wrapper">
                                <div class="AuthorBox_horizontalBox">
                                    <div class="AuthorBox_avatarWrapper">
                                        <div class="AuthorBox_avatar">
                                            <img src="https://static.wanted.co.kr/open_profile/avatar/bb7fc620aaa234ed5da383ee06d9b6c2a4d331dd6b105f70b80c20113b6e9f00"
                                                alt="">
                                        </div>
                                    </div>
                                    <span class="AuthorBox_username">what0</span>
                                </div>
                                <span
                                    class="AuthorBox_createAt">2023.03.13</span>
                            </div>
                        </a>
                    </div>
                    <a class="PostItem_body" href="">
                        <h3 class="PostItem_title">
                        </h3>
                        <p class="PostItem_content">
                        </p>
                        <div class="PostItem_bottom">
                            
                            <div class="PostItem_like">
                                <img src="" alt="">
                                <span class="button_count">2</span>
                            </div>
                            <div class="PostItem_comments">
                                <img src="" alt="">
                                <span class="button_count">2</span>
                            </div>
                        </div>
                    </a>
                </article>
			`;
    });
    $ul.append(text);
}

function showListComment(lists) {
    lists = JSON.parse(lists);
    const $ul = $boardListWrapper;
    let text = "";
    lists.forEach(list => {
        // src = contextPath + "/static/img/board/noImage.png";
        text += `
                <article class="PostItem_wrapper">
                    <div class="PostItem_top">
                        <a href="">
                            <div class="AuthorBox_Wrapper">
                                <div class="AuthorBox_horizontalBox">
                                    <div class="AuthorBox_avatarWrapper">
                                        <div class="AuthorBox_avatar">
                                            <img src="https://static.wanted.co.kr/open_profile/avatar/bb7fc620aaa234ed5da383ee06d9b6c2a4d331dd6b105f70b80c20113b6e9f00"
                                                alt="">
                                        </div>
                                    </div>
                                    <span class="AuthorBox_username">what0</span>
                                </div>
                                <span class="AuthorBox_createAt">2023.03.13</span>
                                <h3 class="Author_title">
                                    합격통보 받고 한달안에 안간다고 연락드려도 되나요..?
                                </h3>
                            </div>
                        </a>
                    </div>
                    <a class="PostItem_body" href="">
                        <p class="PostItem_content">
                            합격 통보받고 한달동안 기간있는데 그 기간안에 원하던곳에 합격하면 보통
                            뭐라고 연락드리나요..?그냥 날렸다 생각하고 진행하시나요..?
                        </p>
                        <div class="PostItem_bottom">
                            <div class="PostItem_like">
                                <img src="/image/mypage/like_before.png" alt="">
                                <span class="button_count">2</span>
                            </div>
                            <div class="PostItem_comments">
                                <img src="" alt="">
                                <span class="button_count">2</span>
                            </div>
                        </div>
                    </a>
                </article>
			`;
    });
    $ul.append(text);
}

function showListLike(lists) {
    lists = JSON.parse(lists);
    const $ul = $boardListWrapper;
    let text = "";
    lists.forEach(list => {
        // src = contextPath + "/static/img/board/noImage.png";
        text += `
                <article class="PostItem_wrapper">
                    <div class="PostItem_top">
                        <a href="">
                            <div class="AuthorBox_Wrapper">
                                <div class="AuthorBox_horizontalBox">
                                    <div class="AuthorBox_avatarWrapper">
                                        <div class="AuthorBox_avatar">
                                            <img src="https://static.wanted.co.kr/open_profile/avatar/bb7fc620aaa234ed5da383ee06d9b6c2a4d331dd6b105f70b80c20113b6e9f00"
                                                alt="">
                                        </div>
                                    </div>
                                    <span class="AuthorBox_username">what0</span>
                                </div>
                                <span
                                    class="AuthorBox_createAt">2023.03.13</span>
                            </div>
                        </a>
                    </div>
                    <a class="PostItem_body" href="">
                        <h3 class="PostItem_title">
                        </h3>
                        <p class="PostItem_content">
                        </p>
                        <div class="PostItem_bottom">
                            
                            <div class="PostItem_like">
                                <img src="" alt="">
                                <span class="button_count">2</span>
                            </div>
                            <div class="PostItem_comments">
                                <img src="" alt="">
                                <span class="button_count">2</span>
                            </div>
                        </div>
                    </a>
                </article>
			`;
    });
    $ul.append(text);
}

function showListInquiry(lists) {
    lists = JSON.parse(lists);
    const $ul = $boardListWrapper;
    let text = "";
    lists.forEach(list => {
        // src = contextPath + "/static/img/board/noImage.png";
        text += `
                <article class="PostItem_wrapper">
                    <div class="PostItem_top">
                        <a href="">
                            <div class="AuthorBox_Wrapper">
                                <div class="AuthorBox_horizontalBox">
                                    <div class="AuthorBox_avatarWrapper">
                                        <div class="AuthorBox_avatar">
                                            <img src="https://static.wanted.co.kr/open_profile/avatar/bb7fc620aaa234ed5da383ee06d9b6c2a4d331dd6b105f70b80c20113b6e9f00"
                                                alt="">
                                        </div>
                                    </div>
                                    <span class="AuthorBox_username">what0</span>
                                </div>
                                <span
                                    class="AuthorBox_createAt">2023.03.13</span>
                            </div>
                        </a>
                    </div>
                    <a class="PostItem_body" href="">
                        <h3 class="PostItem_title">
                            합격통보 받고 한달안에 안간다고 연락드려도 되나요..?
                        </h3>
                        <p class="PostItem_content">
                            합격 통보받고 한달동안 기간있는데 그 기간안에 원하던곳에 합격하면 보통
                            뭐라고 연락드리나요..?그냥 날렸다 생각하고 진행하시나요..?
                        </p>
                        <div class="PostItem_bottom">
                            <div class=" is_answer answer_no">
                                <span>미답변</span>
                            </div>
                            <div class="is_answer answer_yes">
                                <span>답변 완료</span>
                            </div>
                        </div>
                    </a>
                </article>
			`;
    });
    $ul.append(text);
}