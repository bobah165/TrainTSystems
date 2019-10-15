
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>Edit Train</title>

</head>
<body>


<form method="post" action="/train/edit">

    <label for="id">Id Train</label>
    <input type="text" name="id" id="id">

    <label for="startStationName">Start Station</label>
    <input type="text" name="startStationName" id="startStationName">

    <label for="endStationName">End Station</label>
    <input type="text" name="endStationName" id="endStationName">

    <label for="countFreeSits">Free Sits</label>
    <input type="text" name="countFreeSits" id="countFreeSits">

    <input type="submit" value="Edit Train">

</form>

</body>
</html>
