<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<%@ include file="/common/common.jsp"%>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>默--忘记密码</title>
<link rel="stylesheet" href="${ctxStatic }/css/login.css">
<script type="text/javascript" src="${ctxStatic }/js/login.js?r=<%=Math.random() %>"></script>
</head>
<body>
	<!-- 网页中部 -->
	<div class="layui-container">
		<div class="login-header">
			<a href="."><img
				src="${ctxStatic }/image/logo.png" alt="LOGO 默" title="LOGO MO" /></a>
			<div class="mo-explain">
				<span class="fs14 cl99">一种态度，一种沉淀...</span> <span class="fs18 cl99">恭默思道。——《书·说命》</span>
			</div>
		</div>
		<!--  -->
		<div class="change-password-wrapper">
			<div id="mobile-captcha-div" class="change-password">
				<div class="step-image-div">
					<div><img src="${ctxStatic }/image/system/step_1.png"></div>
				</div>
				<div class="notice-content">
					<div class="layui-form-item">
						<label class="layui-form-label">手机号</label>
						<div class="layui-input-inline">
							<input id="yourMobile" name="username" lay-verify="required|phone" placeholder="注册的手机号"
								   class="layui-input" type="text">
						</div>
						<div id="yourMobileTip" class="layui-form-mid"></div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">验证码</label>
						<div class="layui-input-inline">
							<input id="valid-code" name="validCode" lay-verify="required"
								   placeholder="请输入右侧验证码" autocomplete="off" class="layui-input"
								   type="text">
						</div>
						<img src="kaptcha.jpg" class="verifyImg" title="点击换一张" />
					</div>
					<div class="layui-form-item">
						<button id="password-next-step1" class="layui-btn login-btn next-step">下一步</button>
					</div>
				</div>
			</div>
			<div id="short-Message-div" class="change-password">
				<div class="step-image-div">
					<div><img src="${ctxStatic }/image/system/step_2.png"></div>
				</div>
				<div class="notice-content">
					<div class="layui-form-item">
						<label class="layui-form-label">手机号</label>
						<div class="layui-input-inline">
							<input id="validMobile" class="layui-input" type="text" disabled="disabled">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">新密码</label>
						<div class="layui-input-inline">
							<input id="newPassword" name="password" lay-verify="required" placeholder="密码"
								   class="layui-input" type="password">
						</div>
						<div id="yourPasswordTip" class="layui-form-mid layui-word-aux">由字母、数字或下划线组成</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">验证码</label>
						<div class="layui-input-inline">
							<input id="shortMessageCode" name="shortMessageCode" lay-verify="required"
								   placeholder="验证码" autocomplete="off" class="layui-input"
								   type="text">
						</div>
						<button id="password-shortMessageCode-btn" type="button" class="layui-btn layui-btn-primary shortMessageCode-btn">获取验证码</button>
					</div>
					<div class="layui-form-item">
						<button id="password-next-step2" class="layui-btn login-btn next-step">下一步</button>
					</div>
				</div>
			</div>
			<div id="confirm-password-div" class="change-password">
				<div class="step-image-div">
					<div><img src="${ctxStatic }/image/system/step_3.png"></div>
				</div>
				<div class="notice-content">
					密码设置成功！现在
					<a class="cl99" href="member/login">登录</a>
				</div>
			</div>
		</div>
	</div>
	<div class="clear"></div>
</body>
</html>