$(function() {
	//文件列表添加滚动条
	$('.scrollbar_leftMenu').niceScroll({
		cursorcolor : "#999999"
	});
	// 文件列表点击事件
	$(".file").click(function(e) {
		$(".file").each(function () {
			if($(this).hasClass("file_selected")){
				$(this).removeClass("file_selected");
			}
		});
		$(this).addClass("file_selected");
	})
	//显示文件的删除按钮
	$(".folder_title").hover(function() {
		$(this).append("<a href='#' class='file_delete fr' title='删除该文件夹'><i class='iconfont icon-shanchu'></i></a>");
	}, function() {
		$(this).find(".file_delete").remove();
	})
	$(".file").hover(function() {
		$(this).find(".menu_date").hide();
		$(this).append("<a href='#' class='file_delete fr' title='删除该文件'><i class='iconfont icon-shanchu fs16'></i></a>");
	}, function() {
		$(this).find(".file_delete").remove();
		$(this).find(".menu_date").show();
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
	
	function getInfo()
	{
	var s = "";
	s += " 网页可见区域宽："+ document.body.clientWidth;
	s += " 网页可见区域高："+ document.body.clientHeight;
	s += " 网页可见区域宽："+ document.body.offsetWidth + " (包括边线和滚动条的宽)";
	s += " 网页可见区域高："+ document.body.offsetHeight + " (包括边线的宽)";
	s += " 网页正文全文宽："+ document.body.scrollWidth;
	s += " 网页正文全文高："+ document.body.scrollHeight;
	s += " 网页被卷去的高(ff)："+ document.body.scrollTop;
	s += " 网页被卷去的高(ie)："+ document.documentElement.scrollTop;
	s += " 网页被卷去的左："+ document.body.scrollLeft;
	s += " 网页正文部分上："+ window.screenTop;
	s += " 网页正文部分左："+ window.screenLeft;
	s += " 屏幕分辨率的高："+ window.screen.height;
	s += " 屏幕分辨率的宽："+ window.screen.width;
	s += " 屏幕可用工作区高度："+ window.screen.availHeight;
	s += " 屏幕可用工作区宽度："+ window.screen.availWidth;
	s += " 你的屏幕设置是 "+ window.screen.colorDepth +" 位彩色";
	s += " 你的屏幕设置 "+ window.screen.deviceXDPI +" 像素/英寸";
	alert (s);
	}
	//getInfo(); 
});