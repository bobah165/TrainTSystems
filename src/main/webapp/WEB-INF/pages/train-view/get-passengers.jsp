<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Get Passengers From Train</title>
</head>
<body>

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
