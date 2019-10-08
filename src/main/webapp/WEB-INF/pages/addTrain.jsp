<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 08.10.2019
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add</title>
</head>
<body>

<c:url value="/add" var="var"/>

<form action="${var}" method="post">

    <label for="idTrain">Id Train</label>
    <input type="text" name="idTrain" id="idTrain">

    <label for="stationName">Station</label>
    <input type="text" name="stationName" id="stationName">

    <label for="countFreeSits">Free Sits</label>
    <input type="text" name="countFreeSits" id="countFreeSits">

    <input type="submit" value="Add new Train">


</form>

</body>
</html>
