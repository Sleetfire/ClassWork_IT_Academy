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

<h3>Search flights</h3>

<form action="/MK_JD2-88-2-0.0.0/flights" method="post">
    <p>
        Departure Airports
        <select name="departureAirport" id="1">
            <option disabled>Choose Departure Airport</option>
            <option value=""></option>
            <c:forEach var="departureAirports" items="${departureAirports}">
                <option value="${departureAirports}">${departureAirports}</option>
            </c:forEach>
        </select>
    </p>

    <p>
        Arrival Airports
        <select name="arrivalAirport" id="2">
            <option disabled>Choose Arrival Airports</option>
            <option value=""></option>
            <c:forEach var="arrivalAirports" items="${arrivalAirports}">
                <option value="${arrivalAirports}">${arrivalAirports}</option>
            </c:forEach>
        </select>
    </p>

    <p>
        Scheduled Departures
        <select name="scheduledDeparture" id="3">
            <option disabled>Choose Scheduled Departure</option>
            <option value=""></option>
            <c:forEach var="scheduledDepartures" items="${scheduledDepartures}">
                <option value="${scheduledDepartures}">${scheduledDepartures}</option>
            </c:forEach>
        </select>
    </p>

    <p><input type="submit" value="Search"></p>
</form>

</body>
</html>
