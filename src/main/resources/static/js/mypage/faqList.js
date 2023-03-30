$(inquireDTO.answerStatus).each((i, e) => {
    let $bottom = $($(".PostItem_bottom")[i]);
    let text = "";
    if (e == null) {
        text += `
                    <div class=" is_answer">
                         <span>미답변</span>
                    </div>
                   `;
    } else {
        text += `
                         <div class="is_answer answer_yes">
                             <span>답변 완료</span>
                        </div>
            `;
    }
    $bottom.append(text);
});