<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 06.10.2019
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Edit Train</title>

</head>
<body>

    <c:url value="/edit" var="var"/>

<form action="${var}" method="post">

    <label for="idTrain">Id Train</label>
    <input type="text" name="idTrain" id="idTrain">

    <label for="stationName">Station</label>
    <input type="text" name="stationName" id="stationName">

    <label for="countFreeSits">Free Sits</label>
    <input type="text" name="countFreeSits" id="countFreeSits">

    <input type="submit" value="Edit Train">

</form>

</body>
</html>
