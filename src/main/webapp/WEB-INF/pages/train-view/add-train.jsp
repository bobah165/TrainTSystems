<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add</title>
</head>
<body>



<form action="/train/add" method="post">

    <label for="trainNumber">Train Number</label>
    <input type="number" name="trainNumber" id="trainNumber">

    <label for="trainWay">Train Way</label>
    <input type="number" name="trainWay.id" id="trainWay">

    <label for="departureDate">Shedule</label>
    <input type="date" name="departureDate" id="departureDate">

    <label for="countSits">Count of Sits</label>
    <input type="number" name="countSits" id="countSits">

    <label for="schedule">Schedule</label>
    <input type="text" name="schedule" id="schedule" list="ways">
    <datalist id="ways">
        <option>everyday</option>
        <option>other</option>
    </datalist>

    <input type="submit" value="Add new Train">


</form>

</body>
</html>
