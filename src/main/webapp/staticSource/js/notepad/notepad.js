$(function() {
	/* 点击便签事件 */
	//文件列表添加滚动条
	$('.scrollbar-leftMenu').niceScroll({
		cursorcolor : "#999999"
	});
	// 文件列表点击事件
	$(".file").click(function(e) {
		$(".file").each(function () {
			if($(this).hasClass("file-selected")){
				$(this).removeClass("file-selected");
			}
		});
		$(this).addClass("file-selected");
	})
	//显示文件的删除按钮
	$(".file").hover(function() {
		$(this).find(".menu-date").hide();
		$(this).append("<a href='#' class='file-delete fr' title='删除便签'><i class='iconfont icon-shanchu fs16'></i></a>");
	}, function() {
		$(this).find(".file-delete").remove();
		$(this).find(".menu-date").show();
	})
	/* 编辑器提交事件 */
    layui.use([ 'layer'], function() {
        var layer = layui.layer;
        var content = notepadEditor.getData();
        /*layer.open({
            type: 1,
            content: content //这里content是一个普通的String
        });*/
    });

});