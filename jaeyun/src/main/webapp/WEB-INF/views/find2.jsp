<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
