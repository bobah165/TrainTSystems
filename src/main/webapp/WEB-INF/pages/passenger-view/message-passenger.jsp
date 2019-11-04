<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Message</title>
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
            <li><a href="/login">Login</a> </li>
            <li><a href="/registration">Registration</a> </li>
        </ul>
    </nav>
</header>

<h1 align="center"> Wrong Data in Registration Form <br>
        or This Passenger is Exist</h1>


</body>
</html>
