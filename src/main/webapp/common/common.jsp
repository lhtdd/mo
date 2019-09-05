<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% String path = request.getContextPath();     
   String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
   String  url = request.getScheme()+"://" + request.getServerName() + ":" + request.getServerPort() + request.getAttribute("javax.servlet.forward.request_uri").toString();
   if (request.getQueryString()!=null){   
	   url+="?"+request.getQueryString();           
	}
    Date today = new Date();
%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<c:set var="ctxStatic" value="${pageContext.request.contextPath }/staticSource"></c:set>
<c:set var="currentURL" value="<%=url %>"></c:set>
<c:set var="today" value="<%=today %>"></c:set>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="renderer" content="webkit">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
<meta http-equiv="Expires" content="-1">
<link rel="shortcut icon" href="${ctx }/favicon.ico" />
<link rel="stylesheet" href="${ctxStatic }/plugin/layui/css/layui.css"></link>
<link rel="stylesheet" href="${ctxStatic }/css/global.css">
<link rel="stylesheet" href="${ctxStatic }/css/common.css">
<link rel="stylesheet" href="${ctxStatic }/font/iconfont.css"></link>
<link rel="stylesheet" href="${ctxStatic }/css/aside.css">
<link rel="stylesheet" href="${ctxStatic }/css/header.css">
<link rel="stylesheet" href="${ctxStatic }/css/footer.css">	
<!-- <link href="//cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet"> -->
<!-- 以下为了让bootstrap 在IE8中也能正常显示，但是好像没起作用啊 -->
<!--[if lt IE 9]>
  <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
  <script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.js"></script>
<![endif]-->
<!-- <script src="//cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script> -->
<!-- Jquery 2.0 以上的版本不再支持IE8及以下浏览器 -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="${ctxStatic }/plugin/layui/layui.js"></script>
<script src="https://cdn.bootcss.com/jquery.nicescroll/3.7.6/jquery.nicescroll.min.js"></script>
<script type="text/javascript" src="${ctxStatic }/js/tool.js?r=<%=Math.random() %>"></script>
<script type="text/javascript" src="${ctxStatic }/js/base.js?r=<%=Math.random() %>"></script>