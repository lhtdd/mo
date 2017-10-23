$(function() {
	layui.use('form', function() {
		var form = layui.form;
		// 自定义验证规则
		form.verify({
			title : function(value,item) {
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
			$.ajax({
			    url:'member/login',
			    type:'POST',
			    async:true,
			    data:data.field,
			    timeout:5000,
			    dataType:'json',
			    success:function(data){
			        if (data.flag == 'yes'){
			        	window.location.href = data.go_url;
			        }else{
			        	layer.msg(data.errorMsg);
			        }
			    }
			});
			return false;
		});
		// 监听注册提交
		form.on('submit(register_form)', function(data) {
			$.ajax({
			    url:'member/register',
			    type:'POST',
			    async:true,
			    data:data.field,
			    timeout:5000,
			    dataType:'json',
			    success:function(data){
			        if (data.flag == 'yes' && data.registerType == '1'){
			        	// 手机号注册
			        	window.location.href = "member/systemTips/registerSuccess?go_url="+data.go_url;
			        }else if (data.flag == 'yes' && data.registerType == '2'){
			        	// 邮箱注册
			        	window.location.href = "member/systemTips/activationTips";
			        }else{
			        	layer.msg(data.errorMsg);
			        }
			    }
			});
			return false;
		});
	});
	
	// 点击切换验证码
	$(".verifyImg").click(function(){
		$(this).attr("src", "kaptcha.jpg?" + Math.floor(Math.random() * 100));
	})
	
});