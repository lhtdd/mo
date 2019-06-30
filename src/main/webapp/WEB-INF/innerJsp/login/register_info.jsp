<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<fieldset class="layui-elem-field">
	<legend class="fs16 clA1">注册</legend>
	<div class="layui-field-box">
		<div class="spead fl">
			<form class="layui-form layui-form-pane">
				<input type="hidden" name="registerType" value="1" />
				<div class="layui-form-item">
					<label class="layui-form-label">手机号</label>
					<div class="layui-input-inline">
						<input id="registorMobile" name="username" placeholder="请输入手机号"
							lay-verify="required|phone" class="layui-input" type="text">
					</div>
					<div id="mobileTip" class="layui-form-mid"></div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">密码</label>
					<div class="layui-input-inline">
						<input id="registorPassword" name="password" placeholder="请设置6-12位密码" lay-verify="required"
							class="layui-input" type="password">
					</div>
					<div id="passTip" class="layui-form-mid layui-word-aux">由字母、数字或下划线组成</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-inline">
						<input name="shortMessageCode" lay-verify="required"
							placeholder="验证码" autocomplete="off" class="layui-input"
							type="text">
					</div>
					<button id="shortMessageCode-btn" type="button" class="layui-btn layui-btn-primary">获取验证码</button>
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