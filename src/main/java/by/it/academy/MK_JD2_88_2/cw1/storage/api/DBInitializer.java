package by.it.academy.MK_JD2_88_2.cw1.storage.api;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

public class DBInitializer {

    private volatile static DBInitializer instance;

    private final ComboPooledDataSource cpds;

    private DBInitializer() throws PropertyVetoException {
        cpds = new ComboPooledDataSource();
        cpds.setDriverClass("org.postgresql.Driver");
        cpds.setJdbcUrl("jdbc:postgresql://localhost:5432/demo?ApplicationName=TestSweetApp");
        cpds.setUser("postgres");
        cpds.setPassword("12378");
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        cpds.setMaxStatements(180);
    }

    public DataSource getDataSource() {
        return this.cpds;
    }

    public static DBInitializer getInstance() {
        if (instance == null) {
            synchronized (DBInitializer.class) {
                if (instance == null) {
                    try {
                        instance = new DBInitializer();
                    } catch (Exception e) {
                        throw new RuntimeException("Ошибка подключения к базе", e);
                    }
                }
            }
        }
        return instance;
    }
}
