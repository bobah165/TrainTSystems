<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Timetables</title>
</head>
<body>



<table>
    <tr>
        <th>Train</th>
        <th>Station</th>
        <th>arrival time</th>
        <th>departure time</th>
        <th>count free sits</th>
        <th>action</th>
    </tr>
    <c:forEach var="timetable" items="${timetablesList}">
        <tr>
            <td>${timetable.train.id}</td>
            <td>${timetable.station.id}</td>
            <td>${timetable.arrivalTime}</td>
            <td>${timetable.departureTime}</td>
            <td>${timetable.countFreeSits}</td>
            <td>
                <a href="/timetable/edit/${timetable.id}">edit</a>
                <a href="/timetable/delete/${timetable.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<h2>ADD</h2>
<c:url value="/timetable/add" var="add" />
<a href="${add}">Add new Timetable</a>

</body>
</html>
