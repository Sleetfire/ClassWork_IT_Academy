<%--
  Created by IntelliJ IDEA.
  User: barko
  Date: 06.03.2022
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Flights</title>
</head>
<body>

<h3>Flights table</h3>
<table border="1px">
    <tr>
        <th width="10%">Flight Id</th>
        <th width="10%">Flight No</th>
        <th width="10%">Scheduled Departure</th>
        <th width="10%">Scheduled Arrival</th>
        <th width="10%">Departure Airport</th>
        <th width="10%">Arrival Airport</th>
        <th width="10%">Status</th>
        <th width="10%">Aircraft Code</th>
        <th width="10%">Actual Departure</th>
        <th width="10%">Actual Arrival</th>
    </tr>
    <c:forEach var="flights" items="${flights}">
        <tr>
            <td width="10%"> ${flights.flightId} </td>
            <td width="10%"> ${flights.flightNo} </td>
            <td width="10%"> ${flights.scheduledDeparture} </td>
            <td width="10%"> ${flights.scheduledArrival} </td>
            <td width="10%"> ${flights.departureAirport} </td>
            <td width="10%"> ${flights.arrivalAirport} </td>
            <td width="10%"> ${flights.status} </td>
            <td width="10%"> ${flights.aircraftCode} </td>
            <td width="10%"> ${flights.actualDeparture} </td>
            <td width="10%"> ${flights.actualArrival} </td>
        </tr>
    </c:forEach>
</table>

<p><input type="button" onclick="location.href='/MK_JD2-88-2-0.0.0/flights_main'" value="Back"></p>

</body>
</html>
