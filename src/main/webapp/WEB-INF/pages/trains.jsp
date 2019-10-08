<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 06.10.2019
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
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
        <th>Station</th>
        <th>count of sits</th>
        <th>action</th>
    </tr>
    <c:forEach var="train" items="${trainList}">
        <tr>
            <td>${train.idTrain}</td>
            <td>${train.stationName}</td>
            <td>${train.countFreeSits}</td>
            <td>
                <a href="/edit/${train.idTrain}">edit</a>
                <a href="/delete/${train.idTrain}">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<h2>ADD</h2>
<c:url value="/add" var="add" />
<a href="${add}">Add new Train</a>

</body>
</html>
