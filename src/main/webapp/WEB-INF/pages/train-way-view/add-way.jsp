
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add way</title>
    <style>
        <%@include file="/WEB-INF/css/style.css"%>
    </style>
</head>
<body>


<form action="/way/add" method="post">

    <label for="numberWay">Number Way</label>
    <input type="number" name="numberWay" id="numberWay">

    <label for="stationId">Id Station</label>
    <input type="number" name="station.id" id="stationId">

    <label for="schedule">shedule</label>
    <input type="time" name="shedule" id="schedule">

    <label for="stopTime">stop time</label>
    <input type="time" name="stopTime" id="stopTime">

    <label for="daysInWay">Days in way</label>
    <input type="text" name="daysInWay" id="daysInWay">


    <input type="submit" value="Add new Way">

</form>

</body>
</html>
