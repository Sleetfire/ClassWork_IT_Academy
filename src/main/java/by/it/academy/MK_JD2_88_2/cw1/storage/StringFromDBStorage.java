package by.it.academy.MK_JD2_88_2.cw1.storage;

import by.it.academy.MK_JD2_88_2.cw1.storage.api.DBInitializer;
import by.it.academy.MK_JD2_88_2.cw1.storage.api.IParameterFromDBStorage;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StringFromDBStorage implements IParameterFromDBStorage<String> {

    private static IParameterFromDBStorage<String> instance = new StringFromDBStorage();
    private final DataSource dataSource;

    private StringFromDBStorage() {
        dataSource = DBInitializer.getInstance().getDataSource();
    }

    @Override
    public List<String> getByColumnName(String columnName, String tableName) {
        List<String> names = new ArrayList<>();
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
                String name = rs.getString(columnName);
                names.add(name);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подключения к базе", e);
        }
        return names;
    }

    public static IParameterFromDBStorage<String> getInstance() {
        return instance;
    }
}
