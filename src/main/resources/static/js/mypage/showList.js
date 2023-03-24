/* 작성글 목록 */
function showListPost(boards) {
    boards = JSON.parse(boards); /* boards 정의해야 함 */
    const $ul = $boardListWrapper;
    let text = "";
    boards.forEach(board => {
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

function showListReply() {
    boards = JSON.parse(boards);
    const $ul = $boardListWrapper;
    let text = "";
    boards.forEach(board => {
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