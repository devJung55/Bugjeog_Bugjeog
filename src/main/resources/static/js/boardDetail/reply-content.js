let getTimePassed = function (writtenDate, writtenTime) {
    const [yyyy, mm, dd] = writtenDate.split("-");
    const [hh, MM, ss] = writtenTime.split(":");
    const currentTime = new Date();
    const givenTime = new Date();
    givenTime.setFullYear(yyyy, mm - 1, dd);
    givenTime.setHours(hh, MM, ss);

    const betweenTime = Math.floor((currentTime.getTime() - givenTime.getTime()) / 1000 / 60);

    if (betweenTime < 1) return '방금전';
    if (betweenTime < 60) {
        return `${betweenTime}분전`;
    }

    const betweenTimeHour = Math.floor(betweenTime / 60);
    if (betweenTimeHour < 24) {
        return `${betweenTimeHour}시간전`;
    }

    const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
    if (betweenTimeDay < 30) {
        return `${betweenTimeDay}일전`;
    }

    const betweenTimeMonth = Math.floor(betweenTimeDay / 30);
    if (betweenTimeMonth < 12) {
        return `${betweenTimeMonth}개월전`;
    }

    const betweenTimeYear = Math.floor(betweenTimeDay / 365);
    return `${betweenTimeYear}년전`;
}

function count(boardFreeId){
    replyService.count({boardFreeId : boardFreeId}, function(count){
        $(".reply-count").text(count);
    })
}

/*const를 사용하여 아래 사용할 변수를 선언 */
const $replyRegisterButton = $(".reply-register-button");

replyService.getList(page);
count(currentBoard.boardFreeId);

const $replyWrap = $("#reply_wrap_div");

$replyWrap.on("click", "div.update", function () {
    const replyContents = $("div.reply_content");
    let i = $("div.update").index(this);
    const replyContent = $($(".reply_content")[i]).text();

    $($(".modify-reply-box")[i]).show();
    $($(".cancel")[i]).show();
    $($(".update")[i]).hide();

    $($(".new-reply-content")[i]).val(replyContent);
    $($(".reply_content")[i]).css("display", "none");
});

// 등록
$replyWrap.on("click", "button.save-reply", function () {
    let i = $("button.save-reply").index(this);
    let replyVO = new Object();
    replyVO.replyId = $($("div.update")[i]).data("reply-id");
    replyVO.replyContent = $($(".new-reply-content")[i]).val();

    replyService.update(replyVO, function () {
        $("#reply_textarea").val("");
        replyService.getList(page);
        count(currentBoard.boardFreeId);
    });
});

// 수정 취소
$replyWrap.on("click", "button.cancelBtn", function () {
    let i = $("button.cancelBtn").index(this);
    $($(".cancel")[i]).hide();
    $($(".modify-reply-box")[i]).hide();
    $($(".modifyBtn")[i]).show();
    $($(".update")[i]).show();
    $($(".reply_content")[i]).show();

});

// 등록
$replyRegisterButton.click(function () {
    const replyContent = $("#reply_textarea").val();
    const boardFreeId = currentBoard.boardFreeId;

    if(!memberId && !businessId){
        alert("로그인을 먼저 해주세요.");
        return false;
    }

    replyService.save({replyContent: replyContent, boardFreeId: boardFreeId}, function(){
        $("#reply_textarea").val("");
        replyService.getList(page);
        count(boardFreeId);
    });
});

$replyWrap.on("click", "button.deleteBtn", function () {
    let i = $("button.deleteBtn").index(this);
    const replyId = $($("div.update")[i]).data("reply-id");
    replyService.Delete({replyId : replyId}, function(){
        replyService.getList(page);
        count(currentBoard.boardFreeId);
    });
});

$replyWrap.on("click", "button#save-reply", function(){
    let i = $("button#save-reply").index(this);
    let replyId = $($("div.update")[i]).data("reply-id");
    let replyContent = $($(".new-reply-content")[i]).val();

    replyService.update({replyId : replyId, replyContent : replyContent}, function(){
        $($(".modify-reply-box")[i]).hide();
        replyService.getList(page);
    })
});

function showList(list){
    if(list.length == 0){
        $("#reply_img_wrap").show();
    }else {
        $("#reply_img_wrap").hide();
    }

    let text = "";
    list.forEach( reply => {
        let writtenDate = reply.replyRegisterDate.split(" ")[0];
        let writtenTime = reply.replyRegisterDate.split(" ")[1];
        text +=
            `
                     <div style="margin-bottom: 20px">
                            <div style="display: flex; justify-content: space-between; margin-bottom: 10px">
                                <a style="display: flex;">
                                    <div style="display: inline-flex; align-items: center; margin-right: auto;">
                                        <div style="text-align: left; display: flex; align-items: center;">
                                            <div style="position: relative; margin-right: 7px;">
                                                <div id="reply_my">
                                                    <div class="profile-image-login">
                                                        <div class="profile-member-status">
                     `

        if(reply.memberId){
            if(!reply.memberImgUuid){
                text += `<img class="profile-image" src="/image/boardList/self_employ_icon.png">`;
            }else {
                text += `<img src="${'/mypage/profile/display?fileName=' + reply.memberImgPath + '/' + reply.memberImgUuid + '_' + reply.memberImgOriginalName}">`;
            }
        }else {
            if(!reply.businessImgUuid){
                text += `<img class="profile-image" src="/image/boardList/distributor_icon.png">`;
            }else {
                text += `<img src="${'/mypage/profile/display?fileName=' + reply.businessImgPath + '/' + reply.businessImgUuid + '_' + reply.businessImgOriginalName}">`;
            }
        }
        text+=  `                                  </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div style="display: flex; flex-direction: column;">
                                                <div style="display: flex; margin-bottom: 3px; align-items: center;">
                                                  <div id="reply_nickname"><span>${reply.memberName}</span></div>
                                                </div>
                                                <span id="reply_date" style="font-size: 11px;">${getTimePassed(writtenDate,writtenTime)}</span>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                     `
        if((memberId != businessId && memberId == reply.memberId) && (memberId != businessId && businessId == reply.businessId)){
            text +=
                `
                                    <div id="float_delete" style="display: flex">
                                        <div class="update" data-reply-id="${reply.replyId}">
                                           <button type="button" class="modifyBtn">
                                            <span>수정</span>
                                            </button>
                                        </div>
                                        <div class="cancel">
                                            <button type="button" class="cancelBtn">
                                                <span>취소</span>
                                             </button>
                                        </div>
                                        <div style="margin-left: 8px" class="delete" data-reply-id="${reply.replyId}">
                                            <button type="button" class="deleteBtn" >
                                            <span>삭제</span>
                                            </button>
                                        </div>
                                    </div>
                        `
        }
        text += `
                        </div>
                        <div class="reply_content">${reply.replyContent}</div>
                        <div class="modify-reply-box" style="padding-left: 44px;">
                            <div style="display: flex; position:relative; width:100%; height: 30px; border-radius: 10px; border: 1px solid #e1e2e3;">
                                <input type="text" placeholder="수정할 댓글을 입력해주세요." class="new-reply-content" style="width: 100%; outline: none; border:none; padding: 3px 5px; background: none;">
                                <div style="position: absolute; right: 8px; top: 8px; display: flex; font-size:13px;">
                                    <button type="button" id="save-reply">
                                        완료
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                `
    });
    $("#reply_wrap_div").html(text);
}

$(document).ready(function () {
    /*등록 버튼 초기 색상 설정*/
    $('.reply-register-button').css("color", "rgb(196, 196, 196)").css("background-color", "rgb(242, 244, 247)");

    /*textarea 입력시 등록 버튼 색상 변경*/
    $('#reply_textarea').on('input', function () {
        if ($(this).val().length > 0) {
            $('.reply-register-button').css("color", "#fff");
            $('.reply-register-button').css("background-color", "#36f");
        } else {
            $('.reply-register-button').css("color", "rgb(196, 196, 196)").css("background-color", "rgb(242, 244, 247)");
        }
    });
});