$(function() {
	layui.use(['element', 'form'], function() {
		var form = layui.form, element = layui.element;
		// 自定义验证规则
		form.verify({
			title : function(value) {
				if (value.length < 5) {
					return '标题至少得5个字符啊';
				}
			},
			pass : [ /(.+){6,12}$/, '密码必须6到12位' ],
			content : function(value) {
				layedit.sync(editIndex);
			}
		});

		// 监听登录提交
		form.on('submit(login_form)', function(data) {
			layer.alert(JSON.stringify(data.field), {
				title : '登录信息'
			})
			return false;
		});
		// 监听注册提交
		form.on('submit(register_form)', function(data) {
			layer.alert(JSON.stringify(data.field), {
				title : '注册信息'
			})
			return false;
		});
	});
});
// 点击切换验证码
function changeVerifyCode() {
	$("#yzmImg").attr("src", "Kaptcha.jpg?" + Math.floor(Math.random() * 100));
}

// 提交
function doSubmit() {
	/*
	 * var verifyCodeValue = $("#verifyCode").val();
	 * if(verifyCodeValue.replace(/\s/g,"") == "") { alert("请输入验证码"); }else {
	 * //提交前先异步检查验证码是否输入正确 var verifyUrl =
	 * "${pageContext.request.contextPath}/servlet/VerifyServlet?verifyCode="+verifyCodeValue;
	 * $.ajax({ type:"GET", url:verifyUrl, success:function(returnData){
	 * if(returnData!="Y") { alert("请输入正确的验证码！"); }else { //验证码正确，进行提交操作
	 * alert("验证码输入正确，提交表单"); } }, error:function(e){ alert(e); } }); }
	 */
}