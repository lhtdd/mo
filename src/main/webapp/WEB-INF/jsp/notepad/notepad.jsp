<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %> 
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<base href="<%=basePath %>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>首页--记事</title>
	<link rel="stylesheet" href="${ctxStatic }/css/global.css">
	<link rel="stylesheet" href="${ctxStatic }/css/common.css">
	<link rel="stylesheet" href="${ctxStatic }/font/iconfont.css"></link>
	<link rel="stylesheet" href="${ctxStatic }/css/aside.css">
	<link rel="stylesheet" href="${ctxStatic }/css/header.css">
	<link rel="stylesheet" href="${ctxStatic }/css/notepad/home.css">	
	<link rel="stylesheet" href="${ctxStatic }/css/footer.css">	
	<script src="https://cdn.bootcss.com/jquery.nicescroll/3.7.6/jquery.nicescroll.min.js"></script>
	<script type="text/javascript" src="${ctxStatic }/js/common.js"></script>
	<script type="text/javascript" src="${ctxStatic }/js/base.js"></script>
	<script type="text/javascript" src="${ctxStatic }/js/notepad/notepad.js"></script>
</head>
<body>
	<!-- 调整整个页面的宽高 -->
	<div class="wrapper">
		<!-- 网页头部 -->
		<%@ include file="../../frameJsp/header.jsp" %> 
		<!-- 左侧导航栏 -->
		<%@ include file="../../frameJsp/aside.jsp" %> 
		<!-- 网页中部 -->
		<div class="container">
	    	<!-- 左侧菜单栏 -->
	    	<%@ include file="../../innerJsp/notepad/leftMenu.jsp" %> 
	    	<!-- 右侧主页面 -->    
	    	<%@ include file="../../innerJsp/notepad/main.jsp" %> 
		</div>
		<!-- 网页底部 -->
		<%@ include file="../../frameJsp/footer.jsp" %> 
	</div>
	<!-- 弹出层部分 -->
</body>
</html>