/* 리스트 삽입 js */
function showDetail(answers) {
    let text = ``;
    $('#reply_img_wrap').empty();
    console.log(answers);
    if (answers.length == 0) {
        text = `
           <div th:if="${answers.length == 0}" style="text-align: center;">
               <img id="reply_img" src="https://static.wanted.co.kr/images/community/community-3d-comment.png"
                    alt="">
               <p id="reply_content">현재 검토중 입니다.</p>
           </div>
        `;
        $('#reply_img_wrap').addClass("autoHeight");
        $('#reply_img_wrap').append(text);
        return;
    }
    text += `
        <div>
            <ul class="answer_ul" id="answer_wrap">
    `
    answers.forEach(answer => {
        console.log(answer);
        text += `
                <li>
                    <div class="reply_wrap_div">
                        <div class="time_wrap">
                            <span class="reply_date">답변시각 : ${answer.boardInquiryAnswerRegisterDate}</span>
                        </div>
                        <div class="reply_content">${answer.boardInquiryAnswerContent}</div>
                    </div>
                </li>
        `;
    });
    text += `
            </ul>
        </div>
    `
    $('#reply_img_wrap').removeClass("autoHeight");
    $('#reply_img_wrap').append(text);
    return;
}

