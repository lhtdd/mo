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
	<script type="text/javascript" src="${ctxStatic }/js/leisure/leisure.js"></script>
</head>
<body>
	<!-- 调整整个页面的宽高 -->
	<div class="wrapper">
		<!-- 网页头部 -->
		<%@ include file="../frameJsp/header.jsp" %> 
		<!-- 左侧导航栏 -->
		<%@ include file="../frameJsp/aside.jsp" %> 
		<!-- 网页中部 -->
		<div class="container">
	    	<!-- 左侧开心果内容-->
	    	<%@ include file="../innerJsp/leisure/happy.jsp" %> 
	    	<!-- 右侧收藏提示-->    
	    	<%@ include file="../innerJsp/leisure/happyMine.jsp" %> 
		</div>
    	<div class="clear"></div>
		<!-- 网页底部 -->
		<%@ include file="../frameJsp/footer.jsp" %> 
	</div>
	<!-- 弹出层部分  返回顶部等-->
	<ul class="fix-tip leisure-fix-tip">
		<li class="fix-tip-item feedback"><i class="iconfont icon-fankui"></i></li>
		<li class="fix-tip-item rolling-top" id="rolling-top"><i class="iconfont icon-huidingbu"></i></li>
	</ul>
</body>
</html>