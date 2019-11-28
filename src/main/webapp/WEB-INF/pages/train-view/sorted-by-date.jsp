
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


            <li><a href="#">Find</a>
                <ul class="submenu">
                    <li><a href="#">Train Number</a>
                        <br>
                        <form action="/train/findByTrainNumber" method="post">
                            <input name="trainNumber" required="required" type="number"/>
                            <input type="submit" value="Enter" />
                        </form>
                    </li>

                    <li><a href="#">Departure Time</a>
                        <br>
                        <form action="/train/findByDate" method="post">
                            <input name="departureDate" required="required" type="date"/>
                            <input type="submit" value="Enter" />
                        </form>
                    </li>
                </ul>
            </li>
            <li id="quit"><a href="/logout">Quit</a> </li>
        </ul>
    </nav>
</header>




<h1 align="center"> Trains </h1>

<table id="myTable" class="tablesorter">

    <tr>
        <th>
            <form action="/train/sortbynumber">
                <button class="forTable">Train Number</button>
            </form>

        </th>
        <th>Way</th>
        <th>count of sits</th>
        <th>
            <form action="/train/sortbydate">
                <button class="forTable">Departure Date</button>
            </form>
        </th>
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


    <tr>
        <td colspan="8">
            <c:forEach begin="${1}" end="${pageCount}" step="1" varStatus="i">
                <c:url value="/train/sortbydate" var="url">
                    <c:param name="page" value="${i.index}"/>
                </c:url>
                <a href="${url}">${i.index}</a>
            </c:forEach>
        </td>
    </tr>
</table>


</body>
</html>
