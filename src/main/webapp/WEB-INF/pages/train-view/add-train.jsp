<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add</title>
</head>
<body>



<form action="/train/add" method="post">

    <label for="id">Id Train</label>
    <input type="number" name="id" id="id">

    <label for="countSits">Count of Sits</label>
    <input type="text" name="countSits" id="countSits">

    <input type="submit" value="Add new Train">


</form>

</body>
</html>
