
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Passengers</title>

    <style>
        <%@include file="/WEB-INF/css/style.css"%>
    </style>

</head>
<body>

<table>
    <tr>
        <th>name</th>
        <th>surname</th>
        <th>birthday</th>
        <th>login</th>
        <th>password</th>
        <th>Email</th>
        <th>User Status</th>
        <th>action</th>
    </tr>
    <c:forEach var="passenger" items="${passengersList}">
    <tr>
        <td>${passenger.name}</td>
        <td>${passenger.surname}</td>
        <td>${passenger.birthday}</td>
        <td>${passenger.login}</td>
        <td>${passenger.password}</td>
        <td>${passenger.email}</td>
        <td>${passenger.user}</td>
        <td>
            <a href="/passenger/edit/${passenger.id}">edit</a>
            <a href="/passenger/delete/${passenger.id}">delete</a>
        </td>
    </tr>
    </c:forEach>
</table>

<h2>ADD</h2>
<c:url value="/passenger/add" var="addPass" />
<a href="${addPass}">Add new Passenger</a>

</body>
</html>
