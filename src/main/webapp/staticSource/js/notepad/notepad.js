$(function() {
	/* 点击便签事件 */
	//文件列表添加滚动条
	$('.scrollbar-leftMenu').niceScroll({
		cursorcolor : "#999999"
	});
	// 文件列表点击事件
	$(".file-notepad").click(function(e) {
		$(".file-notepad").each(function () {
			if($(this).hasClass("notepad-selected")){
				$(this).removeClass("notepad-selected");
			}
		});
		$(this).addClass("notepad-selected");
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