
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>Edit Train</title>

</head>
<body>


<form method="post" action="/train/edit">

    <input type="hidden" name="id" value="${train.id}">

    <label for="trainNumber">Train Number</label>
    <input type="number" name="trainNumber" id="trainNumber" value="${train.trainNumber}">

    <label for="trainWay">Train Way</label>
    <input type="number" name="trainWay.id" id="trainWay" value="${train.trainWay.id}">

    <label for="departureDate">Departure Date</label>
    <input type="date" name="departureDate" id="departureDate" value="${train.departureDate}">

    <label for="countSits">Count of Sits</label>
    <input type="text" name="countSits" id="countSits" value="${train.countSits}">

    <input type="submit" value="Edit Train">

</form>

</body>
</html>
