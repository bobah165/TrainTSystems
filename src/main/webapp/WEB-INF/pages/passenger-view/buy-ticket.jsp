
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>buy ticket</title>
    <style>
        <%@include file="/WEB-INF/css/style-for-login.css"%>
        #register,
        #login {
            width: 35%;
        }
    </style>
    <%@include file="/WEB-INF/pages/main/passenger.jsp"%>

</head>


<body>


<div id="container_demo" >
    <a class="hiddenanchor" id="toregister"></a>
    <a class="hiddenanchor" id="tologin"></a>
    <div id="wrapper">
        <div id="login" class="animate form">
            <form  action="/train/buy/" autocomplete="on" method="post">
                <h1>Passenger Information</h1>


                <p>
                    <label for="name" class="uname" data-icon="u" > Name </label>
                    <input id="name" name="name" required="required" type="text" value="${passenger.name}"/>
                </p>
                <p>
                    <label for="surname" class="youpasswd" data-icon="p"> Surname </label>
                    <input id="surname" name="surname" required="required" type="text" value="${passenger.surname}" />
                </p>
                <p>
                    <label for="birthday" class="youpasswd" data-icon="p"> Birthday </label>
                    <input id="birthday" name="birthday" required="required" type="date" value="${passenger.birthday}" />
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
                    <input type="submit" value="Enter" />
                </p>

            </form>
        </div>
    </div>
</div>


</body>

</html>
