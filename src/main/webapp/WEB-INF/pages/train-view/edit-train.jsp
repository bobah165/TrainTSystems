
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
    <input type="number" name="id" id="id">

    <label for="countSits">Count of Sits</label>
    <input type="text" name="countSits" id="countSits">

    <input type="submit" value="Edit Train">

</form>

</body>
</html>