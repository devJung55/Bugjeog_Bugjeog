let likeService = (function(){

    // 좋아요 했는지 여부 확인
    function likeCheck(like, callback){
        $.ajax({
            url:"/likes/like-check",
            type: "post",
            data: JSON.stringify(like),
            contentType: "application/json;charset=utf-8",
            success: function(result){
                if(callback){
                    callback(result);
                }
            }
        });
    }

    // 좋아요 카운트 ++
    function likeUp(like,callback) {
        $.ajax({
            url:"/likes/likeUp",
            type: "post",
            data: JSON.stringify(like),
            contentType: "application/json; charset=utf-8",
            success: function(result){
                if(callback){
                    callback(result);
                }

            }
        });
    }

    // 좋아요 카운트 --
    function likeDown(like, callback) {
        $.ajax({
            url: "/likes/likeDown",
            type: "delete",
            data: JSON.stringify(like),
            contentType: "application/json; charset=utf-8",
            success: function (result) {
                if (callback) {
                    callback(result);
                }
            }
        });
    }

    // 좋아요 갯수 업데이트
    function countUp(like,callback) {
        $.ajax({
            url:"/likes/likeCountUpdate",
            type :"patch",
            data: like,
            success : function (result) {
                if (callback){
                    callback(result);
                }
            }
        });
    }

    // 게시판의 좋아요 갯수
    function count(like, callback){
        $.ajax({
            url:"/likes/likeCount",
            type :"get",
            data: like,
            success : function(result){
                if(callback){
                    callback(result);
                }
            }
        });
    }

    return{
        likeCheck : likeCheck,
        likeUp : likeUp,
        likeDown : likeDown,
        countUp : countUp,
        count : count
    }
})();

$(".liked").each((i, e) => {
    let boardFreeId = $(e).attr("id");
    let freeLikeVO = new Object();
    $(e).click(() =>{
        freeLikeVO.memberId = memberId;
        freeLikeVO.businessId = businessId;
        freeLikeVO.boardFreeId = boardFreeId;

        likeService.likeCheck(freeLikeVO, function(result){
            if(result){
                likeService.likeUp(freeLikeVO,function(){
                    $(e).attr("src", "/image/mypage/like_after.png");
                    likeUpdateAndCount(boardFreeId, i);
                });
            }else {
                likeService.likeDown(freeLikeVO,function() {
                    $(e).attr("src", "/image/mypage/like_before.png");
                    likeUpdateAndCount(boardFreeId, i);
                });
            }
        })
    });
});

function likeUpdateAndCount(boardFreeId, i){
    likeService.countUp({boardFreeId: boardFreeId});
    likeService.count({boardFreeId : boardFreeId},function(result){
        $($(".like-count")[i]).text(result);
    })
}