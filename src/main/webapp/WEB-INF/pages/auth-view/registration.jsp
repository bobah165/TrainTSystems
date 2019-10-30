
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Registration</title>
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
            <form  action="/registration" autocomplete="on" method="post">
                <h1>Вход</h1>
                <p>
                    <label for="username" class="uname" data-icon="u" > Ваш логин</label>
                    <input id="username" name="login" required="required" type="text"/>
                </p>
                <p>
                    <label for="password" class="youpasswd" data-icon="p"> Ваш пароль </label>
                    <input id="password" name="password" required="required" type="password"  />
                </p>
                <p>
                    <label for="birthday" class="youpasswd" data-icon="p"> День рождения </label>
                    <input id="birthday" name="birthday" required="required" type="date"  />
                </p>
                <p>
                    <label for="email" class="youpasswd" data-icon="p"> Email </label>
                    <input id="email" name="email" required="required" type="text"  />
                </p>
                <p>
                    <label for="name" class="youpasswd" data-icon="p"> Имя </label>
                    <input id="name" name="name" required="required" type="text"  />
                </p>
                <p>
                    <label for="surname" class="youpasswd" data-icon="p"> Фамилия </label>
                    <input id="surname" name="surname" required="required" type="text"  />
                </p>
                <p>
                    <label for="user" class="youpasswd" data-icon="p"> Пользователь </label>
                    <input id="user" name="user" required="required" type="text" list="users"  />
                    <datalist id="users">
                        <option>passenger</option>
                        <option>employee</option>
                    </datalist>
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
