
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Trains</title>

    <style>
        <%@include file="/WEB-INF/css/style.css"%>
    </style>
    <%@include file="/WEB-INF/pages/main/employee.jsp"%>

</head>

<body>

<h1 align="center"> Trains </h1>

<table>
    <tr>
        <th>Train Number</th>
        <th>Way</th>
        <th>count of sits</th>
        <th>Departure Date</th>
        <th>Passengers</th>
        <th>Edit Train</th>
        <th>Delete Train</th>
    </tr>
    <c:forEach var="train" items="${trainList}">
        <tr>
            <td>${train.trainNumber}</td>
            <td>${train.trainWay.id}</td>
            <td>${train.countSits}</td>
            <td>${train.departureDate}</td>
            <td>
                <button class=""><a href="/train/passfromtrain/${train.id}" class="btn">Passengers</a></button>
            </td>
            <td>
               <button><a href="/train/edit/${train.id}" class="btn">edit</a></button>
            </td>
            <td>
                <button><a href="/train/delete/${train.id}" class="btn">delete</a></button>
            </td>
        </tr>
    </c:forEach>
</table>


<%--<h2>ADD</h2>--%>
<%--<c:url value="/train/add" var="add" />--%>
<%--<a href="${add}">Add new Train</a>--%>

<%--<h2>Find</h2>--%>
<%--<c:url value="/buy/trainfromstations" var="find" />--%>
<%--<a href="${find}">Find Train</a>--%>

</body>
</html>
