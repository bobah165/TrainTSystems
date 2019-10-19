
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Timetable</title>
</head>
<body>

<form action="/timetable/edit" method="post">

    <input type="hidden" name="id" value="${timetable.id}">

    <input type="hidden" name="train.id" value="${timetable.train.id}">

    <input type="hidden" name="station.id" value="${timetable.station.id}">

    <label for="arrivalTime">Arrival Time</label>
    <input type="text" name="arrivalTime" id="arrivalTime">

    <label for="departureTime">Departure Time</label>
    <input type="text" name="departureTime" id="departureTime">

    <label for="countFreeSits">Count Free Sits</label>
    <input type="text" name="countFreeSits" id="countFreeSits">

    <input type="submit" value="Edit Timetable">

</form>

</body>
</html>
