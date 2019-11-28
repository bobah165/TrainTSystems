<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        body {background: url(/ROOT/forLoginImage.jpg); background-size: 100% }
        <%@include file="/WEB-INF/css/style-for-login.css"%>
        .btn {
            text-decoration: none;
            border-radius: 3px;
            font-family: 'BebasNeueRegular','Arial Narrow',Arial,sans-serif;
            color: #fff;
            font-size: 20px;
        }
        .forRegistration {
            width: 105%;
            height: 5%;
            cursor: pointer;
            background: rgb(61, 157, 179);
            padding: 8px 5px;
            font-family: 'BebasNeueRegular','Arial Narrow',Arial,sans-serif;
            color: #fff;
            font-size: 20px;
            border: 1px solid rgb(28, 108, 122);
            /*margin-left: 40px;*/
            margin-bottom: 0px;
            text-shadow: 0 1px 1px rgba(0, 0, 0, 0.5);
            -webkit-border-radius: 3px;
            -moz-border-radius: 3px;
            border-radius: 3px;
            -webkit-box-shadow: 0px 1px 6px 4px rgba(0, 0, 0, 0.07) inset,
            0px 0px 0px 3px rgb(254, 254, 254),
            0px 5px 3px 3px rgb(210, 210, 210);
            -moz-box-shadow:0px 1px 6px 4px rgba(0, 0, 0, 0.07) inset,
            0px 0px 0px 3px rgb(254, 254, 254),
            0px 5px 3px 3px rgb(210, 210, 210);
            box-shadow:0px 1px 6px 4px rgba(0, 0, 0, 0.07) inset,
            0px 0px 0px 3px rgb(254, 254, 254),
            0px 5px 3px 3px rgb(210, 210, 210);
            -webkit-transition: all 0.2s linear;
            -moz-transition: all 0.2s linear;
            -o-transition: all 0.2s linear;
            transition: all 0.2s linear;
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
                <h1>Login</h1>
                <p>
                    <label for="username" class="uname" data-icon="u" > Login </label>
                    <input id="username" name="login" required="required" type="text"/>
                </p>
                <p>
                    <label for="password" class="youpasswd" data-icon="p"> Password </label>
                    <input id="password" name="password" required="required" type="password"  />
                </p>
                <p class="btn" align="center" >
                    <button class="forRegistration"><a href="/registration/" class="btn"> Registration </a></button>
                </p>
                <p class="login button" align="center">
                    <input type="submit" value="Enter" />
                </p>
            </form>
        </div>
    </div>
</div>

</body>
</html>
