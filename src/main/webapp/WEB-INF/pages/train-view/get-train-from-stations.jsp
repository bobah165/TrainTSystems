<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Get Trains From Stations</title>
    <style>
        <%@include file="/WEB-INF/css/style.css"%>
    </style>
    <%@include file="/WEB-INF/pages/main/passenger.jsp"%>
</head>
<body>

<h1 align='center'> Schedule </h1>


<table>
    <tr>
        <th>Train Number</th>
        <th>Departure Station</th>
        <th>Arrival Station</th>
        <th>Departure Time</th>
        <th>Arrival Time</th>
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
                <c:if test="${trainAB.countFreeSits>0}">
                <a href="/train/buy/${trainAB.trainID},${trainAB.deprtureStation},${trainAB.arrivalStation},${trainAB.arrivalTime},${trainAB.departureTime}">buy ticket</a>
                </c:if>
                <c:if test="${trainAB.countFreeSits==0}">
                    No seats
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
