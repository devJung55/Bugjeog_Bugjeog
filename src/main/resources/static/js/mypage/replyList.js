const $replyList = $(".reply-list");

function replyList(){
    let text = "";
    if(boardFreeVOS.length == 0){
        text += `
                <article class="PostItem_wrapper no-content">
                <div style="text-align: center;">
                <h2>댓글을 단 게시글이 없습니다.</h2>
                </div>
                </article>
            `;
    }else {
        for (var i = 0; i < boardFreeVOS.length; i++) {
            let memberIdCheck = memberVOs[i] == null;

            text += `
                            <article class="PostItem_wrapper">
                                <div class="PostItem_top">
                                        <div class="AuthorBox_Wrapper">
                                            <div class="AuthorBox_horizontalBox">
                                                <div class="AuthorBox_avatarWrapper">
                                                    <div class="AuthorBox_avatar">
                        `;
            if (!memberIdCheck) {
                if(memberVOs[i].memberImgUuid == null){
                    text += `<img class="writer-image" src="/image/boardList/self_employ_icon.png">`;
                }else {
                    text += `<img src="${'/mypage/profile/display?fileName=' + memberVOs[i].memberImgPath + '/' + memberVOs[i].memberImgUuid + '_' + memberVOs[i].memberImgOriginalName}">`;
                }
            } else {
                if(businessVOs[i].businessImgUuid == null){
                    text += `<img class="writer-image" src="/image/boardList/distributor_icon.png">`;
                }else {
                    text += `<img src="${'/mypage/profile/display?fileName=' + businessVOs[i].businessImgPath + '/' + businessVOs[i].businessImgUuid + '_' + businessVOs[i].businessImgOriginalName}">`;
                }
            }
            text += `                           </div>
                                                </div>
                                                <div>
                        `;
            if(!memberIdCheck){
                text += `<span class="AuthorBox_username">${memberVOs[i].memberName}</span>`;
            }else{
                text += `<span class="AuthorBox_username">${businessVOs[i].businessCompanyName}</span>`;
            }
            text += `                      </div>
                                                <div>
                                                    <span class="AuthorBox_createAt">${date(boardFreeVOS[i].boardFreeRegisterDate)}</span>
                                                </div>
                                            </div>
                                            <h3 class="Author_title">${boardFreeVOS[i].boardFreeTitle}</h3>
                                        </div>
                                </div>
                    `;
                    text +=  `
                                <div class="reply-content-box" id="${boardFreeVOS[i].boardFreeId}">
                                        
                                </div>        
                             `;
             text +=  `           </article>
                `;
        }
    }
    $replyList.append(text);
}
    replyList();