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
			$.ajax({
			    url:'member/register',
			    type:'POST', //GET
			    async:true,    //或false,是否异步
			    data:data.field,
			    timeout:5000,
			    dataType:'json',
			    success:function(data){
			        layer.alert(data.flag);
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