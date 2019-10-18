<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Get Tarins from Station</title>
</head>
<body>

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
