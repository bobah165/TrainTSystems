
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Train Between Stations</title>
    <style>
        <%@include file="/WEB-INF/css/style.css"%>
    </style>
    <%@include file="/WEB-INF/pages/main/passenger.jsp"%>
</head>
<body>

<form action="/findtrain/trainfromstations/" method="post" class="railway">
    <div class="stripes-block">
        <div class="line"></div>
    </div>
    <h3>Заполните форму поиска поездов</h3>

    <div class="form-group">
        <input type="text" name="stationA" id="stationA" required><label for="stationA">Departure Station</label>
        <input type="text" name="stationB" id="stationB" required><label for="stationB">Arrival Staton</label>
        <input type="date" name="departureDate" id="departureDate" required><label for="departureDate">Departure Date</label>
        <input type="time" name="startTime" id="startTime" required><label for="startTime">Start time</label>
        <input type="time" name="endTime" id="endTime" required><label for="endTime">End time</label>
    </div>
    <div class="submit-block">
        <div class="submit-button">
            <i class="fa fa-train" aria-hidden="true"></i><br>
            <input type="submit" value="Найти">
        </div>
    </div>
</form>

</body>
</html>
