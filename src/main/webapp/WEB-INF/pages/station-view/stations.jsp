
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Stations</title>
    <style>
        <%@include file="/WEB-INF/css/style.css"%>
        .btn {
            text-decoration: none; /* Убираем подчёркивание */
            border-radius: 3px; /* Скругляем уголки */
        }
    </style>

</head>
<body>

<div class="top_bar"></div>
<div class="welcome">
    <img src='<c:url value="/ROOT/train.jpg"></c:url>'  width="600" height="250"/>
</div>

<header>
    <div class="top_bar"></div>
    <nav>
        <ul class="menu">
            <li><a href="/empl">Back</a></li>
            <li><a href="">Find Station</a>
                <ul class="submenu">
                    <li>
                        <br>
                        <form action="/station/find" method="post">
                            <input name="nameStation" required="required" type="text"/>
                        </form>
                    </li>
                </ul>
            </li>
            <li id="quit"><a href="/logout">Quit</a> </li>
        </ul>
    </nav>
</header>




<h1 align="center">Stations</h1>


<table id="stationTable">
    <tr>
        <th>
            <form action="/station/sorted">
                <button class="forTable">Name</button>
            </form>
        </th>
        <th>edit</th>
        <th>delete</th>
    </tr>
    <c:forEach var="station" items="${stationsList}">
        <tr>
            <td>${station.nameStation}</td>
            <td>
                <button><a href="/station/edit/${station.id}" class="btn">edit</a></button>
            </td>
            <td>
                <button><a href="/station/delete/${station.id}" class="btn">delete</a></button>
            </td>
        </tr>
    </c:forEach>

    <tr>
        <td colspan="4">
            <c:forEach begin="${1}" end="${pageCount}" step="1" varStatus="i">
                <c:url value="/station/" var="url">
                    <c:param name="page" value="${i.index}"/>
                </c:url>
                <a href="${url}">${i.index}</a>
            </c:forEach>
        </td>
    </tr>
</table>

</body>
</html>
