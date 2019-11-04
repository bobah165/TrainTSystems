<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>

<body>

    <form action="/login/process/" method="post">
        <div><label> User Name : <input type="text" name="login"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>

        <div><input type="submit" value="Sign In"/></div>
    </form>


</body>
</html>
