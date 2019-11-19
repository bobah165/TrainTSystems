<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add</title>
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
            <form  action="/train/add" autocomplete="on" method="post">
                <h1>Train</h1>
                <p>
                    <label for="trainNumber" class="uname" data-icon="u" > Train Number </label>
                    <input id="trainNumber" name="trainNumber" required="required" type="number"/>
                </p>
                <p>
                    <label for="trainWay" class="youpasswd" data-icon="p"> Train Way </label>
                    <input id="trainWay" name="trainWay.id" required="required" type="number"  />
                </p>
                <p>
                    <label for="departureDate" class="youpasswd" data-icon="p"> Departure Date </label>
                    <input id="departureDate" name="departureDate" required="required" type="date"  />
                </p>
                <p>
                    <label for="countSits" class="youpasswd" data-icon="p"> Count Seats </label>
                    <input id="countSits" name="countSits" required="required" type="number"  />
                </p>
                <p>
                    <label for="schedule" class="youpasswd" data-icon="p"> Schedule </label>
                    <input id="schedule" name="schedule" required="required" type="text" list="ways"  />
                    <datalist id="ways">
                        <option>everyday</option>
                        <option>other</option>
                        <option>odd</option>
                        <option>even</option>
                    </datalist>
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
                    <input type="submit" value="Add" />
                </p>
            </form>
        </div>
    </div>
</div>


</form>

</body>
</html>
