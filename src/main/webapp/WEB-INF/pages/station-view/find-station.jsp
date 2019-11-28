<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Stations</title>
    <style>
        <%@include file="/WEB-INF/css/style.css"%>
    </style>
</head>
<body>


<div class="top_bar"></div>
<div class="welcome">
    <img src='<c:url value="/ROOT/train.jpg"></c:url>'  width="600" height="250"/>
</div>

<header>
    <div class="top_bar"></div>
    <nav>
        <ul class="menu">
            <li><a href="/empl">Back</a></li>
            <li><a href="/station/add">Add Station</a></li>
            <li><a href="/station/">Station List</a></li>
            <li id="quit"><a href="/logout">Quit</a> </li>
        </ul>
    </nav>
</header>


<h1 align="center">Stations</h1>


<table id="stationTable">
    <tr>
        <th>name</th>
        <th>edit</th>
        <th>delete</th>
    </tr>
    <tr>
        <td>${station.nameStation}</td>
        <td>
            <button><a href="/station/edit/${station.id}" class="btn">edit</a></button>
        </td>
        <td>
            <button><a href="/station/delete/${station.id}" class="btn">delete</a></button>
        </td>
    </tr>

</table>

</body>
</html>
