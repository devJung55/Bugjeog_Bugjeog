const $freeCount = $(".freeCount");
const $replyCount = $(".replyCount");
const $likeCount = $(".likeCount");
const $inquireCount = $(".inquireCount");

function count() {
    $.ajax({
        url: "/mypage/profile/count",
        type: "get",
        success: function (count) {
            $freeCount.text(count.freeBoardCount);
            $inquireCount.text(count.inquireCount);
            $likeCount.text(count.likeBoardCount);
            $replyCount.text(count.replyCount);
        }
    });
}
count();