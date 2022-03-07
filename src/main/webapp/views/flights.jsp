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

<h3>Таблица полетов</h3>
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

<form action="/MK_JD2-88-2-0.0.0/flights" method="post">
    <p>
        Departure Airports
        <select name="departureAirport" id="1">
            <option disabled>Choose Departure Airport</option>
            <c:forEach var="departureAirports" items="${departureAirports}">
                <option value="${departureAirports}">${departureAirports}</option>
            </c:forEach>
        </select>
    </p>

    <p>
        Arrival Airports
        <select name="arrivalAirport" id="2">
            <option disabled>Choose Arrival Airports</option>
            <c:forEach var="arrivalAirports" items="${arrivalAirports}">
                <option value="${arrivalAirports}">${arrivalAirports}</option>
            </c:forEach>
        </select>
    </p>

    <p>
        Scheduled Departures
        <select name="scheduledDeparture" id="3">
            <option disabled>Choose Scheduled Departure</option>
            <c:forEach var="scheduledDepartures" items="${scheduledDepartures}">
                <option value="${scheduledDepartures}">${scheduledDepartures}</option>
            </c:forEach>
        </select>
    </p>

    <p><input type="submit" value="Send"></p>
</form>



</body>
</html>
