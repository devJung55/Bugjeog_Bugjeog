const $lCateValue = $('div.fieldset-Static_content-first div.Static-body');
const $sCateValue = $('div.fieldset-Static_content-others div.Static-body');
const $wrapper = $("div.modal_apply_label_wrapper");
$('document').ready(function () {
    var area0 = ["대분류 선택"
        , "동물성 생산품", "식물성 생산품", "동·식물성 유지"
        , "조제식료품", "광물성 생산품", "화학공업"
        , "플라스틱, 고무", "원피, 가죽, 모피", "목재, 농세공품"
        , "펄프, 종이, 서적", "섬유, 의류", "신발, 모자, 우산"
        , "석재, 도자, 유리", "진주, 귀금속", "철강, 금속"
        , "기계, 전기, 전자", "광학, 정밀, 시계", "잡품"];
    var area1 = ["산동물", "식용설육", "어패류", "낙농품", "동물사체"];
    var area2 = ["식물인경", "채소뿌리", "과실견과", "커피,차", "곡물", "맥아전분", "종자대두", "식물수액", "기타식물"];
    var area3 = ["어육조제", "당류설탕", "코코아", "곡물조제", "과실조제", "기타조제", "음료주류", "조제사료", "담배"];
    var area4 = ["소금토석", "광물정광", "석탄석유"];
    var area5 = ["무기물", "유기물", "의료용품", "비료", "염료안료", "화장품", "비누왁스", "단백질", "화약류", "사진재료", "기타화학"];
    var area6 = ["플라스틱", "고무"];
    var area7 = ["원피가죽", "가죽제품", "모피"];
    var area8 = ["목재목탄", "코르크", "조물재료"];
    var area9 = ["펄프", "종이판지", "서적지도"];
    var area10 = ["견(실크)", "양모", "면(목화)", "아마", "필라멘트", "스테이플", "부직포", "양탄자", "특수직물", "도포직물", "편물", "편물의류", "직물의류", "섬유제품"];
    var area11 = ["신발", "모자", "우산", "가발우모"];
    var area12 = ["석제품", "도자제", "유리"];
    var area13 = ["귀금속"];
    var area14 = ["철강", "철강제품", "동(구리)", "니켈", "알미늄", "연(납)", "아연", "주석", "기타금속", "공구도구", ""];
    var area15 = ["기계류", "전기전자"];
    var area16 = ["광학정밀", "시계", "악기"];
    var area17 = ["가구조명", "운동오락", "생활용품"];
    var area18 = ["예술품"];

    // 대분류 선택 박스 초기화

    $("select[name=large_category]").each(function () {
        $lCate = $(this);   // select.large_category
        $.each(eval(area0), function () {
            $lCate.append("<option value='" + this + "'>" + this + "</option>");
        });
        $lCate.next().append("<option value=''>소분류 선택</option>");
    });

    // 대분류 선택시 소분류 설정

    $("select[name=large_category]").change(function () {
        var area = "area" + $("option", $(this)).index($("option:selected", $(this))); // 대분류의 소분류 Array
        var $sCate = $("select[name=small_category]");
        $("option", $sCate).remove(); // 소분류 초기화

        if (area == "area0") {
            $sCate.append("<option value=''>소분류 선택</option>");
            $wrapper.addClass('is-hidden');
        } else {
            $.each(eval(area), function () {
                $sCate.append("<option value='" + this + "'>" + this + "</option>");
            });
            $wrapper.removeClass('is-hidden');
        }
    });

    $("select[name=small_category]").change(function () {
        console.log(this.value);
        console.log($("select[name=large_category]").val());
        $lCateValue.text($("select[name=large_category]").val());
        $sCateValue.text(this.value);
    });
});