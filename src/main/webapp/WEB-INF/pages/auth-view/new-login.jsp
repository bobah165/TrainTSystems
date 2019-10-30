
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        <%@include file="/WEB-INF/css/style-for-login.css"%>
    </style>
</head>
<body>



<div id="container_demo" >
    <a class="hiddenanchor" id="toregister"></a>
    <a class="hiddenanchor" id="tologin"></a>
    <div id="wrapper">
        <div id="login" class="animate form">
            <form  action="/login/process/" autocomplete="on" method="post">
                <h1>Вход</h1>
                <p>
                    <label for="username" class="uname" data-icon="u" > Ваш логин</label>
                    <input id="username" name="login" required="required" type="text"/>
                </p>
                <p>
                    <label for="password" class="youpasswd" data-icon="p"> Ваш пароль </label>
                    <input id="password" name="password" required="required" type="password"  />
                </p>
                <p class="keeplogin">
                    <input type="checkbox" name="loginkeeping" id="loginkeeping" value="loginkeeping" />
                    <label for="loginkeeping">Запомнить меня</label>
                </p>
                <p class="login button">
                    <input type="submit" value="Войти" />
                </p>
            </form>
        </div>
    </div>
</div>

</body>
</html>
