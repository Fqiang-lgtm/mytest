<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/11/27
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false"  language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath }/user/upload.do" >
        上传头像：<input type="file" name="image"/><br>
        <input type="submit" value="上传"/>
    </form>
</div>
<div>
    <form method="post" enctype="multipart/form-data"action="${pageContext.request.contextPath}/user/upload2.do">
        上传图片1：<input type="file" name="image"/><br>
        上传图片2：<input type="file" name="image"/><br>
        上传图片3：<input type="file" name="image"/><br>
        <input type="submit" value="上传"/>
    </form>
</div>
<div>
    <h2>点击图片下载</h2>
    <a href="${pageContext.request.contextPath}/user/download.do/blossom1.jpg">
        <img src="${pageContext.request.contextPath}/images/blossom1.jpg" width="300px"/>
    </a>
    <a href="${pageContext.request.contextPath}/user/download.do/blossom2.jpg">
        <img src="${pageContext.request.contextPath}/images/blossom2.jpg" width="300px"/>
    </a>
    <a href="${pageContext.request.contextPath}/user/download.do/Desert.jpg">
        <img src="${pageContext.request.contextPath}/images/Desert.jpg" width="300px"/>
    </a>
    <a href="${pageContext.request.contextPath}/user/download.do/花城.jpg">
        <img src="${pageContext.request.contextPath}/images/花城.jpg" width="300px"/>
    </a>
    <a href="${pageContext.request.contextPath}/user/download.do/菊花.jpg">
        <img src="${pageContext.request.contextPath}/images/菊花.jpg" width="300px"/>
    </a>

</div>


<%--hello,${requestScope.user.username}--%>
<%--你今年${requestScope.user.age}岁，--%>
<%--密码是${requestScope.user.pwd}<br>--%>
<%--${requestScope.username}!!今年${requestScope.age}岁了！--%>
<%--${sessionScope.username}!!今年${sessionScope.age}岁了！--%>
</body>
</html>
