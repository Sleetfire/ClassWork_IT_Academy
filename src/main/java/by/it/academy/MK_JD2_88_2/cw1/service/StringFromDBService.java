package by.it.academy.MK_JD2_88_2.cw1.service;

import by.it.academy.MK_JD2_88_2.cw1.service.api.IParameterFromDBService;
import by.it.academy.MK_JD2_88_2.cw1.storage.StringFromDBStorage;
import by.it.academy.MK_JD2_88_2.cw1.storage.api.IParameterFromDBStorage;

import java.util.List;

public class StringFromDBService implements IParameterFromDBService<String> {

    private static IParameterFromDBService<String> instance = new StringFromDBService();
    private final IParameterFromDBStorage<String> storage = StringFromDBStorage.getInstance();

    private StringFromDBService() {

    }

    @Override
    public List<String> getByColumnName(String columnName, String tableName) {
        return this.storage.getByColumnName(columnName, tableName);
    }

    public static IParameterFromDBService<String> getInstance() {
        return instance;
    }
}
