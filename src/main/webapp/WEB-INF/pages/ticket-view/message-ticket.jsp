<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ticket Message</title>
</head>
<body>

<h1>You have been registrated on this train or there is no free sit</h1>

<h2>ADD</h2>
<c:url value="/train/" var="train" />
<a href="${train}">Back to Timetable</a>

</body>
</html>
