<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="validCode-pop-form">
	<form class="layui-form">
		<div class="layui-form-item">
			<div class="layui-input-inline">
				<input id="validCode" name="validCode" lay-verify="required"
					   placeholder="请输入右侧验证码" autocomplete="off" class="layui-input"
					   type="text">
			</div>
			<img src="" class="verifyImg" title="点击换一张" />
		</div>
		<div class="layui-form-item">
			<div class="layui-input-inline">
				<button id="validCode-submit-btn" type="button" class="layui-btn layui-btn-danger">确认</button>
			</div>
		</div>
	</form>
</div>