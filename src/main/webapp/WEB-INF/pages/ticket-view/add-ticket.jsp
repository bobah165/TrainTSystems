
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Ticket</title>
</head>
<body>

<form method="post" action="/ticket/add">

    <label for="departureStation">Departure Station </label>
    <input type="text" name="departureStation" id="departureStation">

    <label for="arrivalStation">Arrival Station</label>
    <input type="text" name="arrivalStation" id="arrivalStation">

    <label for="departureDate">Departure Date</label>
    <input type="text" name="departureDate" id="departureDate">

    <label for="arrivalDate">Arrival Date</label>
    <input type="text" name="arrivalDate" id="arrivalDate">

    <label for="train">Train</label>
    <input type="text" name="train.id" id="train">

    <label for="passenger">Passenger</label>
    <input type="text" name="passenger.id" id="passenger">

    <input type="submit" value="Add Ticket">

</form>

</body>
</html>
