<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="header" class="header header-note">
	<div class = "header-wrapper ht">
		<div class="logo">
			<a href="." ><img src="${ctxStatic }/image/logo.png" alt="LOGO 默" title="LOGO MO"/></a>
		</div>
		<div class="nav">
			<ul class="nav-ul">
				<li class="nav-ul-item"><a href="notepad/index">随笔记事</a>
					<!-- <div class="subnav subnav-notepad"> just do it </div> -->
				</li>
				<li class="nav-ul-item"><a href="leisure/index">闲暇小憩</a></li>
			</ul>
		</div>
		<div class="user">
				${user.username }
			
			<a href="member/login">登录</a>&nbsp;|&nbsp;<a href="member/register">注册</a>
		</div>
	</div>
</div>