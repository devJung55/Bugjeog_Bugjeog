	const $memberEmail =$(".member-email");
	const $memberBirth =$(".member-birth");
	const $memberPhoneNumber =$(".member-phone");
	const $modifyButton = $("#modify-button");
	
	$modifyButton.click(function(){
		
		if(!($memberEmail.val())){
			
			alert("이메일을 입력해주세요.");
		}else if(!($memberBirth.val())){
			
			alert("생년월일을 입력해주세요.");
		}else if(!($memberPhoneNumber.val())){
			
			alert("핸드폰 번호를 입력해주세요.");
		}
		
	});