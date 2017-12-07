<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h5>입력하세요!</h5>
<script>
        navigator.geolocation.getCurrentPosition(function(position){
            console.log('latitude: ', position.coords.latitude);
            console.log('longitude: ', position.coords.longitude);
        });
        function success(position) {
            var latitude  = position.coords.latitude;
            var longitude = position.coords.longitude;

            var text = latitude+','+longitude;
            document.getElementById("loc").value = text;
        };
        function error() {};
        navigator.geolocation.getCurrentPosition(success, error);

</script>

    <form:form modelAttribute="festival">
        <form:hidden path="id"/>
        location: <form:input id ="loc" path="location"/><br/>
        destination: <form:input  path="destination"/><br/>
        <input type="submit" value="Register"/>
    </form:form>
</body>
</html>