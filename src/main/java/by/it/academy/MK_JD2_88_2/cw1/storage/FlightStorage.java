package by.it.academy.MK_JD2_88_2.cw1.storage;

import by.it.academy.MK_JD2_88_2.cw1.dto.*;
import by.it.academy.MK_JD2_88_2.cw1.storage.api.DBInitializer;
import by.it.academy.MK_JD2_88_2.cw1.storage.api.IFlightStorage;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightStorage implements IFlightStorage {

    private static IFlightStorage instance = new FlightStorage();
    private final DataSource dataSource;

    private FlightStorage() {
        dataSource = DBInitializer.getInstance().getDataSource();
    }

    @Override
    public List<Flight> get() {
        return get(null, null);
    }

    @Override
    public List<Flight> get(Pageable pageable) {
        return get(null, pageable);
    }

    @Override
    public List<Flight> get(FlightFilter filter) {
        return get(filter, null);
    }

    @Override
    public List<Flight> get(FlightFilter filter, Pageable pageable) {
        List<Flight> flights = new ArrayList<>();

        Integer limit = null;
        Integer offset = null;

        if (pageable != null) {
            if (pageable.getSize() > 0) {
                limit = pageable.getSize();
            }

            if (limit != null && pageable.getPage() > 0) {
                offset = (pageable.getPage() - 1) * limit;
            }
        }

        String sql = "SELECT flight_id," +
                " flight_no," +
                " scheduled_departure," +
                " scheduled_arrival,\n" +
                "departure_airport," +
                " arrival_airport," +
                " status," +
                " aircraft_code,\n" +
                "actual_departure," +
                " actual_arrival\n" +
                "FROM bookings.flights";

        List<Object> params = new ArrayList<>();

        if (filter != null) {
            String where = "";
            if (filter.getArrivalAirport() != null && !filter.getArrivalAirport().isEmpty()) {
                where += "arrival_airport = ?";
                params.add(filter.getArrivalAirport());
            }
            if (filter.getDepartureAirport() != null && !filter.getDepartureAirport().isEmpty()) {
                if (!where.isEmpty()) {
                    where += " AND ";
                }
                where += "departure_airport = ?";
                params.add(filter.getDepartureAirport());
            }
            if (filter.getScheduledDeparture() != null) {
                if (!where.isEmpty()) {
                    where += " AND ";
                }
                where += "scheduled_departure = ?";
                params.add(filter.getScheduledDeparture());
            }
            if (!where.isEmpty()) {
                sql += "\n WHERE " + where;
            }
        }

        if (limit != null) {
            sql += "\n LIMIT " + limit;
        }
        if (offset != null) {
            sql += "\n OFFSET " + offset;
        }
        sql += ";";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            int index = 1;
            for (Object param : params) {
                statement.setObject(index++, param);
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
