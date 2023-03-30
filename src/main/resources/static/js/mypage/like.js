let likeService = (function(){

    function likeCheck(like, callback){
        $.ajax({
            url:"/mypage/profile/like/like-check",
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

    function likeUp(like,callback) {
        $.ajax({
            url:"/mypage/profile/like/likeUp",
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

    function likeDown(like, callback) {
        $.ajax({
            url: "/mypage/profile/like/likeDown",
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

    function countUp(like,callback) {
        $.ajax({
            url:"/mypage/profile/like/likeCountUpdate",
            type :"patch",
            data: like,
            success : function (result) {
                if (callback){
                    callback(result);
                }
            }
        });
    }

    function count(like, callback){
        $.ajax({
            url:"/mypage/profile/like/likeCount",
            type :"post",
            data: JSON.stringify(like),
            contentType: "application/json; charset=utf-8",
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
                    likeService.countUp({boardFreeId : boardFreeId});
                    likeService.count(freeLikeVO,function(result){
                        $($(".like-count")[i]).text(result);
                    })
                });
            }else {
                likeService.likeDown(freeLikeVO,function() {
                    $(e).attr("src", "/image/mypage/like_before.png");
                    likeService.countUp({boardFreeId: boardFreeId});
                    likeService.count(freeLikeVO,function(result){
                        $($(".like-count")[i]).text(result);
                    })
                });
            }
        })
    });
});
