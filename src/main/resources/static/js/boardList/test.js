let memberService = (function(){
    function memberInfo(memberId, callback){
        $.ajax({
            url : "/myPages/memberInfo",
            type: "get",
            data : memberId,
            success : function(memberVO){
                if(callback){
                    callback(memberVO);
                }
            }
        });
    }

    function businessInfo(businessId, callback) {
        $.ajax({
            url : "/myPages/business/businessInfo",
            type: "get",
            data : businessId,
            success : function(businessVO){
                if(callback){
                    callback(businessVO);
                }
            }
        });
    }

    return {memberInfo : memberInfo, businessInfo : businessInfo}
})();