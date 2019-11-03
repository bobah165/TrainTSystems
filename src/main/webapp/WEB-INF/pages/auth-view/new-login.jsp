<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        <%@include file="/WEB-INF/css/style-for-login.css"%>
        .btn {
            text-decoration: none; /* Убираем подчёркивание */
            border-radius: 3px; /* Скругляем уголки */
        }
    </style>
</head>
<body>



<div id="container_demo" >
    <a class="hiddenanchor" id="toregister"></a>
    <a class="hiddenanchor" id="tologin"></a>
    <div id="wrapper">
        <div id="login" class="animate form">
            <form  action="/login/process/" autocomplete="on" method="post">
                <h1>Entrance</h1>
                <p>
                    <label for="username" class="uname" data-icon="u" > Login </label>
                    <input id="username" name="login" required="required" type="text"/>
                </p>
                <p>
                    <label for="password" class="youpasswd" data-icon="p"> Password </label>
                    <input id="password" name="password" required="required" type="password"  />
                </p>
                <p class="btn">
                    <button><a href="/registration/" class="btn"> Registration </a></button>
                </p>
                <p class="login button">
                    <input type="submit" value="Enter" />
                </p>
            </form>
        </div>
    </div>
</div>

</body>
</html>
