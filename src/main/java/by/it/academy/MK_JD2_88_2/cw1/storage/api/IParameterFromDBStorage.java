package by.it.academy.MK_JD2_88_2.cw1.storage.api;

import java.util.List;

public interface IParameterFromDBStorage<T> {

    List<T> getByColumnName(String columnName, String tableName);

}
