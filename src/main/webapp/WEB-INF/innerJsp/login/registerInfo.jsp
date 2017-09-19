<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="spead fl">
	<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
		<ul class="layui-tab-title">
			<li class="layui-this">手机注册</li>
			<li>邮箱注册</li>
		</ul>
		<div class="layui-tab-content" style="height: 100px;">
			<div class="layui-tab-item layui-show">
				<form class="layui-form layui-form-pane" action="">
					<div class="layui-form-item">
						<label class="layui-form-label">手机号</label>
						<div class="layui-input-inline">
							<input name="mobile" placeholder="手机号" lay-verify="required"
								class="layui-input" type="text">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">密码</label>
						<div class="layui-input-inline">
							<input name="password" placeholder="请输入密码" lay-verify="required"
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
							<input name="verifyCode" lay-verify="required"
								placeholder="请输入右侧验证码" autocomplete="off" class="layui-input"
								type="text">
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-inline">
							<button class="layui-btn login-btn" lay-submit lay-filter="register_form">注册</button>
						</div>
					</div>
				</form>
			</div>
			<div class="layui-tab-item">
				<form class="layui-form layui-form-pane" action="member/doRegister">
					<div class="layui-form-item">
						<label class="layui-form-label">邮箱</label>
						<div class="layui-input-inline">
							<input name="email" placeholder="邮箱" lay-verify="required" 
								class="layui-input" type="text">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">密码</label>
						<div class="layui-input-inline">
							<input name="password" placeholder="请输入密码" lay-verify="required"
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
							<input name="verifyCode" lay-verify="required"
								placeholder="请输入右侧验证码" autocomplete="off" class="layui-input"
								type="text">
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-inline">
							<button class="layui-btn login-btn" lay-submit lay-filter="register_form">注册</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<div class="shrink fl">
	<span class="fs14">已经有账号?</span> <span class="register-a"><a
		class="fs16 cl99"
		href="member/login">立即登录</a></span>
	<div>
		<span class="clD2">暂不支持其它方式登录</span>
	</div>
</div>
<div class="clear"></div>