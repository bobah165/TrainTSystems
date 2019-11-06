
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        <%@include file="/WEB-INF/css/style-for-login.css"%>
        #register,
        #login {
            width: 30%;
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
            <form  action="/findtrain/trainfromstations/" autocomplete="on" method="post">
                <h1>Find Train</h1>
                <p>
                    <label for="stationA" class="uname" data-icon="u" > Departure Station </label>
                    <input id="stationA" name="stationA" required="required" type="text"/>
                </p>
                <p>
                    <label for="stationB" class="youpasswd" data-icon="p"> Arrival Station </label>
                    <input id="stationB" name="stationB" required="required" type="text"  />
                </p>
                <p>
                    <label for="departureDate" class="youpasswd" data-icon="p"> Departure Date </label>
                    <input id="departureDate" name="departureDate" required="required" type="date"  />
                </p>
                <p>
                    <label for="startTime" class="youpasswd" data-icon="p"> Start Time </label>
                    <input id="startTime" name="startTime" required="required" type="time"  value="00:00" />
                </p>
                <p>
                    <label for="endTime" class="youpasswd" data-icon="p"> End Time </label>
                    <input id="endTime" name="endTime" required="required" type="time" value="23:59"  />
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
