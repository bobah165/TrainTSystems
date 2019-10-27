
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find Trains</title>
</head>
<body>

<form action="/station/findtrains" method="post">

    <label for="nameStation">Station Name</label>
    <input type="text" name="nameStation" id="nameStation">

    <label for = "departureDate" >Departute Date</label>
    <input type="date" name="departureDate" id="departureDate">

    <label for = "startTime" >Start Time</label>
    <input type="time" name="startTime" id="startTime">

    <label for = "endTime" >End Time</label>
    <input type="time" name="endTime" id="endTime">

    <input type="submit" value="Find">

</form>

</body>
</html>
