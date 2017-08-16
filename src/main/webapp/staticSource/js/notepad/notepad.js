$(function() {
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
	$(".folder-title").hover(function() {
		$(this).append("<a href='#' class='file-delete fr' title='删除该文件夹'><i class='iconfont icon-shanchu'></i></a>");
	}, function() {
		$(this).find(".file-delete").remove();
	})
	$(".file").hover(function() {
		$(this).find(".menu-date").hide();
		$(this).append("<a href='#' class='file-delete fr' title='删除该文件'><i class='iconfont icon-shanchu fs16'></i></a>");
	}, function() {
		$(this).find(".file-delete").remove();
		$(this).find(".menu-date").show();
	})
	// 编辑器
	var notepadHeight = document.body.clientHeight*0.8*0.94;
	layui.use('layedit', function() {
		var layedit = layui.layedit, $ = layui.jquery;
		// 构建一个默认的编辑器
		var index = layedit.build('notepad', {
			tool : [ 'strong' // 加粗
			, 'italic' // 斜体
			, 'underline' // 下划线
			, 'del' // 删除线
			, '|' // 分割线
			, 'left' // 左对齐
			, 'center' // 居中对齐
			, 'right' // 右对齐
			, '|' // 分割线
			, 'link' // 超链接
			, 'unlink' // 清除链接
			],
			height : notepadHeight - 43
		});
		// 编辑器外部操作
		var active = {
			content : function() {
				alert(layedit.getContent(index)); // 获取编辑器内容
			},
			text : function() {
				alert(layedit.getText(index)); // 获取编辑器纯文本内容
			},
			selection : function() {
				alert(layedit.getSelection(index));
			}
		};
		$('.site-demo-layedit').on('click', function() {
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		})

	});
});