<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 20.10.2019
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>buy ticket</title>
</head>
<body>

<form method="post" action="/buy/passenger">

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
