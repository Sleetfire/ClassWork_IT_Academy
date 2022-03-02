package by.it.academy.MK_JD2_88_2.cw1.storage;

import by.it.academy.MK_JD2_88_2.cw1.dto.Airport;
import by.it.academy.MK_JD2_88_2.cw1.dto.AirportCity;
import by.it.academy.MK_JD2_88_2.cw1.dto.AirportName;
import by.it.academy.MK_JD2_88_2.cw1.dto.Coordinates;
import by.it.academy.MK_JD2_88_2.cw1.storage.api.DBInitializer;
import by.it.academy.MK_JD2_88_2.cw1.storage.api.IAirportStorage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirportStorage implements IAirportStorage {

    private static IAirportStorage instance = new AirportStorage();
    private final ObjectMapper mapper = new ObjectMapper();

    private AirportStorage() {
        DBInitializer.getInstance();
    }

    @Override
    public List<Airport> get(int count) {
        List<Airport> airports = new ArrayList<>();
        String sql = "SELECT airport_code, airport_name, city, coordinates, timezone FROM bookings.airports_data LIMIT ?";
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/demo?ApplicationName=TestSweetApp", "postgres", "12378");
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, count);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                String airportCode = rs.getString(1);

                AirportName airportName = this.mapper.readValue(rs.getString(2), AirportName.class);
                AirportCity airportCity = this.mapper.readValue(rs.getString(3), AirportCity.class);
                String coordinatesStr = rs.getString(4);

                String[] coordinatesArr = coordinatesStr.replaceAll("(\\(|\\))", "").split(",");
                Coordinates coordinates = new Coordinates();
                coordinates.setX(coordinatesArr[0]);
                coordinates.setY(coordinatesArr[1]);

                String timeZone = rs.getString(5);

                Airport airport = new Airport();
                airport.setAirportCode(airportCode);
                airport.setAirportName(airportName);
                airport.setAirportCity(airportCity);
                airport.setTimezone(timeZone);
                airport.setCoordinates(coordinates);

                airports.add(airport);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка выполнения SQL", e);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Ошибка преобразования json из бд", e);
        }

        return airports;
    }

    public static IAirportStorage getInstance() {
        return instance;
    }
}
