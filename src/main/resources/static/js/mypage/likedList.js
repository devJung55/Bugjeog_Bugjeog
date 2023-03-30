const $boardList = $(".board-list");

function likedList() {
    let text = "";
    if (freeVOs.length != 0) {
        for (var i = 0; i < freeVOs.length; i++) {

            text += `<article class="PostItem_wrapper">
                        <div class="PostItem_top">
                        <div class="AuthorBox_Wrapper">
                        <div class="AuthorBox_horizontalBox">
                        <div class="AuthorBox_avatarWrapper">
                        <div class="AuthorBox_avatar">
                       `;
            if (freeVOs[i].memberId != null) {
                if (memberVOs[i].memberImgUuid == null) {
                    text += `<img class="writer-image" src="/image/boardList/self_employ_icon.png">`;
                } else {
                    text += `<img src="${'/mypage/profile/display?fileName=' + memberVOs[i].memberImgPath + '/' + memberVOs[i].memberImgUuid + '_' + memberVOs[i].memberImgOriginalName}">`;
                }
            } else {
                if (businessVOs[i].businessImgUuid == null) {
                    text += `<img class="writer-image" src="/image/boardList/distributor_icon.png">`;
                } else {
                    text += `<img src="${'/mypage/profile/display?fileName=' + businessVOs[i].businessImgPath + '/' + businessVOs[i].businessImgUuid + '_' + businessVOs[i].businessImgOriginalName}">`;
                }
            }

            text += `  </div>
                          </div>
                       `;
            if (freeVOs[i].memberId != null) {
                text += `<span class="AuthorBox_username">${memberVOs[i].memberName}</span>`;
            } else {
                text += `<span class="AuthorBox_username">${businessVOs[i].businessCompanyName}</span>`;
            }

            text += `  </div>
                           <span class="AuthorBox_createAt">${date(freeVOs[i].boardFreeRegisterDate)}</span>
                       </div>
                   </div>
                   <div class="PostItem_body">
                       <div class="PostItem_header">
                           <a href="">
                               <h3 class="PostItem_title">${freeVOs[i].boardFreeTitle}</h3>
                               <p class="PostItem_content">${freeVOs[i].boardFreeContent}</p>
                           </a>
                       </div>
                       <div class="PostItem_bottom">
                           <div class="PostItem_like">
                               <label>
                                   <button style="display: none;"></button>
                                   <img src="/image/mypage/like_after.png" class="liked" id="${freeVOs[i].boardFreeId}">
                               </label>
                                   <span class="button_count like-count">${freeVOs[i].boardFreeLike}</span>
                           </div>
                           <div class="PostItem_comments">
                               <img src="/image/mypage/comment-dots-64.png">
                               <span class="button_count">${replyCounts[i]}</span>
                           </div>
                       </div>
                   </div>
               </article>
               `;
        }
    } else {
        text += `
                <article class="PostItem_wrapper no-content">
                     <div style="text-align: center;">
                         <h2>좋아요한 게시글이 없습니다.</h2>
                    </div>
                </article>
               `;
    }
    $boardList.append(text);
}

likedList();