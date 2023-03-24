const $tabLis = $('ul.CommunityProfile_tabs').children('li');
$tabLis.on('click', (e) => {
    console.log(e.currentTarget);
    for (let i = 0; i < $tabLis.length; i++) {
        if ($($tabLis[i]).hasClass('CommunityProfile_current')) { $($tabLis[i]).removeClass('CommunityProfile_current'); }
    }
    $(e.currentTarget).addClass('CommunityProfile_current');
    $(e.currentTarget).
});