
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Stations</title>
</head>
<body>


<table>
    <tr>
        <th>name</th>
        <th>action</th>
    </tr>
    <c:forEach var="station" items="${stationsList}">
        <tr>
            <td>${station.nameStation}</td>
            <td>
                <a href="/station/edit/${station.id_station}">edit</a>
                <a href="/station/delete/${station.id_station}">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<h2>ADD</h2>
<c:url value="/station/add" var="addStation" />
<a href="${addStation}">Add new Station</a>

</body>
</html>