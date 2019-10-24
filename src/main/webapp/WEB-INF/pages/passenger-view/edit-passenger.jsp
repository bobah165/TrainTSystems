
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
    <input type="date" name="birthday" id="birthday">

    <label for="login">login</label>
    <input type="text" name="login" id="login">

    <label for="password">password</label>
    <input type="text" name="password" id="password">

    <label for="email">Email</label>
    <input type="text" name="email" id="email">

    <label for="user">user</label>
    <input type="text" name="user" id = "user" list="users">
    <datalist id="users">
        <option>passenger</option>
        <option>employee</option>
    </datalist>

    <input type="submit" value="Edit Passenger">

</form>

</body>
</html>
