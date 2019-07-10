<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %> 
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<base href="<%=basePath %>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>默--闲暇小憩</title>
	<link rel="stylesheet" href="${ctxStatic }/css/leisure/leisure.css">	
	<script type="text/javascript" src="${ctxStatic }/js/leisure/leisure_mine.js?r=<%=Math.random() %>"></script>
</head>
<body>
	<!-- 网页头部 -->
	<%@ include file="../../frameJsp/header.jsp" %>
	<!-- 左侧导航栏 -->
	<%@ include file="../../frameJsp/aside.jsp" %>
	<!-- 网页中部 -->
	<div class="container">
		<!-- 左侧开心果内容-->
		<%@ include file="../../innerJsp/leisure/happy.jsp" %>
	</div>
	<div class="clear"></div>
	<!-- 网页底部 -->
	<%@ include file="../../frameJsp/footer.jsp" %>
	<!-- 弹出层部分  返回顶部等-->
	<%@ include file="../../frameJsp/pop/fix_top.jsp" %> 
	<%@ include file="../../frameJsp/pop/login_pop.jsp" %>
	<%@ include file="../../frameJsp/hidden_info.jsp" %>
</body>
</html>