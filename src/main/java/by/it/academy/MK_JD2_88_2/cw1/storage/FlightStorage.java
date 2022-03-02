package by.it.academy.MK_JD2_88_2.cw1.storage;

import by.it.academy.MK_JD2_88_2.cw1.dto.*;
import by.it.academy.MK_JD2_88_2.cw1.storage.api.DBInitializer;
import by.it.academy.MK_JD2_88_2.cw1.storage.api.IFlightStorage;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class FlightStorage implements IFlightStorage {

    private static IFlightStorage instance = new FlightStorage();

    private FlightStorage() {
        DBInitializer.getInstance();
    }

    @Override
    public List<Flight> get(String departureAirport, String arrivalAirport, String scheduledDeparture) {
        List<Flight> flights = new ArrayList<>();
        String sql = "SELECT flight_id, flight_no, scheduled_departure, scheduled_arrival,\n" +
                "departure_airport, arrival_airport, status, aircraft_code,\n" +
                "actual_departure, actual_arrival\n" +
                "FROM bookings.flights";

        if (departureAirport != null || arrivalAirport != null || scheduledDeparture != null) {

            StringBuilder builder = new StringBuilder(sql);
            builder.append(" WHERE ");
            if (departureAirport != null) {
                builder.append("departure_airport =?");
            }

            if (arrivalAirport != null) {
                if (departureAirport != null) {
                    builder.append(" AND ");
                }
                builder.append("arrival_airport =?");
            }

            if (scheduledDeparture != null) {
                if (departureAirport != null || arrivalAirport != null) {
                    builder.append(" AND ");
                }
                builder.append("scheduled_departure =?");
            }
            sql = builder.toString();
        }

        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/demo?ApplicationName=TestSweetApp", "postgres", "12378");
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            if (departureAirport != null) {
                statement.setString(1, departureAirport);
            }
            if (arrivalAirport != null) {
                if (departureAirport != null) {
                    statement.setString(2, arrivalAirport);
                } else {
                    statement.setString(1, arrivalAirport);
                }
            }

            if (scheduledDeparture != null) {
                if (departureAirport != null && arrivalAirport != null) {
                    statement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.parse(scheduledDeparture)));
                } else if (departureAirport != null || arrivalAirport != null) {
                    statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.parse(scheduledDeparture)));
                } else {
                    statement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.parse(scheduledDeparture)));
                }
            }

            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                int flightIdDB = rs.getInt(1);
                String flightNoDB = rs.getString(2);
                LocalDateTime scheduledDepartureDB = rs.getTimestamp(3).toLocalDateTime();
                LocalDateTime scheduledArrivalDB = rs.getTimestamp(4).toLocalDateTime();
                String departureAirportDB = rs.getString(5);
                String arrivalAirportDB = rs.getString(6);
                String statusDB = rs.getString(7);
                String aircraftCodeDB = rs.getString(8);
                LocalDateTime actualDepartureDB = null;
                LocalDateTime actualArrivalDB = null;

                if (rs.getTimestamp(9) != null) {
                    actualDepartureDB = rs.getTimestamp(9).toLocalDateTime();
                }
                if (rs.getTimestamp(10) != null) {
                    actualArrivalDB = rs.getTimestamp(10).toLocalDateTime();
                }

                Flight flight = new Flight();
                flight.setFlightId(flightIdDB);
                flight.setFlightNo(flightNoDB);
                flight.setScheduledDeparture(scheduledDepartureDB);
                flight.setScheduledArrival(scheduledArrivalDB);
                flight.setDepartureAirport(departureAirportDB);
                flight.setArrivalAirport(arrivalAirportDB);
                flight.setStatus(statusDB);
                flight.setAircraftCode(aircraftCodeDB);
                flight.setActualDeparture(actualDepartureDB);
                flight.setActualArrival(actualArrivalDB);
                flights.add(flight);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка выполнения SQL", e);
        }
        return flights;
    }

    public static IFlightStorage getInstance() {
        return instance;
    }
}
