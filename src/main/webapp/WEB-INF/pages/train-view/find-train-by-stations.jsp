
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find train by stations</title>
</head>
<body>

<form method="post" action="/findtrain/trainfromstations/">

    <label for="stationA">Departure Station</label>
    <input type="text" name="stationA" id="stationA">

    <label for="stationB">Arrival Station</label>
    <input type="text" name="stationB" id="stationB">

    <label for="depDate">Departure Date</label>
    <input type="date" name="departureDate" id="depDate">

    <label for="startTime">Start Time</label>
    <input type="time" name="startTime" id="startTime">

    <label for="endTime">End Time</label>
    <input type="time" name="endTime" id="endTime">

    <input type="submit" value="Find Train">


</form>


</body>
</html>
