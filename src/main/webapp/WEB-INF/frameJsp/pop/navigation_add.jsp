<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="navigation-add-pop">
	<div class = "navigation-add-title">
		<i class="layui-icon">&#xe658;</i>
		<span>收藏您经常访问或者感觉有用的网站、网页地址。</span>
	</div>
	<div class="navigation-add-form">
		<form class="layui-form"  lay-filter="navigation_add_form">
			<div class="layui-form-item">
				<label class="layui-form-label">网页标题</label>
				<div class="layui-input-inline">
					<input name="urlName" lay-verify="required" placeholder="例如:淘宝主页  (名称尽量简短可记)"
						class="layui-input url-input" type="text">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">网址</label>
				<div class="layui-input-inline">
					<input name="webLocation" lay-verify="required" placeholder="例如:https://www.taobao.com (可从浏览器地址栏复制后黏贴于此,你懂的)"
						class="layui-input url-input" type="text">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">归类位置</label>
				<div class="layui-input-inline">
					<select class="folder-select" lay-ignore name="targetFolder" lay-verify="required" >
						<option value="" class="clD2">请选择分类</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit lay-filter="navigation_add_form">添加</button>
					<button class="layui-btn" type="reset">取消</button>
				</div>
			</div>
		</form>
	</div>
</div>