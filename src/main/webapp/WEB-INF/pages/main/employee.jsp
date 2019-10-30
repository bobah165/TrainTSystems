<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee</title>
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
            <li><a href="/train/">Train List</a> </li>
            <li><a href="/station/">Stations List</a> </li>
            <li><a href="/train/add/">Add Train</a> </li>
            <li><a href="/station/add/">Add Station</a> </li>
            <li><a href="/logout">Quit</a> </li>
        </ul>
    </nav>
</header>

</body>
</html>
