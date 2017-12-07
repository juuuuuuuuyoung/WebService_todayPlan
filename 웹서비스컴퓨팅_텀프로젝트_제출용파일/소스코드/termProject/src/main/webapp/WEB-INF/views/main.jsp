<!DOCTYPE html>
<html>
<head>
    <style>
        #map {
            height: 400px;
            width: 100%;
        }
    </style>
    <script>
        navigator.geolocation.getCurrentPosition(function(position){
            console.log('latitude: ', position.coords.latitude);
            console.log('longitude: ', position.coords.longitude);
        });
        function success(position) {
            latitude  = position.coords.latitude;
            longitude = position.coords.longitude;

            var text = latitude+','+longitude;
            document.getElementById("loc").value = text;
        };
        function error() {};
        navigator.geolocation.getCurrentPosition(success, error);

    </script>

    <script>
        function initMap() {
            var uluru = {lat: 37, lng: 127};
            var map = new google.maps.Map(document.getElementById('map'), {
                zoom: 4,
                center: uluru
            });
            var marker = new google.maps.Marker({
                position: uluru,
                map: map
            });
        }
    </script>
</head>
<body>
<%--<h3>My Google Maps Demo</h3>--%>
<%--<div id="map"></div>--%>

<%--<script async defer--%>
        <%--src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDdM9MDSZtIA-K2q0Jfbo6Gj0sFZt9QkCU&callback=initMap">--%>
<%--</script>--%>
<h1> Web Service Computing 2017-2 </h1>
<h2> project </h2>


</body>
</html>