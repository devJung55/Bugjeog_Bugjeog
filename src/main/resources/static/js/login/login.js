// 이메일 정규식
const $email = $("input[name=memberEmail]");
const $errorMessageEmail = $(".error-message-email");


$email.on("blur", function(e){
    var emailValue = $email.val(); 
    var rgbEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

    if(emailValue.length == 0){    // 빈문자열이 들어왔을 때
        $errorMessageEmail.css("display", "block");
        $errorMessageEmail.css("color", "red");
        $errorMessageEmail.text("이메일을 입력해주세요.");
        
    } else if(rgbEmail.test(emailValue)){  
        $errorMessageEmail.css("display", "none");

    } else {
        $errorMessageEmail.css("display", "block");
        $errorMessageEmail.css("color", "red"); // 올바른 이메일 형식이 아닐 때
        $errorMessageEmail.text("이메일 형식으로 입력해주세요.");
    }
});

// 비밀번호 
const $password = $("input[name=memberPassword]");
const $errorMessagePassword = $(".error-message-password");

$password.on("blur", function(e){
    var passwordValue = $password.val(); 

    if(passwordValue.length == 0){    // 빈문자열이 들어왔을 때
        $errorMessagePassword.css("display", "block");
        $errorMessagePassword.css("color", "red");
        $errorMessagePassword.text("비밀번호를 입력해주세요.");

    } else {
        $errorMessagePassword.css("display", "none");

    }
});


	// 비밀번호 확인
	// 비밀번호 text and password 
  	const $eye = $(".eye");
    const $first = $(".first");
				
		$eye.click(function(){
			if($eye.hasClass("close-eye")) {
				$eye.removeClass("close-eye");
	            $first.attr("type", "text");
	            $eye.attr("src", contextPath + '/static/img/member/passwordEye.png');
	        } else {
				$eye.addClass("close-eye");
				$first.attr("type", "password");
	            $eye.attr("src", contextPath + '/static/img/member/passwordEyeSlash.png');
	        }
		});



const $loginButton = $(".login-button");
const urlParams = new URL(location.href).searchParams;
const login = urlParams.get('login');

if(login == false){
	login = true;
}

$(function() {
	if(login){
		alert("이메일 또는 비밀번호를 확인해주세요.");
	}
});

$loginButton.click(function(){
	$(".loginEmail_form").submit();
});