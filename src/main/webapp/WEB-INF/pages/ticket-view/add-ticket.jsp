
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Ticket</title>
</head>
<body>

<form method="post" action="/ticket/add">


    <label for="train">Train</label>
    <input type="text" name="train.id" id="train">

    <label for="passenger">Passenger</label>
    <input type="text" name="passenger.id" id="passenger">

    <input type="submit" value="Add Ticket">

</form>

</body>
</html>
