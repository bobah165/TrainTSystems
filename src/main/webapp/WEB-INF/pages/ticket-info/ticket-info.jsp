<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ticket Info</title>
</head>
<body>



   <h2>
       Train Id ${ticketInfo.idTrain} <br>
       Passenger Id ${ticketInfo.idPassenger} <br>
       Dparture Station ${ticketInfo.departureStation}<br>
       Departure Date ${ticketInfo.departureDate}<br>
       Departure Time ${ticketInfo.departureTime}<br>
       Arrival Station ${ticketInfo.arrivalStation}<br>
       Arrival Date ${ticketInfo.arrivalDate}<br>
       Arrival Time ${ticketInfo.arrivalTime}<br>
       Name ${ticketInfo.name}<br>
       Surname ${ticketInfo.surname}<br>
       Birthday ${ticketInfo.birthday}<br>
   </h2>




</body>
</html>
