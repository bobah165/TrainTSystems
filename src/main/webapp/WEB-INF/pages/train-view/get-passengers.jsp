<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Get Passengers From Train</title>
    <style>
        <%@include file="/WEB-INF/css/style.css"%>
    </style>
    <%@include file="/WEB-INF/pages/main/employee.jsp"%>
</head>
<body>

<h1 align="center"> Passenger List</h1>

<table>
    <tr>
        <th>Ticket ID</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Birthday</th>
    </tr>
    <c:forEach var="passenger" items="${passfromtrainList}">
        <tr>
            <td>${passenger.ticketID}</td>
            <td>${passenger.name}</td>
            <td>${passenger.surname}</td>
            <td>${passenger.birthday}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
