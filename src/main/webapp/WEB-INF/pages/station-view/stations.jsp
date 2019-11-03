
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Stations</title>
    <style>
        <%@include file="/WEB-INF/css/style.css"%>
    </style>
    <%@include file="/WEB-INF/pages/main/employee.jsp"%>
</head>
<body>

<h1 align="center">Stations</h1>


<table>
    <tr>
        <th>name</th>
        <th>edit</th>
        <th>delete</th>
    </tr>
    <c:forEach var="station" items="${stationsList}">
        <tr>
            <td>${station.nameStation}</td>
            <td>
                <button><a href="/station/edit/${station.id}" class="btn">edit</a></button>
            </td>
            <td>
                <button><a href="/station/delete/${station.id}" class="btn">delete</a></button>
            </td>
        </tr>
    </c:forEach>
</table>

<h2>ADD</h2>
<c:url value="/station/add" var="addStation"/>
<a href="${addStation}">Add new Station</a>

</body>
</html>
