/*댓글*/
let page = 1;

const replyService = (() => {
    /*댓글 목록*/
    function getList(page, callback) {
        $.ajax({
            url: `/replies/list/${page}?boardFreeId=${currentBoard.boardFreeId}`,
            type : "get",
            success: function (replies) {
                if (callback) {
                    callback(replies);
                }
                showList(replies.replyDTO);
                showPage(replies.criteria);
            },
            error(){
                console.log("에러");
            }
        });
    }
    /*댓글 등록*/
    function save(reply, callback){
        $.ajax({
            url: "/replies/register-reply",
            type: "post",
            data: JSON.stringify(reply),
            contentType: "application/json;charset=utf-8",
            success: function(){
                if(callback){
                    callback();
                }
            }
        })
    }
    /*댓글 수정 + 수정된 데이터 화면에 뿌려줌*/
    function update(reply, callback){
        $.ajax({
            url: "/replies/update-reply",
            type: "patch",
            data: JSON.stringify(reply),
            contentType: "application/json;charset=utf-8",
            success: function(){
                if(callback){
                    callback();
                }
            }
        })
    }

    /*댓글 수정 필요?*/

    /*댓글 삭제*/
    function Delete(reply, callback){
        $.ajax({
            url:"/replies/delete-reply",
            type: "delete",
            data: reply ,
            success: function(){
                if(callback){
                    callback();
                }
            }
        })
    }

    // 댓글 갯수
    function count(reply, callback){
        $.ajax({
            url : "/replies/count",
            type: "get",
            data : reply,
            success : function(count){
                if(callback){
                    callback(count);
                }
            }
        });
    }

    return {getList: getList, save: save, update: update, Delete: Delete, count : count};
})();