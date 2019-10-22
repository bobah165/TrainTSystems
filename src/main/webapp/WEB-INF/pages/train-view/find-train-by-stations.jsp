
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find train by stations</title>
</head>
<body>

<form method="post" action="/buy/trainfromstations/">


    <label for="stationA">Departure Station</label>
    <input type="text" name="stationA" id="stationA">

    <label for="stationB">Departure Station</label>
    <input type="text" name="stationB" id="stationB">

    <input type="submit" value="Find Train">


</form>

</body>
</html>
