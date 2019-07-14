$(function() {
	// 加载layer.js,防止有些页面没有使用layui 而无法使用layer
	layui.use(['layer'], function() {
		var layer = layui.layer;
	});
	// 返回顶部
	rollingTop("rolling-top");
    // 点击切换验证码
    $(".verifyImg").click(function(){
        $(this).attr("src", "kaptcha.jpg?" + Math.floor(Math.random() * 100));
    });
});