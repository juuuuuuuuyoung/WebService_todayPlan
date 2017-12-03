<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8"/>
    <title>Weather</title>

    <script>
        navigator.geolocation.getCurrentPosition(function(position){
            console.log('latitude: ', position.coords.latitude);
            console.log('longitude: ', position.coords.longitude);
        });

        function success(position) {
            var latitude, longitude;
            latitude = position.coords.latitude;
            longitude = position.coords.longitude;

            document.getElementById("lat").value = latitude;
            document.getElementById("lon").value = longitude;
        };
        function error() {};


        navigator.geolocation.getCurrentPosition(success, error);
        function getCurLocation () {
            navigator.geolocation.getCurrentPosition(success, error);
        }
    </script>
</head>

<body>

<button onclick="getAppKey()">인증키 받기</button>
<p id="appKey"></p>

<form:form modelAttribute="weather">
    province: <form:input path="province"/><br/>
    city <form:input path="city"/><br/>
    region: <form:input path="region"/><br/>
    <input type="submit" value="해당 위치의 날씨 찾기"/>
</form:form>

<form:form modelAttribute="weather">
    <form:hidden id="lat" path="lat"/>
    <form:hidden id="lon" path="lon"/>

    <input type="submit" onclick="getCurLocation()" value="현재 위치로 날씨 찾기"/>
</form:form>

</body>
</html>
