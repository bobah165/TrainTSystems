
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Trains</title>
</head>

<body>
<table>
    <tr>
        <th>Train ID</th>
        <th>Start Station</th>
        <th>End Station</th>
        <th>count of sits</th>
        <th>Passengers</th>
        <th>action</th>
    </tr>
    <c:forEach var="train" items="${trainList}">
        <tr>
            <td>${train.id}</td>
            <td>${train.startStationName}</td>
            <td>${train.endStationName}</td>
            <td>${train.countFreeSits}</td>
            <td>
                <a href="/passfromtrain/${train.id}">passengers</a>
            </td>
            <td>
                <a href="/train/edit/${train.id}">edit</a>
                <a href="/train/delete/${train.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<h2>ADD</h2>
<c:url value="/train/add" var="add" />
<a href="${add}">Add new Train</a>

</body>
</html>
