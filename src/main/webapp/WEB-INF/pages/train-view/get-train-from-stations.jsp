<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Get Trains From Stations</title>
</head>
<body>

<table>
    <tr>
        <th>Train ID</th>
        <th>Departure Station</th>
        <th>Arrival Station</th>
        <th>Departure Date</th>
        <th>Arrival Date</th>
        <th>count of free sits</th>
        <th>Buy</th>
    </tr>
    <c:forEach var="trainAB" items="${trainListfromAtoB}">
        <tr>
            <td>${trainAB.trainID}</td>
            <td>${trainAB.deprtureStation}</td>
            <td>${trainAB.arrivalStation}</td>
            <td>${trainAB.departureTime}</td>
            <td>${trainAB.arrivalTime}</td>
            <td>${trainAB.countFreeSits}</td>
            <td>
                <a href="/buy/passenger/${trainAB.trainID}">buy ticket</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
