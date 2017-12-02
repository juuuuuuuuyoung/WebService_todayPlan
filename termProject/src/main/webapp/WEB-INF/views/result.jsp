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
    <title>Weather</title>
</head>
<body>
    location : ${location}<br/>
    sky : ${sky}<br/>
    tmin : ${tmin}<br/>
    tmax : ${tmax}<br/>
    dustValue : ${dustValue}<br/>
    dustGrade : ${dustGrade}<br/>
</body>
</html>
