<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
  <title>Book list</title>
  <style>
    table, tr, td {
      border: 1px solid black;
      text-align:center;
    }
  </style>
</head>
<body>
<a href="/book/register">책 추가로 가기</a>
<br/>
<table>
  <thead>
  <tr>
    <td>ID</td>
    <td>제목</td>
    <td>작가</td>
    <td>페이지수</td>
    <td>사용자아이디</td>
    <td>삭제</td>
    <td></td>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="b" items="${books}">
    <tr>
      <td>${b.id}</td>
      <td>${b.title}</td>
      <td>${b.author}</td>
      <td>${b.page}</td>
      <td>${b.userId}</td>
      <td>

        <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
          <a href="/book/delete?id=${b.id}">삭제</a>
        </sec:authorize>

        <c:set var="user" value="${SPRING_SECURITY_CONTEXT.authentication.principal}"/>
        <c:if test="${ user.id eq b.userId }">
          <a href="/book/delete?id=${b.id}">삭제</a>
        </c:if>

      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>

</body>
</html>
