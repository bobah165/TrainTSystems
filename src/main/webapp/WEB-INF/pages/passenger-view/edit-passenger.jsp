
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Edit Passenger</title>
</head>
<body>


<form action="/passenger/edit" method="post">

    <input type="hidden" name="id" value="${passenger.id}">

    <label for="name">Name </label>
    <input type="text" name="name" id="name">

    <label for="surname">Surname</label>
    <input type="text" name="surname" id="surname">

    <label for="birthday">Birthday</label>
    <input type="text" name="birthday" id="birthday">

    <label for="login">login</label>
    <input type="text" name="login" id="login">

    <label for="password">password</label>
    <input type="text" name="password" id="password">

    <label for="train">Id Train</label>
    <input type="number" name="train.id" id="train">

    <input type="submit" value="Edit Passenger">

</form>

</body>
</html>
