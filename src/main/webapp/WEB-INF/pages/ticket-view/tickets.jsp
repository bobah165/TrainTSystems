
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Tickets</title>
    <style>
        <%@include file="/WEB-INF/css/style.css"%>
    </style>
</head>
<body>

<table>
    <tr>
        <th>Train</th>
        <th>Passenger</th>
        <th>action</th>
    </tr>
    <c:forEach var="ticket" items="${ticketsList}">
        <tr>
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
