$(function() {
	// 加载layer.js,防止有些页面没有使用layui 而无法使用layer
	layui.use([ 'layer'], function() {
		var layer = layui.layer;
	});
	// header 设置选中三角形,单机跳转后不起作用，必须设置变量
	/*
	 * $('.nav-ul-item>a').click(function(){ $(this).after("<i
	 * class='sub-selected'><i>"); })
	 */
	// aside设置滚动条
	$('.scrollbar-aside').niceScroll(".scrollbar-wrap", {
		cursorcolor : "#FF6600"
	});
	// 返回顶部
	rollingTop("rolling-top");

});