
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add Station</title>

    <style>
        <%@include file="/WEB-INF/css/style-for-login.css"%>
        #register,
        #login {
            width: 20%;
        }
    </style>
    <%@include file="/WEB-INF/pages/main/employee.jsp"%>

</head>
<body>

<div id="container_demo" >
    <a class="hiddenanchor" id="toregister"></a>
    <a class="hiddenanchor" id="tologin"></a>
    <div id="wrapper">
        <div id="login" class="animate form">
            <form action="/station/add" autocomplete="on" method="post">
                <h1>Station</h1>
                <p>
                    <label for="nameStation" class="uname" data-icon="u" > Station Name </label>
                    <input id="nameStation" name="nameStation" required="required" type="text"/>
                </p>

                <p class="login button">
                    <input type="submit" value="Add" />
                </p>
            </form>
        </div>
    </div>
</div>


</body>
</html>
