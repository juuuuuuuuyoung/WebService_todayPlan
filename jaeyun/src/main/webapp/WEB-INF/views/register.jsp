<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: acorn
  Date: 2017-10-25
  Time: 오후 12:00
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>Register Book</title>
</head>
<body>
<c:set var="user" value="${SPRING_SECURITY_CONTEXT.authentication.principal}"/>
<form:form modelAttribute="book">
    title: <form:input path="title"/><br/>
    author: <form:input path="author"/><br/>
    page: <form:input path="page"/><br/>
    userId: <form:input path="userId"/><br/>
    <input type="submit" value="register"/>
</form:form>
</body>
</html>
