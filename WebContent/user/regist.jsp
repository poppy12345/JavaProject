<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function _change(){
	var ele=document.getElementById("vcodeID");
	ele.src="<c:url value='/VerifyCodeServlet'/>?xxx="+new Date().getTime();
}
</script>
</head>
<body>
<h1>注册</h1>
<p style="color:red;font-weight:900">${msg }</p>
<form action="<c:url value='/RegistServlet'/>" method="post">
用户名：<input type="text" name="username" value="${form.username }">${errors.username }<br>
 密    码：<input type="password" name="password" value="${form.password }">${errors.password }<br>
验证码：<input type="text" name="verifyCode" value="${user.verifyCode}" size="3">
	    <img id="vcodeID" src="<c:url value='/VerifyCodeServlet'/>" border="1">
	    <a href="javascript:_change()">换一张</a>${errors.verifycode }<br>
     <input type="submit" value="注册">
</form>
</body>
</html>