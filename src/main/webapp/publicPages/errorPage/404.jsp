<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>sorry</title>
</head>
<body>
	404：未找到你要打开的页面！<br>
	(错误页面内容小于1024byte,IE浏览器将替换掉此页面而展示它自己的提示页面)。<br>
	优雅REST风格的资源URL不希望带 .html 或 .do 等后缀.由于早期的Spring MVC不能很好地处理静态资源，所以在web.xml中配置DispatcherServlet的请求映射，往往使用 *.do 、 *.xhtml等方式。这就决定了请求URL必须是一个带后缀的URL，而无法采用真正的REST风格的URL。

如果将DispatcherServlet请求映射配置为"/"，则Spring MVC将捕获Web容器所有的请求，包括静态资源的请求，Spring MVC会将它们当成一个普通请求处理，因此找不到对应处理器将导致错误。

如何让Spring框架能够捕获所有URL的请求，同时又将静态资源的请求转由Web容器处理，是可将DispatcherServlet的请求映射配置为"/"的前提。由于REST是Spring3.0最重要的功能之一，所以Spring团队很看重静态资源处理这项任务，给出了堪称经典的两种解决方案。

先调整web.xml中的DispatcherServlet的配置，使其可以捕获所有的请求：
</body>
</html>