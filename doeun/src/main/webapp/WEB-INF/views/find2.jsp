<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: DOEUN
  Date: 2017-11-30
  Time: 오후 9:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movie</title>
</head>
<body>
<form:form modelAttribute="movie">
    targetDt: <form:input path="targetDt"/>
    <input type="submit" value="findMovie"/>
</form:form>

</body>
</html>
