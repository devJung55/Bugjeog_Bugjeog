const $pagingLabel = $('label.num_label');

$pagingLabel.on('click', (e) => {
    var $target = $(e.target);
    // console.log($target); // span.paging_num
    $pagingLabel.removeClass('current_page');
    $target.parent().addClass('current_page');
})