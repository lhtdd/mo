<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>  
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<base href="<%=basePath %>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>默--登录界面</title>
	<link rel="stylesheet" href="${ctxStatic }/css/login.css">
	<script type="text/javascript" src="${ctxStatic }/js/login.js"></script>
</head>
<body>
	<!-- 调整整个页面的宽高 -->
	<div class="wrapper">
		<!-- 网页头部 -->
		<%@ include file="../frameJsp/header.jsp" %> 
		<!-- 网页中部 -->
		<div class="container">
	    	<!-- 左侧菜单栏 -->
	    	<%@ include file="../innerJsp/login_register.jsp" %> 
		</div>
		<div class="clear"></div>
		<!-- 网页底部 -->
		<%@ include file="../frameJsp/footer.jsp" %> 
	</div>
</body>
</html>