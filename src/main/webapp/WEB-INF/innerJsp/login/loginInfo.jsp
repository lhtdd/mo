<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<fieldset class="layui-elem-field">
	<legend class="fs16 clA1">登录</legend>
	<div class="layui-field-box">
		<div class="spead fl">
			<form class="layui-form layui-form-pane" action="">
				<div class="layui-form-item">
					<label class="layui-form-label">用户名</label>
					<div class="layui-input-inline">
						<input name="userName" lay-verify="required" placeholder="请输入用户名"
							autocomplete="off" class="layui-input" type="text">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">密码</label>
					<div class="layui-input-inline">
						<input name="password" placeholder="请输入密码" autocomplete="off"
							class="layui-input" type="password">
					</div>
					<div class="layui-form-mid layui-word-aux">
						<a class="clD2" href="#">忘记密码?</a>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">验证码</label>
					<div class="layui-input-inline">
						<input name="verifyCode" lay-verify="required"
							placeholder="请输入右侧验证码" autocomplete="off" class="layui-input"
							type="text">
					</div>
				</div>
				<div>
					<button class="layui-btn lay-submit login-btn" lay-filter="*">登录</button>
				</div>
			</form>
		</div>
		<div class="shrink fl">
			<span class="fs14">还没有账号?</span> <span class="register-a"><a
				class="fs16 cl99"
				href="http://10.186.106.131:8081/mo/login?type=register">立即注册</a></span>
			<div>
				<span class="clD2">暂不支持其它方式登录</span>
			</div>
		</div>
		<div class="clear"></div>
	</div>
</fieldset>