/**
 * 单删询问
 */
$(".removeOne").click(function () {
    return window.confirm("确定删除该条数据吗？");
});

/**
 * 全选/全不选
 */
$('.check-all').click(function(){
    //判断当前全选框是否选中，如果选中则全选，否则全不选
    if($(this).prop('checked')){
        $('input[type="checkbox"]').prop('checked',true);
    }else{
        $('input[type="checkbox"]').prop('checked',false);

    }
});
$(".check-del").click(function () {
    allLen = $(".check-del").length;
    checkedLen = $(".check-del:checked").length;

    if (allLen == checkedLen) {
        $('.check-all').prop("checked", true);
    } else {
        $('.check-all').prop("checked", false);
    }
});

/**
 * 删除多个通用操作
 */
$(".removeSome").click(function () {
    if (window.confirm("确定删除这些数据吗？")) {
        var ids = '';
        $(".check-del").each(function (i) {
            if ($(this).prop("checked")) {
                ids += $(this).attr("data-id") + ',';
            }
        });

        if (!ids) {
            return false;
        }

        ids = ids.substr(0, ids.length - 1);

        var href = $(".removeSome").attr("data-href");
        href += '?ids=' + ids;
        $(".removeSome").attr("href", href);
    } else {
        return false;
    }

});


/**
 * 排序操作
 */
$("#orderList").click(function () {
    var url = $(this).attr("data-href");
    var data = '';
    $("input.orderby").each(function (i){
        var primary = $(this).attr("data-primary");
        var orderNum = $(this).val();

        data += primary + '-' + orderNum + ',';
    });

    data = data.substr(0, data.length - 1);

    $(this).attr("href", url+'?data='+data);
});
