
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>buy ticket</title>
</head>
<body>

<form method="post" action="/buy/">

    <label for="name">Name </label>
    <input type="text" name="name" id="name">

    <label for="surname">Surname</label>
    <input type="text" name="surname" id="surname">

    <label for="birthday">Birthday</label>
    <input type="date" name="birthday" id="birthday">


    <input type="submit" value="Add Passenger">

</form>


</body>
</html>
