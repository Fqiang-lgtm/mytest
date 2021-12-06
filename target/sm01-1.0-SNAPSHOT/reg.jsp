<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/11/29
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/param2/reg" method="post">
    用户名：<input name="username"><br/>
    密码：<input name="pwd"><br/>
    年龄：<input name="age"><br/>
    <input type="submit" value="reg">
</form>
<a href="${pageContext.request.contextPath}/user/delete.do">删除操作</a>
</body>
</html>
