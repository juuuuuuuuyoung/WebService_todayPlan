<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DOEUN
  Date: 2017-11-30
  Time: 오후 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movie</title>
</head>
<body>
<table>
    <tr>
        <td>rnum</td>
        <td>movieNm</td>
        <td>openDt</td>
        <td>targetDt<br/>
    </tr>
<c:forEach var="m" items="${movies}">
    <tr>
        <td>${m.rnum}</td>
        <td>${m.movieNm}</td>
        <td>${m.openDt}</td>
        <td>${m.targetDt}<br/>
        <td>${m.wideArea}<br/>
    </tr>
</c:forEach>
</table>
</body>
</html>
