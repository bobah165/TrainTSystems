
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Way</title>
    <style>
        <%@include file="/WEB-INF/css/style.css"%>
    </style>
</head>
<body>


<form action="/way/edit" method="post">

    <input type="hidden" name="id" value="${way.id}">

    <label for="numberWay">Number Way</label>
    <input type="number" name="numberWay" id="numberWay">

    <label for="stationId">Id Station</label>
    <input type="number" name="station.id" id="stationId">

    <label for="departureTime">Departure Time</label>
    <input type="time" name="departureTime" id="departureTime">

    <label for="arrivalTime">Arrival Time</label>
    <input type="time" name="arrivalTime" id="arrivalTime">

    <label for="daysInWay">Days in way</label>
    <input type="text" name="daysInWay" id="daysInWay">


    <input type="submit" value="Edit Way">

</form>

</body>
</html>
