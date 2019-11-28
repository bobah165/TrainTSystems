
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Trains</title>
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

            <li><a href="/train/">Train List</a></li>

            <li><a href="#">Find</a>
                <ul class="submenu">
                    <li><a href="#">Departure Date</a>
                        <br>
                        <form action="/train/findByDate" method="post">
                            <input name="departureDate" required="required" type="date"/>
                            <input type="submit" value="Enter" />
                        </form>
                    </li>
                </ul>
            </li>

            <li ><a href="/train/add">Add Train</a> </li>
            <li id="quit"><a href="/logout">Quit</a> </li>
        </ul>
    </nav>
</header>




<h1 align="center"> Trains </h1>

<table id="myTable" class="tablesorter">

    <tr>
        <th>Train Number</th>
        <th>Way</th>
        <th>count of sits</th>
        <th>Departure Date</th>
        <th>Passengers</th>
        <th>Edit Train</th>
        <th>Delete Train</th>
    </tr>


    <c:forEach var="train" items="${trainList}">

        <tr>
            <td>${train.trainNumber}</td>
            <td>${train.trainWay.id}</td>
            <td>${train.countSits}</td>
            <td>${train.departureDate}</td>
            <td>
                <button class=""><a href="/train/passfromtrain/${train.id}" class="btn">Passengers</a></button>
            </td>
            <td>
                <button><a href="/train/edit/${train.id}" class="btn">edit</a></button>
            </td>
            <td>
                <button><a href="/train/delete/${train.id}" class="btn">delete</a></button>
            </td>
        </tr>

    </c:forEach>
</table>

</body>
</html>
