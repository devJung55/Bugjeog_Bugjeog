/* 리스트 삽입 js */
function showDetail(answers) {
    $ul.empty();
    let text = ``;
    console.log(answers);
    if(answers.length == 0){
        text = `
           <div th:if="${answers.length == 0}">
               <img id="reply_img" src="https://static.wanted.co.kr/images/community/community-3d-comment.png"
                    alt="">
               <p id="reply_content">현재 검토중 입니다.</p>
           </div>
        `;
        $('#reply_img_wrap').append(text);
        return;
    }
    answers.forEach(answer => {
        text += `
            <div style="display: flex; justify-content: space-between;">
                <a style="display: flex; width: 0;">
                    <div style="display: inline-flex; align-items: center; margin-right: auto;">
                        <div style="text-align: left; display: flex; align-items: center;">
                            <div style="position: relative; margin-right: 7px;">
                                <div class="reply_my">
                                    <div class="profile-image-login">
                                        <div class="profile-member-status">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </a>
            </div>
        `;
    });
    $ul.append(text);
}

