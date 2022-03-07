package by.it.academy.MK_JD2_88_2.cw1.service;

import by.it.academy.MK_JD2_88_2.cw1.service.api.IParameterFromDBService;
import by.it.academy.MK_JD2_88_2.cw1.storage.DatesFromDBStorage;
import by.it.academy.MK_JD2_88_2.cw1.storage.api.IParameterFromDBStorage;

import java.time.LocalDateTime;
import java.util.List;

public class DatesFromDBService implements IParameterFromDBService<LocalDateTime> {

    private static IParameterFromDBService<LocalDateTime> instance = new DatesFromDBService();
    private final IParameterFromDBStorage<LocalDateTime> storage = DatesFromDBStorage.getInstance();

    private DatesFromDBService() {

    }

    @Override
    public List<LocalDateTime> getByColumnName(String columnName, String tableName) {
        return this.storage.getByColumnName(columnName, tableName);
    }

    public static IParameterFromDBService<LocalDateTime> getInstance() {
        return instance;
    }
}
