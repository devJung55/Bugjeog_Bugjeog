

const file = document.querySelector('input[type=file]');
const imgDiv = document.querySelector('.file-name');
const closeSpan = document.querySelector('#btn_x');
const input = document.querySelector('#file_input');
const plusPicture = document.querySelector('#plus_picture');
const $registerBtn = $("#regist_btn");
const $writeForm = $("form[name='form']");

/* 파일 객체 담는 변수 */
let $file;

/* uuid를 담을 전역?변수 */
globalThis.uuids;

// globalThis.fileData = new Array();
FileList.prototype.forEach = Array.prototype.forEach;

$(document).ready(function() {

// 초기 등록 버튼 색상 설정
    $registerBtn.css("color", "rgb(196, 196, 196)").css("background-color", "rgb(242, 244, 247)");
// textarea 입력시 등록 버튼 색상 변경
    $('#content_textarea, #title_textarea').on('input', function() {
        var contentLength = $('#content_textarea').val().length;
        var titleLength = $('#title_textarea').val().length;
        if (contentLength > 0 && titleLength > 0) {
            $registerBtn.css("cursor", "pointer");
            $registerBtn.css("color", "white").css("background-color", "blue");
        } else {
            $registerBtn.css("cursor", "default");
            $registerBtn.css("color", "rgb(196, 196, 196)").css("background-color", "rgb(242, 244, 247)");
        }
    });
});

$registerBtn.on("click", function () {
    let contentLength = $('#content_textarea').val().length;
    let titleLength = $('#title_textarea').val().length;
    if (contentLength > 0 && titleLength > 0) {
        $writeForm.append(
            `
                <input type="text" value="${$file.name}" name="boardFreeImgOriginalName" style="display: none">
                <input type="text" value="${globalThis.uuids[0]}" name="boardFreeImgUuid" style="display: none">
            `
        );

        $writeForm.submit();
    }
})

// x 버튼을 누르면, 기본 이미지로 변경!
closeSpan.addEventListener('click', function (e) {
    e.preventDefault();
    input.value = "";
    this.style.display = 'none';
    imgDiv.style.backgroundImage = `url('')`;
    $("#plus_picture").show();
});

file.addEventListener('change', function (e) {
    closeSpan.style.display = "inline-block";
    this.style.display = 'none';
    let reader = new FileReader();

    $file = $(file)[0].files[0];
    let formData = new FormData();

    formData.append("file", $file);

    $.ajax({
        url: "/free-boards/imgs/upload",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        success: function (uuids) {
            console.log("change 이벤트 ajax 성공")
            globalThis.uuids = uuids;
            console.log(uuids);
            $("#plus_picture").hide();

            reader.readAsDataURL(e.target.files[0]);
            reader.onload = function (e) {
                let result = e.target.result;
                if (result.includes('image')) {
                    imgDiv.style.backgroundImage = `url('${result}')`;
                } else {
                    imgDiv.style.backgroundImage = `url('no_image.png')`;
                }
            };
        },
        error: function () {
            console.log("change 이벤트 ajax 실패")
        }
    });
});

