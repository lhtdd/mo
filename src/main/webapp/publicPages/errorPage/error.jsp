<%@ page language="java" import="java.util.*,java.io.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>sorry</title>
</head>
<body>
	出错了！
	<br> 发生了以下的错误：
	<br>
	<hr>
	getMessage():
	<br>
	<%=exception.getMessage()%><br> getLocalizedMessage():
	<br>
	<%=exception.getLocalizedMessage()%><br> PrintStatckTrace():
	<br>
	<%
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		out.println(sw);
	%><br>

</body>
</html>