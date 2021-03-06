<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<%@ include file="/common/common.jsp" %>
<head>
	<base href="<%=basePath %>">
	<title>默--闲暇小憩</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="Keywords" content="默,mo,闲暇小憩,笑话,段子,糗事,开心一笑,休闲一刻,闲暇小憩">
	<meta name="Description" content="一些段子,笑话,开心果。在忙碌的工作,学习,生活中可以有一点可以轻松一笑的乐园。">
	<link rel="stylesheet" href="${ctxStatic }/css/main.css">
	<link rel="stylesheet" href="${ctxStatic }/css/leisure/leisure.css">
	<script type="text/javascript" src="${ctxStatic }/js/leisure/leisure.js?r=<%=Math.random() %>"></script>
</head>
<body>
	<!-- 网页头部 -->
	<%@ include file="../../frameJsp/header.jsp" %>
	<!-- 左侧导航栏 -->
	<%@ include file="../../frameJsp/aside.jsp" %>
	<!-- 网页中部 -->
	<div class="layui-container">
		<div class="layui-row layui-col-space10">
			<!-- 左侧开心果内容-->
			<div class="layui-col-md8">
				<ul class="happy-list">
					<%-- 流式加载 happy-item --%>
				</ul>
			</div>
			<!-- 右侧收藏提示-->
			<div class="layui-col-md4">
				<%@ include file="../../innerJsp/leisure/happy_mine.jsp" %>

			</div>
		</div>
	</div>
	<!-- 网页底部 -->
	<%@ include file="../../frameJsp/footer.jsp" %>
	<!-- 弹出层部分  返回顶部等-->
	<%@ include file="../../frameJsp/pop/fix_top.jsp" %>
	<%@ include file="../../frameJsp/pop/login_pop.jsp" %>
	<%@ include file="../../frameJsp/hidden_info.jsp" %>
</body>
</html>