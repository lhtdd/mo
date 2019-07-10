<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${ctxStatic }/js/login.js?r=<%=Math.random() %>"></script>
<div id="login-pop">
	<div class = "login-pop-title">
		<div class="cut-off"><span>登录</span></div>
	</div>
	<div class="login-pop-form">
		<form class="layui-form layui-form-pane">
			<div class="layui-form-item">
				<label class="layui-form-label">用户名</label>
				<div class="layui-input-inline">
					<input name="username" lay-verify="required|phone" placeholder="手机号"
						class="layui-input" type="text">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">密码</label>
				<div class="layui-input-inline">
					<input name="password" lay-verify="required" placeholder="请输入密码"
						class="layui-input" type="password">
				</div>
				<div class="layui-form-mid layui-word-aux">
					<a class="clD2" href="system/changepassword">忘记密码?</a>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">验证码</label>
				<div class="layui-input-inline">
					<input name="validCode" lay-verify="required"
						placeholder="请输入右侧验证码" autocomplete="off" class="layui-input"
						type="text">
				</div>
				<img src="" class="verifyImg" title="点击换一张" />
			</div>
			<div class="layui-form-item login-btn-div" style="height: 30px">
				<input type="checkbox" lay-skin="primary" name="rememberMe" title="自动登录" value="1">
				<input type="hidden"  name="from_url" value="${currentURL }">
				<input type="hidden"  name="event_object" value="">
				<button class="layui-btn login-btn" lay-submit lay-filter="login_form">登录</button>
			</div>
		</form>
	</div>
	<div class="login-pop-other">
		<span class="fs14">还没有账号?</span> <span class="register-a"><a
			class="fs16 cl99" href="member/register">立即注册</a></span>
		<div>
			<span class="clD2">暂不支持其它方式登录</span>
		</div>
	</div>
</div>