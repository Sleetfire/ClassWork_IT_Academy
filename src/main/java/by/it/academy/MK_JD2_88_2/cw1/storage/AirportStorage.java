package by.it.academy.MK_JD2_88_2.cw1.storage;

import by.it.academy.MK_JD2_88_2.cw1.dto.Airport;
import by.it.academy.MK_JD2_88_2.cw1.dto.AirportCity;
import by.it.academy.MK_JD2_88_2.cw1.dto.AirportName;
import by.it.academy.MK_JD2_88_2.cw1.dto.Coordinates;
import by.it.academy.MK_JD2_88_2.cw1.storage.api.DBInitializer;
import by.it.academy.MK_JD2_88_2.cw1.storage.api.IAirportStorage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirportStorage implements IAirportStorage {

    private static IAirportStorage instance = new AirportStorage();
    private final ObjectMapper mapper = new ObjectMapper();
    private final DataSource dataSource;

    private AirportStorage() {
        dataSource = DBInitializer.getInstance().getDataSource();
    }

    @Override
    public List<Airport> get(int page, int size) {
        List<Airport> airports = new ArrayList<>();
        int limit = size;
        int offset = (page - 1) * limit;
        String sql = "SELECT airport_code, airport_name, city, coordinates, timezone FROM bookings.airports_data";

        if (limit > 0) {
            sql += "\n LIMIT ? " + limit;
        }

        if (offset > 0) {
            sql += "\n OFFSET ?" + offset;
        }
        sql += ";";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.prepareStatement(sql)
        ) {
            statement.executeQuery(sql);
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
            throw new RuntimeException("???????????? ???????????????????? SQL", e);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("???????????? ???????????????????????????? json ???? ????", e);
        }

        return airports;
    }

    public static IAirportStorage getInstance() {
        return instance;
    }
}
