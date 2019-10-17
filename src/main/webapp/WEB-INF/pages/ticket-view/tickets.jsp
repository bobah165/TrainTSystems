
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Tickets</title>
</head>
<body>

<table>
    <tr>
        <th>departure station</th>
        <th>arrival station</th>
        <th>departure date</th>
        <th>arrival date</th>
        <th>Train</th>
        <th>Passenger</th>
        <th>action</th>
    </tr>
    <c:forEach var="ticket" items="${ticketsList}">
        <tr>
            <td>${ticket.departureStation}</td>
            <td>${ticket.arrivalStation}</td>
            <td>${ticket.departureDate}</td>
            <td>${ticket.arrivalDate}</td>
            <td>${ticket.train.id}</td>
            <td>${ticket.passenger.id}</td>
            <td>
                <a href="/ticket/edit/${ticket.id}">edit</a>
                <a href="/ticket/delete/${ticket.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<h2>ADD</h2>
<c:url value="/ticket/add" var="add" />
<a href="${add}">Add new Ticket</a>


</body>
</html>
