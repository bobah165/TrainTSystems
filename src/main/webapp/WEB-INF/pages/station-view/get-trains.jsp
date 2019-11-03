<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Get Tarins from Station</title>
    <style>
        <%@include file="/WEB-INF/css/style.css"%>
    </style>

    <%@include file="/WEB-INF/pages/main/passenger.jsp"%>
</head>
<body>

<h1 align="center"> Schedule</h1>

<table>
    <tr>
        <th>Train</th>
        <th>Station</th>
        <th>Departure Time</th>
        <th>Arrival Time</th>
    </tr>
    <c:forEach var="train" items="${trainsList}">
        <tr>
            <td>${train.idTrain}</td>
            <td>${train.nameStation}</td>
            <td>${train.departureTime}</td>
            <td>${train.arrivalTime}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
