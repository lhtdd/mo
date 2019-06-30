<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<fieldset class="layui-elem-field">
	<legend class="fs16 clA1">注册</legend>
	<div class="layui-field-box">
		<div class="spead fl">
			<form class="layui-form layui-form-pane">
				<input type="hidden" name="registerType" value="2" />
				<div class="layui-form-item">
					<label class="layui-form-label">邮箱</label>
					<div class="layui-input-inline">
						<input name="username" placeholder="请输入常用邮箱"
							lay-verify="required|email" class="layui-input" type="text">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">昵称</label>
					<div class="layui-input-inline">
						<input name="alias" placeholder="起个昵称吧"
							lay-verify="required" class="layui-input" type="text">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">密码</label>
					<div class="layui-input-inline">
						<input name="password" placeholder="请设置密码" lay-verify="required"
							class="layui-input" type="password">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">性别</label>
					<div class="layui-input-block">
						<input name="sex" value="1" title="男" checked="" type="radio">
						<input name="sex" value="2" title="女" type="radio">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">验证码</label>
					<div class="layui-input-inline">
						<input name="validCode" lay-verify="required"
							placeholder="请输入右侧验证码" autocomplete="off" class="layui-input"
							type="text">
					</div>
					<img src="kaptcha.jpg" class="verifyImg" title="点击换一张" />
				</div>
				<div class="layui-form-item">
					<div class="layui-input-inline">
						<button class="layui-btn login-btn" lay-submit
							lay-filter="register_form">注册</button>
					</div>
				</div>
			</form>
		</div>
		<div class="shrink fl">
			<span class="fs14">已经有账号?</span> <span class="register-a"><a
				class="fs16 cl99" href="member/login">立即登录</a></span>
			<div>
				<span class="clD2">暂不支持其它方式登录</span>
			</div>
		</div>
		<div class="clear"></div>
	</div>
</fieldset>