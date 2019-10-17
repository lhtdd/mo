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
// 刷新或提交页面对象
var refreshPage = {
    'refreshType': 'page',
    'refreshObject':'',
    'refreshMethod':''
};
$.ajaxSetup({
    complete: function (request, status) {
        try {
            var sessionIsExpire = request.getResponseHeader('Session-Expire');
            if (sessionIsExpire == 'yes') {
                if (refreshPage.refreshType == 'page'){
                    window.location.href = 'member/login';
                }
                if (refreshPage.refreshType == 'pop'){
                    var $loginPop = $('#login-pop');
                    layer.open({
                        type : 1,
                        content : $loginPop,
                        title:false,
                        area: ['500px', '340px'],
                        shadeClose : true,
                        success: function () {
                            $(".verifyImg").click();
                        }

                    })
                }
            }
        } catch (e) {
            //后台没有设置responseHeader则不做处理
        }
    }
});
function initRefreshPage() {
    refreshPage.refreshType = 'page';
    refreshPage.refreshObject = '';
    refreshPage.refreshMethod = '';
}
function initLoginPop() {
    var $loginPop = $('#login-pop');
    $loginPop.find('input').val('');
}