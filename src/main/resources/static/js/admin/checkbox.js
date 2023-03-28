	$("#allSelect").click(function() {
	    if($("#allSelect").is(":checked")) $("input[name=check]").prop("checked", true);
	    else $("input[name=check]").prop("checked", false);
	});
	
	$("input[name=check]").click(function() {
	    var total = $("input[name=check]").length;
	    var checked = $("input[name=check]:checked").length;
	
	    if(total != checked) $("#allSelect").prop("checked", false);
	    else $("#allSelect").prop("checked", true); 
	});
	
	const pageButton = $(".page-button");







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
			url: "/admin/admin-delete",
			type: "delete",
			data: {"checkedIds": checkedIds},
			success: function(){
				location.reload();

			}

		});
	});