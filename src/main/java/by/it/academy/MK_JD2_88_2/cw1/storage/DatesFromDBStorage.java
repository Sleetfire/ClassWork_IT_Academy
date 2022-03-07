package by.it.academy.MK_JD2_88_2.cw1.storage;

import by.it.academy.MK_JD2_88_2.cw1.storage.api.DBInitializer;
import by.it.academy.MK_JD2_88_2.cw1.storage.api.IParameterFromDBStorage;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DatesFromDBStorage implements IParameterFromDBStorage<LocalDateTime> {

    private static IParameterFromDBStorage<LocalDateTime> instance = new DatesFromDBStorage();
    private final DataSource dataSource;

    private DatesFromDBStorage() {
        dataSource = DBInitializer.getInstance().getDataSource();
    }

    @Override
    public List<LocalDateTime> getByColumnName(String columnName, String tableName) {
        List<LocalDateTime> dates = new ArrayList<>();
        String sql = "SELECT DISTINCT ";

        sql += columnName;
        sql += " FROM ";
        sql += tableName;
        sql += ";";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
        ) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                LocalDateTime dateTime = rs.getTimestamp(columnName).toLocalDateTime();
                dates.add(dateTime);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подключения к базе", e);
        }
        return dates;
    }

    public static IParameterFromDBStorage<LocalDateTime> getInstance() {
        return instance;
    }
}
