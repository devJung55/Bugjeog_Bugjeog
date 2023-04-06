let freeLikeVO = new Object();
let boardFreeId = currentBoard.boardFreeId;

freeLikeVO.memberId = memberId;
freeLikeVO.businessId = businessId;
freeLikeVO.boardFreeId = boardFreeId;

likeService.count({boardFreeId: boardFreeId},function(result){
    $(".good-count").text(result);
})

likeService.likeCheck(freeLikeVO, function(result){
    if(result){
        $(".like").css("color", "black");
    }else {
        $(".like").css("color", "blue");
    }
});

$(".good-button").click(() => {
    if(!memberId && !businessId){
        alert("로그인을 먼저해주세요.");
        return false;
    }

    likeService.likeCheck(freeLikeVO, function(result){
        if(result){
            likeService.likeUp(freeLikeVO, function(){
                $(".like").css("color", "blue");
                likeUpdateAndCount(boardFreeId);
            })
        }else {
            likeService.likeDown(freeLikeVO, function(){
                $(".like").css("color", "black");
                likeUpdateAndCount(boardFreeId);
            });
        }
    });
});

function likeUpdateAndCount(boardFreeId) {
    likeService.countUp({boardFreeId: boardFreeId});
    likeService.count({boardFreeId: boardFreeId}, function (result) {
        $(".good-count").text(result);
    })
}
