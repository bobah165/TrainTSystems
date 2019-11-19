
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="с" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Registration</title>
    <style>
        <%@include file="/WEB-INF/css/style-for-login.css"%>
        #register,
        #login {
            width: 25%;
        }
    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

</head>
<body>

<div id="container_demo" >
    <a class="hiddenanchor" id="toregister"></a>
    <a class="hiddenanchor" id="tologin"></a>
    <div id="wrapper">
        <div id="login" class="animate form">

            <form action="/registration" autocomplete="on" method="post">
                <h1>Registration</h1>

                <p>
                    <label for="username" class="uname" data-icon="u" > Login </label>
                    <input id="username" name="login" required="required" type="text"/>
                </p>
                <p>
                    <label for="password" class="youpasswd" data-icon="p"> Password </label>
                    <input id="password" name="password" required="required" type="password"  />
                </p>
                <p>
                    <label for="birthday" class="youpasswd" data-icon="p"> Birthday </label>
                    <input id="birthday" name="birthday" required="required" type="date"  />
                </p>
                <p>
                    <label for="email" class="youpasswd" data-icon="p"> Email </label>
                    <input id="email" name="email" required="required" type="text"  />
                </p>
                <p>
                    <label for="name" class="youpasswd" data-icon="p"> Name </label>
                    <input id="name" name="name" required="required" type="text"  />
                </p>
                <p>
                    <label for="surname" class="youpasswd" data-icon="p"> Surname </label>
                    <input id="surname" name="surname" required="required" type="text"  />
                </p>

                <p>
                    <!-- Error Message -->
                <div id="errormessage">
                    <c:if test="${not empty errMsg}">
                        <h4 class="text-danger">${errMsg}</h4>
                    </c:if>
                </div>
                </p>

                <p class="login button">
                    <input type="submit" value="Submit" />
                </p>

            </form>

        </div>
    </div>
</div>


</body>
</html>
