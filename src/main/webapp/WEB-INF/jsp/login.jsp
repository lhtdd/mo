<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>默--登录界面</title>
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
		<c:if test="${type == 'register' }">
			<!--  注册   -->
			<%@ include file="../innerJsp/login/register_info.jsp" %>
		</c:if>
		<c:if test="${type != 'register' }">
			<!-- 登录 -->
			<%@ include file="../innerJsp/login/login_info.jsp" %>
		</c:if>
		</div>
	</div>
	<div class="clear"></div>
	<%-- 弹出层部分 --%>
	<%@ include file="../frameJsp/pop/validCode_pop.jsp" %>
</body>
</html>