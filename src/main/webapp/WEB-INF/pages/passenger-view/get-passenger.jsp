
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Get Passenger</title>
</head>
<body>

<table>
<tr>
    <th>name</th>
    <th>surname</th>
    <th>train</th>
</tr>
<c:forEach var="passenger" items="${passengersList}">
    <tr>
        <td>${passenger.name}</td>
        <td>${passenger.surname}</td>
        <td>${passenger.trainId}</td>
    </tr>
</c:forEach>
</table>


</body>
</html>
