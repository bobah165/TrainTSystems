<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add</title>
</head>
<body>

<c:url value="/train/add" var="var"/>

<form action="${var}" method="post">

    <label for="id">Id Train</label>
    <input type="text" name="id" id="id">

    <label for="startStationName">Start Station</label>
    <input type="text" name="startStationName" id="startStationName">

    <label for="endStationName">End Station</label>
    <input type="text" name="endStationName" id="endStationName">

    <label for="countFreeSits">Free Sits</label>
    <input type="text" name="countFreeSits" id="countFreeSits">

    <input type="submit" value="Add new Train">


</form>

</body>
</html>
