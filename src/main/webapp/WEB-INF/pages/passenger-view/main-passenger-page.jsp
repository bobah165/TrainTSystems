
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Passenger</title>
    <style>
        <%@include file="/WEB-INF/css/style.css"%>
    </style>
    <%@include file="/WEB-INF/pages/main/passenger.jsp"%>
</head>
<body>




<h1 align="center">Welcome ${passengerName}</h1>

<div class="didyouknow" align="center" style="margin-left: 300px; margin-right: 300px">
    <img align="center" src='<c:url value="/ROOT/didYouKnow.jpg"></c:url>'  width="300" height="100"/>
    <h3 align="center">${text}</h3>
</div>

</body>
</html>
