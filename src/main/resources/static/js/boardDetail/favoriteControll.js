function favoriteShowControll(isFavorite) {
    const $favoriteButton = $($('svg[name=favoriteButton]')[0]);

    if (memberId == null && businessId != null) {
        $favoriteButton.hide();
    } else if (memberId != null) {
        $favoriteButton.show();
        favoriteColorControll(isFavorite);
    }
}

function favoriteColorControll(isFavorite) {
    const $favoriteButton = $($('svg[name=favoriteButton]')[0]);
    $favoriteButton.hide();
    if (`${isFavorite != null}`) {
        $favoriteButton.show()
        if (`${isFavorite}`) {
            $favoriteButton.addClass('fill');
        } else {
            $favoriteButton.removeClass('fill');
        }
    } else {
        $favoriteButton.hide();
    }
}