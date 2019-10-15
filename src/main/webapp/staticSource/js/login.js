$(function() {
	layui.use('form', function() {
		var form = layui.form;
		// 自定义验证规则
		form.verify({

		});

		// 监听登录提交
		form.on('submit(login_form)', function(formData) {
			$.ajax({
			    url:'member/login',
			    type:'POST',
			    async:true,
			    data:formData.field,
			    timeout:5000,
			    dataType:'json',
			    success:function(data){
			        if (data.flag == 'yes'){
                        if (refreshPage.refreshType == 'page'){
                            window.location.href = data.go_url;
                        }
                        if (refreshPage.refreshType == 'pop'){
                            layer.close(layer.index);
                            if (refreshPage.refreshMethod == 'click'){
                                refreshPage.refreshObject.click();
                            }
                        }
                        initRefreshPage();
                        initLoginPop();
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
	
    function isPoneAvailable(mobile) {
        var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
        if (!myreg.test(mobile)) {
            return false;
        } else {
            return true;
        }
    }

	// 校验手机号--注册时
    $("#registorMobile").blur(function () {
        var mobile = $(this).val();
        var $vlMobile = $("#mobileTip");
        if (isPoneAvailable(mobile)){
            $.ajax({
                url:'system/mobile',
                type:'GET',
                async:true,
                data:{'mobile':mobile},
                timeout:5000,
                dataType:'json',
                success:function(data){
                    if (data.flag == 'no'){
                        $vlMobile.removeClass("cl-red");
                        $vlMobile.html("");
                    }else{
                        $vlMobile.addClass("cl-red");
                        $vlMobile.html(data.message);
                    }
                }
            });
		}else {
            $vlMobile.addClass("cl-red");
            $vlMobile.html("手机号码非法");
		}
    });

    function isPasswordAvailable(pass) {
        var myreg=/^[0-9a-zA-Z_]{6,12}$/;
        if (!myreg.test(pass)) {
            return false;
        } else {
            return true;
        }
    }

    // 校验密码
    $("#registorPassword").blur(function () {
        var password = $(this).val();
        var $vlPassword = $("#passTip");
        if (isPasswordAvailable(password)){
            $vlPassword.removeClass("layui-word-aux");
            $vlPassword.removeClass("cl-red");
            $vlPassword.html("");
        }else {
            $vlPassword.removeClass("layui-word-aux");
            $vlPassword.addClass("cl-red");
            $vlPassword.html("密码非法,应由字母、数字或下划线组成6-12位");
		}
    });
    var idx_validCode;
    // 获取短信验证码
	$("#shortMessageCode-btn").click(function () {
        var mobile = $("#registorMobile").val();
        var password = $("#registorPassword").val();
        if (mobile.trim() == ""){
        	layer.msg("请输入手机号", {icon:6});
        	return;
		}
        if (password.trim() == ""){
            layer.msg("请输入密码", {icon:6});
            return;
        }
        var mobileIsLegal = $("#mobileTip").hasClass("cl-red");
        var passwordIsLegal = $("#passTip").hasClass("cl-red");
		if (mobileIsLegal || passwordIsLegal){
			layer.msg("请修改手机号或密码", {icon:6})
		}else {
            idx_validCode = layer.open({
                type: 1,
				title: false,
                content: $('#validCode-pop-form'),
                success:function(){
                    $(".verifyImg").click();
                }
            });
		}
    });

    var InterValObj; //timer变量，控制时间
    var count = 180; //间隔函数，1秒执行
    var curCount; //当前剩余秒数
    // 提交图形验证码获取短信验证码
    $("#validCode-submit-btn").click(function () {
        var subBtn = $("#shortMessageCode-btn");
        var mobile = $("#registorMobile").val();
        var validCode = $("#validCode").val();
        if (validCode.trim() != ''){
			$.ajax({
				url:'sms/captcha',
				type:'POST',
				async:true,
				data:{'mobile':mobile,'validCode':validCode},
				timeout:5000,
				dataType:'json',
				success:function(data){
					$("#validCode").val("");
                    $(".verifyImg").click();
					layer.close(idx_validCode);
                    layer.msg(data.message);
					if (data.flag == 'yes'){
                        curCount = count;
                        subBtn.addClass("layui-btn-disabled");
                        subBtn.attr("disabled", "true");
                        subBtn.text(curCount + "秒后重新获取");
                        InterValObj = window.setInterval(function () {repeatObtainCode(subBtn)  }, 1000); //启动计时器，1秒执行一次
					}
				}
			});
		}else {
        	return;
		}
    });
    
    // 倒计时重新获取短信验证码
	function repeatObtainCode(subBtn) {
        if(curCount == 0) {
            window.clearInterval(InterValObj); //停止计时器
            subBtn.removeClass("layui-btn-disabled");
            subBtn.removeAttr("disabled"); //启用按钮
            subBtn.text("重新发送验证码");
        } else {
            curCount--;
            subBtn.text(curCount + "秒后重新获取");
        }
    }

    /* 忘记密码 */
    var legalMobile;
    var legalValidCode;
    // 校验图形验证码--找回密码时
    $("#password-next-step1").click(function () {
        var mobile = $("#yourMobile").val();
        var validCode = $("#valid-code").val();
        if (isPoneAvailable(mobile)){
            $.ajax({
                url:'system/captcha',
                type:'POST',
                async:true,
                data:{'mobile':mobile,'validCode':validCode},
                timeout:5000,
                dataType:'json',
                success:function(data){
                    if (data.flag == 'yes'){
                        legalMobile = mobile;
                        legalValidCode = validCode;
                        $("#validMobile").val(legalMobile);
                        $("#mobile-captcha-div").css("display", "none");
                        $("#short-Message-div").css("display", "block");
                    }else{
                        layer.msg(data.message);
                    }
                }
            });
        }else {
            layer.msg("手机号码非法");
        }
    });

    // 点击获取验证码
    $("#password-shortMessageCode-btn").click(function () {
        var newPassword = $("#newPassword").val();
        var subBtn = $("#password-shortMessageCode-btn");
        if (isPasswordAvailable(newPassword)){
            $.ajax({
                url:'sms/captcha',
                type:'POST',
                async:true,
                data:{'mobile':legalMobile,'validCode':legalValidCode},
                timeout:5000,
                dataType:'json',
                success:function(data){
                    layer.msg(data.message);
                    if (data.flag == 'yes'){
                        curCount = count;
                        subBtn.addClass("layui-btn-disabled");
                        subBtn.attr("disabled", "true");
                        subBtn.text(curCount + "秒后重新获取");
                        InterValObj = window.setInterval(function () {repeatObtainCode(subBtn)  }, 1000); //启动计时器，1秒执行一次
                    }else {
                        layer.msg(data.message);
                    }
                }
            });
        }else {
            layer.msg("密码非法", {icon:6});
        }
    });
    // 提交新密码
    $("#password-next-step2").click(function () {
        var shortMessageCode = $("#shortMessageCode").val();
        if (shortMessageCode.trim() == ''){
            layer.msg("请填写验证码");
            return
        }
        var newPassword = $("#newPassword").val();
        if (isPasswordAvailable(newPassword)){
            $.ajax({
                url:'system/pw',
                type:'POST',
                async:true,
                data:{'username':legalMobile,'password':newPassword,'shortMessageCode':shortMessageCode},
                timeout:5000,
                dataType:'json',
                success:function(data){
                    if (data.flag == 'yes'){
                        $("#short-Message-div").css("display", "none");
                        $("#confirm-password-div").css("display", "block");
                    }else{
                        layer.msg(data.errorMsg);
                    }
                }
            });
        }else {
            layer.msg("密码非法", {icon:6});
        }
    });
	
});