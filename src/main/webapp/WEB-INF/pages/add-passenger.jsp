
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Add Passenger</title>
</head>
<body>



<form method="post" action="/passenger/add">

    <label for="name">Name </label>
    <input type="text" name="name" id="name">

    <label for="surname">Surname</label>
    <input type="text" name="surname" id="surname">

    <label for="birthday">Birthday</label>
    <input type="text" name="birthday" id="birthday">

    <input type="submit" value="Add Passenger">
    
</form>


</body>
</html>
