<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>默--注意</title>
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
		<div class="submit-wrapper">
		<c:if test="${type == 'registerSuccess' }">
			<!-- 手机号注册成功提示 -->
			<%@ include file="../../innerJsp/system/register_success.jsp" %>
		</c:if>
		<c:if test="${type == 'activationTips' }">
			<!--  邮箱注册后提示激活   -->
			<%@ include file="../../innerJsp/system/activation_tips.jsp" %>
		</c:if>
		<c:if test="${type == 'activationSuccess' }">
			<!-- 激活成功 -->
			<%@ include file="../../innerJsp/system/activation_success.jsp" %>
		</c:if>
		<c:if test="${type == 'activationFailed' }">
			<!--  激活失败   -->
			<%@ include file="../../innerJsp/system/activation_failed.jsp" %>
		</c:if>
		</div>
	</div>
	<div class="clear"></div>
</body>
</html>