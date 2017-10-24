<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %> 
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<base href="<%=basePath %>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>首页--随笔记事</title>
	<link rel="stylesheet" href="${ctxStatic }/css/notepad/notepad.css">	
	<script type="text/javascript" src="${ctxStatic }/js/notepad/notepad.js?r=<%=Math.random() %>"></script>
</head>
<body>
	<!-- 调整整个页面的宽高 -->
	<div class="wrapper">
		<!-- 网页头部 -->
		<%@ include file="../../frameJsp/header.jsp" %> 
		<!-- 左侧导航栏 -->
		<%@ include file="../../frameJsp/aside.jsp" %> 
		<!-- 网页中部 -->
		<%-- <div class="container">
	    	<!-- 左侧菜单栏 -->
	    	<%@ include file="../../innerJsp/notepad/left_menu.jsp" %> 
	    	<!-- 右侧主页面 -->    
	    	<%@ include file="../../innerJsp/notepad/editor.jsp" %> 
		</div>
		<div class="clear"></div>
		<!-- 网页底部 -->
		<%@ include file="../../frameJsp/footer.jsp" %> --%> 
	</div>
	<!-- 弹出层部分 -->
	<%@ include file="../../frameJsp/hidden_info.jsp" %> 
	<%@ include file="../../frameJsp/pop/login_pop.jsp" %> 
	<%@ include file="../../frameJsp/pop/navigation_add.jsp" %> 
</body>
</html>