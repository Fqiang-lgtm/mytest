<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
欢迎${sessionScope.user.username}登录！<br>
<a href="${pageContext.request.contextPath}/user/delete.do">删除操作</a>
    欢迎你
</body>
</html>--%>


<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/common/head.jsp"%>
<div class="right">
    <img class="wColck" src="${pageContext.request.contextPath }/statics/images/clock.jpg" alt=""/>
    <div class="wFont">
        <h2>${sessionScope.user.username}</h2>
        <p>欢迎${sessionScope.user.username}来到超市订单管理系统!</p>
    </div>
</div>
</section>
<%@include file="/WEB-INF/pages/common/foot.jsp" %>
