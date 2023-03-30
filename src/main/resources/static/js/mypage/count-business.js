const $freeCount = $(".freeCount");
const $replyCount = $(".replyCount");
const $likeCount = $(".likeCount");
const $inquireCount = $(".inquireCount");
const $reviewGrade = $(".review-grade");
const $reviewCount = $(".review-count");

function count() {
    $.ajax({
        url: "/mypages/count",
        type: "get",
        success: function (count) {
            $freeCount.text(count.freeBoardCount);
            $inquireCount.text(count.inquireCount);
            $likeCount.text(count.likeBoardCount);
            $replyCount.text(count.replyCount);
            $reviewGrade.text(count.reviewGrade);
            $reviewCount.text(count.reviewCount);
        }
    });
}
count();