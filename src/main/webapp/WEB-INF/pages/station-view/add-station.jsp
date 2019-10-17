
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add Station</title>
</head>
<body>

<form action="/station/add" method="post">

    <label for="nameStation">Station Name</label>
    <input type="text" name="nameStation" id="nameStation">

    <input type="submit" value="Add new Station">

</form>

</body>
</html>
