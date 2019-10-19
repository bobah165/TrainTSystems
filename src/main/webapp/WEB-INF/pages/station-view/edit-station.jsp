
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Station</title>
</head>
<body>

<form action="/station/edit" method="post">

    <input type="hidden" name="id" value="${station.id}">

    <label for="nameStation">Station Name </label>
    <input type="text" name="nameStation" id="nameStation">

    <input type="submit" value="Edit Station">
</form>

</body>
</html>
