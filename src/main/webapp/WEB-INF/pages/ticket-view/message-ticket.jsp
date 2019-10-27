<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ticket Message</title>
</head>
<body>

<h1>You have been registrated on this train or there is no free sit</h1>

<c:url value="/main/pass" var="pass" />
<a href="${pass}">Back to main</a>

</body>
</html>
