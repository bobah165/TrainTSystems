
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Timetable</title>
</head>
<body>

<form action="/timetable/add" method="post">

    <label for="idTrain">Train</label>
    <input type="text" name="train.id" id="idTrain">

    <label for="idStation">Station</label>
    <input type="text" name="station.id_station" id="idStation">

    <label for="arrivalTime">Arrival Time</label>
    <input type="text" name="arrivalTime" id="arrivalTime">

    <label for="departureTime">Departure Time</label>
    <input type="text" name="departureTime" id="departureTime">

    <label for="countFreeSits">Count Free Sits</label>
    <input type="text" name="countFreeSits" id="countFreeSits">

    <input type="submit" value="Add new Timetable">

</form>

</body>
</html>
