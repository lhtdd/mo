<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${ctxStatic }/js/header/header.js?r=<%=Math.random() %>"></script>
<script type="text/javascript">
	$(function() {
		$(".nav .nav-ul-item >a").each(function() {
			$this = $(this);
			if ($this.hasClass("${current_module}")) {
				$this.addClass("current-module");
			}
		});
	});
</script>
<div id="header" class="header header-note">
	<div class="layui-container">
		<div class="logo">
			<a href="."><img src="${ctxStatic }/image/logo.png" alt="LOGO 默"
				title="LOGO MO" /></a>
		</div>
		<div class="nav">
			<ul class="nav-ul">
				<li class="nav-ul-item">
					<a class="item-title wd100 notepad" href="notepad/index">随笔记事</a> 
					<!-- <div class="subnav subnav-notepad"> just do it </div> -->
				</li>
				<li class="nav-ul-item">
					<a class="item-title wd100 leisure" href="leisure/index">闲暇小憩</a>
				</li>
			</ul>
		</div>
		<div class="user">
			<c:if test="${user != null}">
				<ul class="nav-ul">
					<li class="nav-ul-item">
						<span>你好，${user.alias }</span>
					</li>
					<li class="nav-ul-item">
						<span class="item-title wd40"><i class="layui-icon">&#xe614;</i></span> 
						<div class="subnav subnav-userInfo"> 
							<%--<a href="#">我的主页</a>
							<hr width="90%" class="ht">
							<a href="#">修改资料</a>--%>
							<a href="loginOut">退出</a>
						</div>
					</li>
				</ul>
				<span></span>
			</c:if>
			<c:if test="${user == null}">
				<a class="cl99" href="member/login">登录</a>&nbsp;|&nbsp;<a class="cl99" href="member/register">注册</a>
			</c:if>
		</div>
	</div>
</div>