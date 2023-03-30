let $pageForm = $(pageForm);

$("a.changePage").on("click", function(e){
    e.preventDefault();
    $pageForm.find("input[name='pageNum']").val($(this).attr("href"));
    $pageForm.submit();
});


/* delete 버튼 클릭 시 ajax실행*/
$('.delete-button').on('click', function() {
    var checkedIds = new Array();
    // 체크 박스 체크된 값
    $('input:checkbox[name=check]:checked').parent().next().each(function(){
        console.log(this.innerText);
        checkedIds.push(this.innerText);
    });

    console.log(checkedIds);

    $.ajax({
        url: "/admin/admin-deleteMember",
        type: "delete",
        data: {"checkedIds": checkedIds},
        success: function(){
            reload();
        }
    });
});

function showList(members){
    const $result = $('.info-table table');
    var text = "";
    members.forEach(member => {
        text += `
				<tr th:each="member : ${memberDTO}">
					<td>
						<input type="checkbox" name="check">
					</td>
					<td >${member.memberId}</td>
					<td><a href="{admin-member/{memberId}(memberId=${member.memberId})}">${member.memberName}</a></td>
						<td>${member.memberEmail}</td>
					<td>${member.memberRegisterDate}</td>
					<td>${member.memberStatus}</td>
				</tr>
			`
    })
    $result.html(text);
}

function reload() {
    $.ajax({
        url:"admin-member-companyList",
        type: "post",
        success: function(result){
            showList(result);
        }

    });
}

window.onload = reload();
