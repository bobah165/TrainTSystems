<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ways</title>
    <style>
        <%@include file="/WEB-INF/css/style.css"%>
    </style>
</head>
<body>

<table>
    <tr>
        <th>Way number</th>
        <th>Station ID</th>
        <th>Departure Time</th>
        <th>Arrival Time</th>
        <th>Days in way</th>
        <th>action</th>
    </tr>
    <c:forEach var="way" items="${wayList}">
        <tr>
            <td>${way.numberWay}</td>
            <td>${way.station.id}</td>
            <td>${way.departureTime}</td>
            <td>${way.arrivalTime}</td>
            <td>${way.daysInWay}</td>

            <td>
                <a href="/way/edit/${way.id}">edit</a>
                <a href="/way/delete/${way.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<h2>ADD</h2>
<c:url value="/way/add" var="add" />
<a href="${add}">Add new Way</a>

</body>
</html>
