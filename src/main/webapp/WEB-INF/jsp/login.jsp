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
<script type="text/javascript" src="${ctxStatic }/js/login.js"></script>
</head>
<body>
	<!-- 调整整个页面的宽高 -->
	<div class="wrapper">
		<!-- 网页中部 -->
		<div class="container">
			<div class="login-header">
				<a href="http://10.186.106.131:8081/mo"><img
					src="${ctxStatic }/image/logo.png" alt="LOGO 默" title="LOGO MO" /></a>
				<div class="mo-explain">
					<span class="fs14 cl99">一种态度，一种沉淀...</span> <span class="fs18 cl99">恭默思道。——《书·说命》</span>
				</div>
			</div>
			<!--  -->
			<div class="submit-wrapper">
			<c:if test="${type == 'register' }">
		    	<!--  注册   --> 
		    	<%@ include file="../innerJsp/login/registerInfo.jsp" %> 
			</c:if>
			<c:if test="${type != 'register' }">
				<!-- 登录 -->
		    	<%@ include file="../innerJsp/login/loginInfo.jsp" %> 
			</c:if>
			</div>
		</div>
		<div class="clear"></div>
	</div>
</body>
</html>