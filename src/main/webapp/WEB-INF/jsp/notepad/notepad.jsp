<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<%@ include file="/common/common.jsp" %>
<head>
	<base href="<%=basePath %>">
	<title>默--随笔记事</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="Keywords" content="默,mo,便签,记事本,备忘录">
	<meta name="Description" content="简单的对日常工作,学习,生活中的一些小事进行记录。以便能够方便的找到并完成,随建随删。">
	<link rel="stylesheet" href="${ctxStatic }/css/notepad/notepad.css">
	<script type="text/javascript" src="${ctxStatic }/js/notepad/notepad.js?r=<%=Math.random() %>"></script>
	<script src="//cdn.ckeditor.com/4.11.3/full/ckeditor.js"></script>
</head>
<body>
	<!-- 网页头部 -->
	<%@ include file="../../frameJsp/header.jsp" %>
	<!-- 左侧导航栏 -->
	<%@ include file="../../frameJsp/aside.jsp" %>
	<!-- 网页中部 -->
	 <div class="notepad-container">
         <!-- 左侧菜单栏 -->
        <%@ include file="../../innerJsp/notepad/left_menu.jsp" %>
         <!-- 右侧主页面 -->
        <%@ include file="../../innerJsp/notepad/editor.jsp" %>
        <div class="clear"></div>
	</div>
	<!-- 网页底部 -->
	<%@ include file="../../frameJsp/footer.jsp" %>
	<!-- 弹出层部分 -->
	<%@ include file="../../frameJsp/hidden_info.jsp" %> 
	<%@ include file="../../frameJsp/pop/login_pop.jsp" %> 
</body>
</html>